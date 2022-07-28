package com.eyecan.bitcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class coindetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coindetail);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String url ="https://api.wazirx.com/sapi/v1/ticker/24hr?symbol=";
        String symbol = intent.getStringExtra("symbol");
        url= url+symbol;

        TextView nameview= findViewById(R.id.textnamee);
        TextView symbolview= findViewById(R.id.textsymbol);
        TextView lastpriceview= findViewById(R.id.last_price);
        TextView openpricevoew= findViewById(R.id.textsopenprice);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    TextView volumeview= findViewById(R.id.volumetext);
                    TextView askview= findViewById(R.id.askprice);
                    TextView bidview= findViewById(R.id.bidprice);
                    String name = response.getString("baseAsset");
                    String symbol = response.getString("symbol");
                    String lastprice = response.getString("lastPrice");
                    String openprice = response.getString("openPrice");


                    nameview.setText(name);
                    symbolview.setText(symbol);
                    lastpriceview.setText(lastprice);
                    openpricevoew.setText(openprice);
                    volumeview.setText(response.getString("volume"));
                    askview.setText(response.getString("askPrice"));
                    bidview.setText(response.getString("bidPrice"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}