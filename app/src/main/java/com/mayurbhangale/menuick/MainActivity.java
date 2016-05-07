package com.mayurbhangale.menuick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventsAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
                if (position == 0) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/amrapali.html"); //geoSK@N
                } else if (position == 1) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/annapurna.html"); //BusinessClub
                } else if (position == 2) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/omsai.html"); //NFS
                } else if (position == 3) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/shreeganesh.html"); // C Venture
                } else if (position == 4) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/durgaprasad.html"); // C Venture
                } else if (position == 5) {
                    webView.loadUrl("http://mayurbhangale.bitbucket.org/kamla.html"); // C Venture
                }
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        String comp = new String("Department of Computer Engineering");
        String mech = new String("Department of Mechanical Engineering");
        String elec = new String("Department of Electrical Engineering");
        String it = new String("Department of Information Technology");
        String entc = new String("Department of Electronics & Telecommunication Engineering");

        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 1; index++) {
            DataObject obj6 = new DataObject("Kamla Mess","");
            results.add(index, obj6);
            DataObject obj = new DataObject("Durgaprasad Mess", "");
            results.add(index, obj);
            DataObject obj1 = new DataObject("Shree Ganesh Mess","");
            results.add(index, obj1);
            DataObject obj2 = new DataObject("Om Sai Mess","");
            results.add(index, obj2);
            DataObject obj3 = new DataObject("Annapurna Mess","");
            results.add(index, obj3);
            DataObject obj4 = new DataObject("Sahdev Mess","");
            results.add(index, obj4);
            DataObject obj5 = new DataObject("Amrapali Mess","");
            results.add(index, obj5);
        }
        return results;
    }
}
