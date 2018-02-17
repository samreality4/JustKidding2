package com.udacity.gradle.builtitbigger.free;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private InterstitialAd interstitialAd;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        newInterstitial();
    }


    private void newInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void tellJoke(View view) {
        progressBar.setVisibility(View.VISIBLE);
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }else{
            Toast.makeText(mContext, "error ad", Toast.LENGTH_SHORT);
        }
        interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    newInterstitial();
                    progressBar.setVisibility(View.VISIBLE);
                    new EndpointAsyncTask(mContext).execute(new Pair<>(mContext, "jokefromcloud"));

                }
            });

        }


    //make the progressbar invisible when another activity comes to the foreground.
    @Override
    public void onPause() {
        super.onPause();
        progressBar.setVisibility(View.GONE);
    }
}












