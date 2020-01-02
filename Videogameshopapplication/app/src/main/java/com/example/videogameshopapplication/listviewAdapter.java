package com.example.videogameshopapplication;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.videogameshopapplication.Data.VideoGame;

import java.util.ArrayList;

public class listviewAdapter extends BaseAdapter {

        public ArrayList<VideoGame> productList;
        Activity activity;

        public listviewAdapter(Activity activity, ArrayList<VideoGame> productList) {
            super();
            this.activity = activity;
            this.productList = productList;
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int position) {
            return productList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView mID;
            TextView mName;
            TextView mPublisher;
            TextView mPlatform;
            TextView mPrice;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            LayoutInflater inflater = activity.getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listview_row, null);
                holder = new ViewHolder();
                holder.mID = (TextView) convertView.findViewById(R.id.tv_id);
                holder.mName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.mPublisher = (TextView) convertView.findViewById(R.id.tv_publisher);
                holder.mPlatform = (TextView) convertView.findViewById(R.id.tv_platform);
                holder.mPrice = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            VideoGame item = productList.get(position);
            holder.mID.setText(Long.toString(item.getId()));
            holder.mName.setText(item.getName());
            holder.mPublisher.setText(item.getPublisher());
            holder.mPlatform.setText(item.getPlatform());
            holder.mPrice.setText(Float.toString(item.getPrice()));

            return convertView;
        }

        public void swapItems(ArrayList<VideoGame> items){
            this.productList = items;
            notifyDataSetChanged();
        }
    }


