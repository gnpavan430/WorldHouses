package com.example.android.recyclerview;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by X085271 on 9/19/2017.
 */

class CustomFilter extends Filter {
    GreenAdapter greenAdapter;
    ArrayList<DataModel> filterList;
    public CustomFilter(ArrayList<DataModel> filterList,GreenAdapter adapter)
    {
        this.greenAdapter=adapter;
        this.filterList=filterList;
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<DataModel>filteredData= new ArrayList<>();
            for(int i=0;i<filterList.size();i++){
                if(filterList.get(i).getName().toUpperCase().contains(constraint)){
                    filteredData.add(filterList.get(i));
                }
            }
            results.count=filteredData.size();
            results.values=filteredData;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults filterResults) {
        //greenAdapter.dataModel= (ArrayList<DataModel>) filterResults.values;
        greenAdapter.notifyDataSetChanged();

    }
}
