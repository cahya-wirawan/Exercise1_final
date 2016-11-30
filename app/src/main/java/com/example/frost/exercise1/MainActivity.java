package com.example.frost.exercise1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static android.R.attr.key;
import static com.example.frost.exercise1.Encryptor.decrypt;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getLastMessage();
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        byte[] decodedBytes;

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        Context context = getApplicationContext();
        CharSequence text = "Sending ...!";


        //create connection
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-web-api.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ShortMessageProvider smp = retrofit.create(ShortMessageProvider.class);
        SendShortMessageModel myModel = new SendShortMessageModel();

        String aesKey = getString(R.string.aesKey); // 128 bit key
        String aesIV = getString(R.string.aesIV); // 16 bytes IV

        // get base64 decoded consumerKey and consumerSecret from string.xml
        String consumerKey = getString(R.string.consumerKey);
        String consumerSecret = getString(R.string.consumerSecret);

        consumerKey = decrypt(aesKey, aesIV, consumerKey);
        consumerSecret = decrypt(aesKey, aesIV, consumerSecret);

        myModel.setConsumerSecret(consumerSecret);
        myModel.setConsumerKey(consumerKey);
        myModel.setText(message);
        Call<ShortMessageModel> call = smp.sendShortMessage(myModel);

        call.enqueue(new Callback<ShortMessageModel>() {
            @Override
            public void onResponse(Response<ShortMessageModel> response, Retrofit retrofit) {
                Toast toast = Toast.makeText(getApplicationContext(), "Sent succ", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Sent FAIL", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //call.enqueue(this);




        startActivity(intent);
    }

    public void getLastMessage(View view){
        byte[] decodedBytes;
        TextView tv = (TextView) findViewById(R.id.latest_message);

        //create connection
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simple-web-api.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // prepare call in Retrofit 2.0
        ShortMessageProvider smp = retrofit.create(ShortMessageProvider.class);


        String consumerKey = getString(R.string.consumerKey);

        decodedBytes = Base64.decode(consumerKey.getBytes(), Base64.DEFAULT);
        consumerKey = new String(decodedBytes);

        Call<ShortMessages> call = smp.getShortMessages(consumerKey);
        //call.enqueue();
        call.enqueue(new Callback<ShortMessages>() {
            @Override
            public void onResponse(Response<ShortMessages> response, Retrofit retrofit) {
                List<ShortMessageModel> res = new ArrayList<ShortMessageModel>(response.body().getMessages());
                try {
                    ShortMessageModel newest = res.get(0);
                    for (ShortMessageModel i : res) {
                        String k = "0000000000000000000000000";
                        String j = i.getCreated().replace("-", "").replace("T","").replace(":","");
                        for (int l = 0; l < 21; l++) {
                            if (Integer.parseInt(k.charAt(l) + "") == Integer.parseInt(j.charAt(l) + "")) {
                                continue;
                            } else if(Integer.parseInt(k.charAt(l) + "") > Integer.parseInt(j.charAt(l) + "")){
                                k = j;
                                newest = i;
                                break;
                            }else{
                                break;
                            }
                        }
                        TextView tv = (TextView) findViewById(R.id.latest_message);
                        tv.setText(newest.getText());
                    }
                }catch(Exception e){
                    int asdfg = 0;
                }
            }

            @Override
            public void onFailure(Throwable t) {
int i =0;
            }
        });


    }




}