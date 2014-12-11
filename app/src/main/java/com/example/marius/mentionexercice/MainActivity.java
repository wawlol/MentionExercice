package com.example.marius.mentionexercice;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private MentionAdapter mAdapter = null;
    private ArrayList<Mention> mArrayOfList = null;
    private String mHref = "https://api.mention.net/api/accounts/349583_3jzkp761p4aogw88oocgo8s8gc88kg0wkwgo0ko0s48gk88s0o/alerts/874910/mentions";
    private boolean mLoaded = true;
    private boolean mLoaded2 = true;
    private Activity ctx = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateMentionsList();
        changeOnClick();

    }

    public void populateMentionsList() {

        HttpGetMention httpGetMention = new HttpGetMention();
        httpGetMention.execute();
    }

    private void loadMoreMentions() {
        ListView listView = (ListView) findViewById(R.id.mention);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (mLoaded) {
                    boolean shouldLoadMore = firstVisibleItem + visibleItemCount >= totalItemCount;

                    if (shouldLoadMore) {
                        mLoaded = false;
                        populateMentionsList();
                    }
                }
            }
        });
    }

    private void changeOnClick() {


        ListView listView = (ListView) findViewById(R.id.mention);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                final Mention mention = mArrayOfList.get(position);

                class ProgressTask extends AsyncTask<Void, Void, Boolean> {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        mention.setState(Mention.State.WAIT);
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

                        mention.setState(Mention.State.READ);
                        mAdapter.notifyDataSetChanged();
                        }
                    }
                System.out.println("La position de la mention est" + position);
                if (mention.getState() == Mention.State.UNREAD) {
                    ProgressTask progresstask = new ProgressTask();
                    progresstask.execute();
                }

                }
        });

    }

    class HttpGetMention extends AsyncTask<Void, Void, ArrayList<Mention>> {


//        private AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected ArrayList<Mention> doInBackground(Void... params) {

            JSONResponseHandler responseHandler = new JSONResponseHandler();
            AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

            try {
                ArrayList<Mention> local = mClient.execute(JsonRequest.request(mHref), responseHandler);

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
            TextView tv = (TextView) findViewById(R.id.internetalert);
            tv.setVisibility(View.INVISIBLE);

            if (mArrayOfList == null) {
                if (result == null) {

                    tv.setVisibility(View.VISIBLE);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            populateMentionsList();
                        }
                    });

                } else {
                    MentionAdapter adapter = new MentionAdapter(getApplicationContext(), result);
                    ListView listView = (ListView) findViewById(R.id.mention);
                    Utils.insertFooter(getApplicationContext(), listView);
                    listView.setAdapter(adapter);
                    loadMoreMentions();
                    mAdapter = adapter;
                    mArrayOfList = result;
                }

            } else {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar2);
                Button button = (Button) findViewById(R.id.button_reload);
                if (result == null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            populateMentionsList();
                        }
                    });
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);

                mArrayOfList.addAll(result);
                mAdapter.notifyDataSetChanged();
                mLoaded = true;
                }
            }
        }
    }

    class JSONResponseHandler implements ResponseHandler<ArrayList<Mention>> {

        private static final String TITLE_TAG = "title";
        private static final String SOURCE_TAG = "source_name";
        private static final String MENTIONS_TAG = "mentions";

        @Override
        public ArrayList<Mention> handleResponse(HttpResponse response)
                throws IOException {
            ArrayList<Mention> result = new ArrayList<Mention>();
            String JSONResponse = new BasicResponseHandler().handleResponse(response);
            try {

                JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();
                JSONArray mentions = responseObject.getJSONArray(MENTIONS_TAG);
                JSONObject links = responseObject.getJSONObject("_links");
                JSONObject more = links.getJSONObject("more");
                String href = "https://api.mention.net" + more.getString("href");
                mHref = href;

                for (int idx = 0; idx < mentions.length(); idx++) {

                    JSONObject mention = (JSONObject) mentions.get(idx);
                    result.add(new Mention(R.drawable.logo1, R.drawable.avatar1, mention.getString(SOURCE_TAG), idx, mention.getString(TITLE_TAG), Mention.State.UNREAD));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}