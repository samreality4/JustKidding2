package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.androidjoker.TheAndroidJoker;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by sam on 1/26/18.
 */

public class EndpointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String>{

    private static MyApi myApi = null;
    private Context context;


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request)
                                throws IOException {request.setDisableGZipContent(true);

                        }
                    });

            myApi = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try{
            return myApi.joking().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
        Intent intent = new Intent(context, TheAndroidJoker.class);
        intent.putExtra("jokefromcloud", result);
        context.startActivity(intent);



        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
