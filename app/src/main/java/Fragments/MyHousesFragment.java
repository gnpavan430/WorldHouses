package Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.recyclerview.DataModel;
import com.example.android.recyclerview.MyHousesAdapter;
import com.example.android.recyclerview.R;

import java.util.ArrayList;

import Database.DbHelper;

/**
 * Created by x085271 on 8/22/2017.
 */

public class MyHousesFragment extends Fragment {
    RecyclerView recyclerView;
    MyHousesAdapter myHousesAdapter;
    DbHelper dbHelper;
    ArrayList<DataModel> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myhousescollectionfragment,container,false);
        dbHelper= new DbHelper(getContext());
        recyclerView=(RecyclerView)view.findViewById(R.id.myhousesrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        myHousesAdapter= new MyHousesAdapter(addObjectsToArrayList(dbHelper.getAllData(dbHelper.MyHouses_Table)));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public ArrayList<DataModel> addObjectsToArrayList(Cursor cursor){
        arrayList=new ArrayList<DataModel>();
        while(cursor.moveToNext()){
            Log.i("Values in Cursor are",cursor.getString(1));
            DataModel dataModel=new DataModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            arrayList.add(dataModel);
        }
        return arrayList;

    }


}
