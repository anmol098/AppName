package com.aapreneur.appname.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapreneur.appname.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MyArrayAdapter  extends ArrayAdapter < MyDataModel > {

    List < MyDataModel > modelList;
    Context context;
    private LayoutInflater mInflater;

    Locale INR = new Locale("en", "IN");
    NumberFormat inrFormat = NumberFormat.getCurrencyInstance(INR);

    // Constructors
    public MyArrayAdapter(Context context, List < MyDataModel > objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
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

        MyDataModel item = getItem(position);
        vh.textViewItem.setText(item.getItem());
        vh.textViewPrice.setText(String.valueOf(inrFormat.format(item.getPrice())));



        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewItem;
        public final TextView textViewPrice;



        private ViewHolder(RelativeLayout rootView, TextView textViewItem, TextView textViewPrice) {
            this.rootView = rootView;
            this.textViewPrice = textViewPrice;
            this.textViewItem = textViewItem;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewPrice = rootView.findViewById(R.id.price);
            TextView textViewItem = rootView.findViewById(R.id.item);

            return new ViewHolder(rootView, textViewItem,textViewPrice);
        }
    }
}
