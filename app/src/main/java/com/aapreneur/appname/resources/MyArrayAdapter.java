package com.aapreneur.appname.resources;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aapreneur.appname.R;
import com.aapreneur.appname.cart;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    private List<MyDataModel> modelList;
    private Context context;
    private LayoutInflater mInflater;

    Locale INR = new Locale("en", "IN");
    NumberFormat inrFormat = NumberFormat.getCurrencyInstance(INR);

    // Constructors
    public MyArrayAdapter(Context context, List < MyDataModel > objects) {
        super(context, R.layout.layout_row_view_category, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view_menu, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final MyDataModel item = modelList.get(position);
        vh.parentLayout.setVisibility(View.VISIBLE);
        vh.rootView.setVisibility(View.VISIBLE);
            vh.textViewItem.setText(item.getItem());
            vh.textViewPrice.setText(String.valueOf(inrFormat.format(item.getPrice())));
        vh.textViewDescription.setText(item.getDescription());

            if (item.getType())
                vh.imageViewType.setImageResource(R.drawable.ic_veg);
            else
                vh.imageViewType.setImageResource(R.drawable.ic_non_veg);
        if (item.getProductStatus()) {
            vh.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, cart.class);
                    intent.putExtra("product", item);
                    context.startActivity(intent);
                }
            });

        } else {
            vh.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Item Currently not Availavle", Toast.LENGTH_LONG).show();
                }
            });
        }



        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final RelativeLayout parentLayout;
        public final TextView textViewItem;
        public final TextView textViewPrice;
        public final TextView textViewDescription;
        public final ImageView imageViewType;
        public final Button buttonAdd;


        private ViewHolder(RelativeLayout rootView, RelativeLayout parentLayout, TextView textViewItem, TextView textViewPrice, ImageView imageViewType, Button buttonAdd, TextView textViewDescription) {
            this.rootView = rootView;
            this.parentLayout = parentLayout;
            this.textViewPrice = textViewPrice;
            this.textViewItem = textViewItem;
            this.imageViewType = imageViewType;
            this.buttonAdd = buttonAdd;
            this.textViewDescription = textViewDescription;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            RelativeLayout parentLayout = rootView.findViewById(R.id.parentLayout);
            TextView textViewPrice = rootView.findViewById(R.id.price);
            TextView textViewItem = rootView.findViewById(R.id.item);
            ImageView imageViewType = rootView.findViewById(R.id.foodType);
            Button buttonAdd = rootView.findViewById(R.id.add);
            TextView textViewDescription = rootView.findViewById(R.id.description);
            return new ViewHolder(rootView, parentLayout, textViewItem, textViewPrice, imageViewType, buttonAdd, textViewDescription);
        }
    }
}