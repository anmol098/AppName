package com.aapreneur.appname;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private static String url = "http://www.aapreneur.com/JSON/123456.php";
    private String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    String barcode;




    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        list = new ArrayList<>();

        adapter = new MyArrayAdapter(this, list);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

       /* barcode = getIntent().getStringExtra("table");

        // close the activity in case of empty barcode
        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(getApplicationContext(), "Barcode is empty!", Toast.LENGTH_LONG).show();
            finish();
        } else {


            new GetContacts().execute();
        }*/
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
                    Log.e("price", "price" + jsonObj.getString("table"));
                    JSONArray items = jsonObj.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject c = items.getJSONObject(i);
                        MyDataModel model = new MyDataModel();
                        int id = c.getInt("id");
                        String category = c.getString("category");
                        boolean type = c.getBoolean("isVeg");
                        String item = c.getString("item");
                        String description = c.getString("description");
                        boolean productStatus = c.getBoolean("isActive");
                        double price = c.getDouble("price");

                        Log.e("item", "item " + item);
                        Log.e("price", "price" + price);
                        Log.e("catagory", "cataagory " + category);

                        model.setItem(item);
                        model.setId(id);
                        model.setPrice(price);
                        model.setType(type);
                        model.setDescription(description);
                        model.setProductStatus(productStatus);

                        list.add(model);
                    }
                    JSONArray categories = jsonObj.getJSONArray("categories");
                    for (int j = 0; j < categories.length(); j++) {
                        //JSONObject d = categories.getJSONObject(j);
                        MyDataModel model = new MyDataModel();
                        model.setCategory(categories.getString(j));
                        Log.e("catagory", "cataagory " + categories.getString(j));
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
