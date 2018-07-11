package com.aapreneur.appname;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.aapreneur.appname.resources.HttpHandler;
import com.aapreneur.appname.resources.MyArrayAdapter;
import com.aapreneur.appname.resources.MyDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class restaurant_menu extends AppCompatActivity {
    private static String url = "http://www.aapreneur.com/JSON/";
    private String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;




    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        list = new ArrayList<>();

        adapter = new MyArrayAdapter(this, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        new GetContacts().execute();
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(restaurant_menu.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray menu = jsonObj.getJSONArray("menu");
                    for(int i =0; i < menu.length();i++){
                        JSONObject c = menu.getJSONObject(i);
                        JSONArray catagory = c.getJSONArray("catagory"+(i+1));
                        for(int j = 0;j<catagory.length();j++){
                            MyDataModel model = new MyDataModel();
                            JSONObject fooditem = catagory.getJSONObject(j);
                            String Catagory = fooditem.getString("catagory_name");
                            String id = fooditem.getString("id");
                            String item = fooditem.getString("item");
                            double price =Double.parseDouble(fooditem.getString("price"));
                            Log.e("ID", "ID " + id);
                            Log.e("item", "item " + item);
                            Log.e("price", "price" + price);

                            model.setItem(item);
                            model.setPrice(price);

                            list.add(model);




                        }




                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if(list.size() > 0) {
                adapter.notifyDataSetChanged();
            }

        }

    }
}
