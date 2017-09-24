package Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.recyclerview.DataModel;
import com.example.android.recyclerview.GreenAdapter;
import com.example.android.recyclerview.R;
import com.example.android.recyclerview.TabsPageAdapter;

import java.util.ArrayList;


import Database.DbHelper;


/**
 * Created by x085271 on 8/22/2017.
 */

public class CompleteCollectionFragment extends Fragment implements SearchView.OnQueryTextListener {
    public ArrayList<DataModel> dataArrayList = new ArrayList<DataModel>();
    private ArrayList<DataModel> filterResults=new ArrayList<DataModel>();
    ArrayList<DataModel> arrayList;
    DbHelper dbHelper;

    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;
    SearchView searchView;
    MenuItem searchMenuItem;
    private boolean searchMenuEnableCheck=true;
    private Boolean toggleCheck=true;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.completecollectionfragment,container,false);
        dbHelper= new DbHelper(getContext());
        mNumbersList = (RecyclerView) view.findViewById(R.id.rv_numbers);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mNumbersList.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);
        searchView=(SearchView)view.findViewById(R.id.search);

        // COMPLETED (13) Pass in this as the ListItemClickListener to the GreenAdapter constructor
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        //mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        mAdapter = new GreenAdapter(addObjectsToArrayList(dbHelper.getAllData(dbHelper.Table_Name)));
        Log.i("All data from database",""+addObjectsToArrayList(dbHelper.getAllData(dbHelper.Table_Name)).size());
        mNumbersList.setAdapter(mAdapter);


        return view;
    }
    public  ArrayList<DataModel> addObjectsToArrayList(Cursor cursor){
        arrayList=new ArrayList<DataModel>();
        while(cursor.moveToNext()){
            Log.i("Values in Cursor are",cursor.getString(1));
            DataModel dataModel=new DataModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            arrayList.add(dataModel);
        }
        return arrayList;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        searchMenuItem = menu.findItem(R.id.search);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.action_refresh:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new GreenAdapter(dataArrayList);
                mNumbersList.setAdapter(mAdapter);
                item.setIcon(R.drawable.gridviewicon);
                return true;
            case R.id.toggle:
                getActivity().supportInvalidateOptionsMenu();
                boolean isSwitched = mAdapter.toggleItemViewType();


                    //item.setIcon(R.drawable.searchicon);

              /*  if(isSwitched){
                    isSwitched=false;
                    item.setIcon(R.drawable.gridviewicon);
                    mNumbersList.setLayoutManager(new LinearLayoutManager(getContext()));

                }
                else{
                    isSwitched=true;
                    item.setIcon(R.drawable.listviewicon);
                    mNumbersList.setLayoutManager(new GridLayoutManager(getContext(),3));

                }*/
                setIcon(isSwitched,item);
               mNumbersList.setLayoutManager(isSwitched ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 3));
                mAdapter.notifyDataSetChanged();



                return true;
            case R.id.search:

                searchMenuEnableCheck=false;
                SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
                searchView = (SearchView) searchMenuItem.getActionView();

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                searchView.setSubmitButtonEnabled(true);
                searchView.setOnQueryTextListener(this);
                return true;



        }

        return false;
    }

    private void setIcon(boolean isSwitched, MenuItem item) {
        item.setIcon(R.drawable.searchicon);
        if(isSwitched){
            item.setIcon(R.drawable.listviewicon);
        }
        else{
            item.setIcon(R.drawable.gridviewicon);

        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String constraint) {

        Log.i("Entered search query",constraint);
        ArrayList<DataModel> completeArrayList=new ArrayList<DataModel>();
        completeArrayList=addObjectsToArrayList(dbHelper.getAllData(dbHelper.Table_Name));
        if(constraint.length()==0){
            filterResults=completeArrayList;

        }else if(constraint!=null && constraint.length()>0){
            Log.i("Constraint is",""+constraint);

            ArrayList<DataModel> tempList = new ArrayList<DataModel>();
            //search content in list
            Log.i("data from database size",completeArrayList.size()+"");
            for(DataModel dataModel: completeArrayList){
                Log.i("filter name",dataModel.getName());
                String searchconstraint=constraint+"";
                String searchConstraint=searchconstraint.toUpperCase();
                Log.i("Search constraint is",searchConstraint);
                Log.i("data model cons",dataModel.getName());
                if(dataModel.getName().toString().toUpperCase().contains(searchConstraint)|| dataModel.getSurname().toUpperCase().contains(searchConstraint)){
                    tempList.add(dataModel);
                    Log.i("Temp list is",tempList+"");
                }

            }


            filterResults=tempList;



        }
        mAdapter=new GreenAdapter(filterResults);
        mNumbersList.setAdapter(mAdapter);

        return true;
    }
}