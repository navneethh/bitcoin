package com.eyecan.bitcoin;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String nameall="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String producturl ="https://api.wazirx.com/sapi/v1/tickers/24hr";
        textView = findViewById(R.id.text);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, producturl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray= response;
                            for( int i=0 ; i<jsonArray.length();i++){
                                JSONObject coin = jsonArray.getJSONObject(i);
                                String coinname = coin.getString("symbol");
                                textView.setText(coinname);
                                nameall=nameall+coinname;
                            }
                            
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
        requestQueue.add(jsonArrayRequest);
        textView.setText(nameall);
    }
}