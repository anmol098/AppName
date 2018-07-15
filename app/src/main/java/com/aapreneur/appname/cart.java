package com.aapreneur.appname;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.aapreneur.appname.resources.Cart;
import com.aapreneur.appname.resources.Item;
import com.aapreneur.appname.resources.MyDataModel;

public class cart extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button buttonContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tableLayout = findViewById(R.id.tableLayoutProduct);
        buttonContinueShopping = findViewById(R.id.buttonContinueShopping);

        buttonContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart.this, restaurant_menu.class);
                startActivity(intent);
            }
        });

        addCart();
        createColumns();
        fillData();
    }

    private void addCart() {
        try {
            Intent intent = getIntent();
            MyDataModel product = (MyDataModel) intent.getSerializableExtra("product");
            if (!Cart.isExists(product)) {
                Cart.insert(new Item(product, 1));
            } else {
                Cart.update(product);
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


        TextView textViewId = new TextView(this);
        textViewId.setText("id");
        textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewId.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewId);


        TextView textViewName = new TextView(this);
        textViewName.setText("Name");
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);


        TextView textViewPrice = new TextView(this);
        textViewPrice.setText("Price");
        textViewPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewPrice.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewPrice);


        TextView textViewQuantity = new TextView(this);
        textViewQuantity.setText("Quantity");
        textViewQuantity.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewQuantity.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewQuantity);


        TextView textViewSubTotal = new TextView(this);
        textViewSubTotal.setText("Sub Total");
        textViewSubTotal.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewSubTotal.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewSubTotal);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

    }

    private void fillData() {
        try {
            for (Item item : Cart.contents()) {

                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


                TextView textViewId = new TextView(this);
                textViewId.setText(String.valueOf(item.getProduct().getId()));
                textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textViewId.setPadding(5, 5, 5, 0);
                tableRow.addView(textViewId);


                TextView textViewName = new TextView(this);
                textViewName.setText(item.getProduct().getItem());
                textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textViewName.setPadding(5, 5, 5, 0);
                tableRow.addView(textViewName);


                TextView textViewPrice = new TextView(this);
                textViewPrice.setText(String.valueOf(item.getProduct().getPrice()));
                textViewPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textViewPrice.setPadding(5, 5, 5, 0);
                tableRow.addView(textViewPrice);


                TextView textViewQuantity = new TextView(this);
                textViewQuantity.setText(String.valueOf(item.getQuantity()));
                textViewQuantity.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textViewQuantity.setPadding(5, 5, 5, 0);
                tableRow.addView(textViewQuantity);


                TextView textViewSubTotal = new TextView(this);
                textViewSubTotal.setText(String.valueOf(item.getProduct().getPrice() * item.getQuantity()));
                textViewSubTotal.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textViewSubTotal.setPadding(5, 5, 5, 0);
                tableRow.addView(textViewSubTotal);

                tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
            }

            TableRow tableRowTotal = new TableRow(this);
            tableRowTotal.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


            TextView textViewTotal = new TextView(this);
            textViewTotal.setText("Total");
            textViewTotal.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewTotal.setPadding(5, 5, 5, 0);
            tableRowTotal.addView(textViewTotal);


            TextView textViewTotalValue = new TextView(this);
            textViewTotalValue.setText(String.valueOf(Cart.total()));
            textViewTotalValue.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewTotalValue.setPadding(5, 5, 5, 0);
            tableRowTotal.addView(textViewTotalValue);

            tableLayout.addView(tableRowTotal, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}
