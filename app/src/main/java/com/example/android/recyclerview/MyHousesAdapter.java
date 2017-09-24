package com.example.android.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Database.DbHelper;

/**
 * Crepublic MyHousesAdapter(ArrayList<DataModel> ) {
    }ated by x085271 on 9/23/2017.
 */

public class MyHousesAdapter extends RecyclerView.Adapter<MyHousesAdapter.MyHousesViewHolder> {
    private ArrayList<DataModel> dataModelArrayList= new ArrayList<DataModel>();
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    public static boolean isSwitchView = true;
    public MyHousesAdapter(ArrayList<DataModel> numberOfItems) {
        dataModelArrayList = numberOfItems;

    }


    @Override
    public MyHousesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View itemView;
        int layoutIdForListItem = R.layout.number_list_item;
        if(viewType==LIST_ITEM){
            itemView= LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.number_list_item, null);
        }
        else{
            itemView= LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.number_grid_item, null);
        }
        MyHousesViewHolder myHousesViewHolder= new MyHousesViewHolder(itemView);
        return myHousesViewHolder;
    }

    @Override
    public void onBindViewHolder(MyHousesViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case LIST_ITEM:
                holder.dataTextView.setText(dataModelArrayList.get(position).getName());
                holder.descriptionTextView.setText(dataModelArrayList.get(position).getSurname());
                holder.cityTextView.setText(dataModelArrayList.get(position).getMarks());
                break;
            case GRID_ITEM:
                holder.dataTextView.setText(dataModelArrayList.get(position).getName());
                break;


        }

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (isSwitchView){
            return LIST_ITEM;
        }else{
            return GRID_ITEM;
        }
    }
    public boolean toggleItemViewType () {
        isSwitchView = !isSwitchView;
        return isSwitchView;
    }

    public class MyHousesViewHolder extends RecyclerView.ViewHolder {
        TextView viewHolderIndex,dataTextView,descriptionTextView,cityTextView;
        DbHelper dbHelper;
        public MyHousesViewHolder(View itemView) {
            super(itemView);
            dataTextView = (TextView) itemView.findViewById(R.id.textView);
            descriptionTextView=(TextView)itemView.findViewById(R.id.descriptionTextView);
            cityTextView=(TextView)itemView.findViewById(R.id.cityTextView);
            dbHelper= new DbHelper(itemView.getContext());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("View is clicked","view clicked is"+getAdapterPosition());
                    Intent intent= new Intent(view.getContext(),HouseDetailsActivity.class);
                    intent.putExtra("Extra name",getAdapterPosition());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
