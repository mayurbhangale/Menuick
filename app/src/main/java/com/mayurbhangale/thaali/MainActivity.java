package com.mayurbhangale.thaali;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new EventsAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        loadAds();
    }

    void loadAds(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        try {
            mAdView.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((EventsAdapter) mAdapter).setOnItemClickListener(new EventsAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                setContentView(R.layout.form_activity);
                WebView webView = (WebView) findViewById(R.id.webview);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                progress = (ProgressBar) findViewById(R.id.progressBar);
                progress.setVisibility(View.GONE);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        progress.setVisibility(View.GONE);
                        progress.setProgress(100);
                        super.onPageFinished(view, url);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        progress.setVisibility(View.VISIBLE);
                        progress.setProgress(0);
                        super.onPageStarted(view, url, favicon);
                    }

                });
                if (position == 0) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/amrapali.html");        //amrapali
                } else if (position == 1) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/annapurna.html");       //annapurna
                } else if (position == 2) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/omsai.html");           //omsai
                } else if (position == 3) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/shreeganesh.html");     // shreeganesh
                } else if (position == 4) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/durgaprasad.html");     // durgaprasad
                } else if (position == 5) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/kamla.html");           // kamla
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MainActivity.this,MainActivity.class);
        super.onBackPressed();
    }

    private ArrayList<DataObject> getDataSet() {

        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 1; index++) {
            DataObject obj6 = new DataObject("Kamla Mess");
            results.add(index, obj6);
            DataObject obj = new DataObject("Durgaprasad Mess");
            results.add(index, obj);
            DataObject obj1 = new DataObject("Shree Ganesh Mess");
            results.add(index, obj1);
            DataObject obj2 = new DataObject("Om Sai Mess");
            results.add(index, obj2);
            DataObject obj3 = new DataObject("Annapurna Mess");
            results.add(index, obj3);
            DataObject obj4 = new DataObject("Sahdev Mess");
            results.add(index, obj4);
            DataObject obj5 = new DataObject("Amrapali Mess");
            results.add(index, obj5);
        }
        return results;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.home)
        {
            MainActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
