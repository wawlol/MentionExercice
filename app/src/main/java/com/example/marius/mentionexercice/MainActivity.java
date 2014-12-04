package com.example.marius.mentionexercice;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;




public class MainActivity extends Activity {

    private MentionAdapter mAdapter;
    private ArrayList<Mention> mArrayOfList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMentionsList();
        changeOnClick();
    }
    private void populateMentionsList() {
        HttpGetMention httpGetMention = new HttpGetMention();
        httpGetMention.execute();

    }
    private void changeOnClick() {

        ListView listView = (ListView) findViewById(R.id.mention);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                final Mention mention = mArrayOfList.get(position);

                class ProgressTask extends AsyncTask<Void, Void, Boolean> {
                    private int x;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                        if (mention.isRead()) {
                            mention.setRead(false);
                            x = 1;
                        }
                        if (mention.isWait()) mention.setWait(false);
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    protected Boolean doInBackground(Void... arg0) {

                        try {
                            Thread.sleep(1000);
                            return true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    @Override
                    protected void onPostExecute(Boolean result) {
                        super.onPostExecute(result);
                        mention.setWait(true);
                        if (x != 1) mention.setRead(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                ProgressTask progresstask = new ProgressTask();
                progresstask.execute();

            }
        });
    }

    class HttpGetMention extends AsyncTask<Void, Void, ArrayList<Mention>> {

        private static final String ACCEPT_HEADER = "application/json";
        private static final String ACCEPT_LANGUAGE_HEADER = "fr";
        private static final String TOKEN_HEADER = "Bearer ZTlhODAzMmMxZGU4NGI4NDA2OTA0MzFmOTIwZTZkY2ViMTdiYjg4YmQwNWNmNTEyMjc3NzBlOGZjMzJjNTZlOQ";
        private static final String URL = "https://api.mention.net/api/accounts/349583_3jzkp761p4aogw88oocgo8s8gc88kg0wkwgo0ko0s48gk88s0o/alerts/874910/mentions";

        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected ArrayList<Mention> doInBackground(Void... params) {

            HttpGet request = new HttpGet(URL);
            request.addHeader("Accept", ACCEPT_HEADER);
            request.addHeader("Accept-Language", ACCEPT_LANGUAGE_HEADER);
            request.addHeader("Authorization", TOKEN_HEADER );

            JSONResponseHandler responseHandler = new JSONResponseHandler();

            try {
                ArrayList<Mention> local = mClient.execute(request, responseHandler);
                if (null != mClient)
                    mClient.close();
                return local;

            } catch (ClientProtocolException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<Mention> result) {

            ArrayList<Mention> arrayOfMention = result;
            MentionAdapter adapter = new MentionAdapter(getApplicationContext(), arrayOfMention);
            ListView listView = (ListView) findViewById(R.id.mention);
            listView.setAdapter(adapter);
            mAdapter = adapter;
            mArrayOfList = arrayOfMention;


        }
    }

    class JSONResponseHandler implements ResponseHandler<ArrayList<Mention>> {

        private static final String SPACE = "";
        private static final String TITLE_TAG = "title";
        private static final String SOURCE_TAG = "source_name";
        private static final String MENTIONS_TAG = "mentions";

        @Override
        public ArrayList<Mention> handleResponse(HttpResponse response)
                throws ClientProtocolException, IOException {
            ArrayList<Mention> result = new ArrayList<Mention>();
            String JSONResponse = new BasicResponseHandler()
                    .handleResponse(response);
            try {

                JSONObject responseObject = (JSONObject) new JSONTokener(
                        JSONResponse).nextValue();

                JSONArray mentions = responseObject
                        .getJSONArray(MENTIONS_TAG);

                for (int idx = 0; idx < mentions.length(); idx++) {


                    JSONObject mention = (JSONObject) mentions.get(idx);

                    result.add(new Mention(R.drawable.logo1, R.drawable.avatar1,SPACE + mention.get(SOURCE_TAG), "30min", SPACE + mention.get(TITLE_TAG) ,true, true));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

}
