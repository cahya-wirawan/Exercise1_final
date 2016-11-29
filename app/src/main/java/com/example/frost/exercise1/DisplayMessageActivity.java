package com.example.frost.exercise1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class DisplayMessageActivity extends AppCompatActivity implements Callback<ShortMessages> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        Context context = getApplicationContext();
        CharSequence text = "Sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

        sendMessage(message);
    }

    public void sendMessage(String message){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-web-api.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        ShortMessageProvider smp = retrofit.create(ShortMessageProvider.class);

        // prepare message model for the call
        SendShortMessageModel ssmm = new SendShortMessageModel();
        ssmm.setText(message);
        ssmm.setConsumerKey("ebehausvo");
        ssmm.setConsumerSecret("ebehausvo");

        Call<ShortMessageModel> call = smp.sendShortMessage(ssmm);

    }

    @Override
    public void onResponse(Response<ShortMessages> response, Retrofit retrofit) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
