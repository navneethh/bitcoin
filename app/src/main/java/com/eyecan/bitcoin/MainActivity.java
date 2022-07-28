package com.eyecan.bitcoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    BitRecyclerAdapter adapter;
    TextView textView;
    String nameall="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String producturl ="https://api.wazirx.com/sapi/v1/tickers/24hr";
        recyclerView = findViewById(R.id.recyler);
         ArrayList<Coins> coinlist = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, producturl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray= response;
                            for( int i=0 ; i<jsonArray.length();i++){
                                JSONObject coin = jsonArray.getJSONObject(i);
                                String name = coin.getString("baseAsset");
                                String symbol = coin.getString("symbol");
                                String price = coin.getString("lastPrice");

                               Coins coins = new Coins(name,symbol,price);
                               coinlist.add(coins);
                            }
                            adapter.notifyDataSetChanged();
                            
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getBaseContext(),"Error found",Toast.LENGTH_LONG).show();
                        Log.e("Volley111",error.toString());

                    }
                });
        adapter = new BitRecyclerAdapter(coinlist);
        requestQueue.add(jsonArrayRequest);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }
}