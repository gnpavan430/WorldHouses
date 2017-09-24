/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DbHelper;
import Fragments.CompleteCollectionFragment;
import Fragments.DuplicateCollectionFragment;
import Fragments.MyHousesFragment;
import Fragments.MissingCollectionFragment;

// COMPLETED (8) Implement GreenAdapter.ListItemClickListener from the MainActivity
public class MainActivity extends AppCompatActivity
        implements  SearchView.OnQueryTextListener {

    private static final int NUM_LIST_ITEMS = 100;
    public ArrayList<DataModel> dataArrayList = new ArrayList<DataModel>();
    private ArrayList<DataModel> filterResults=new ArrayList<DataModel>();
    ArrayList<DataModel> arrayList;
    DbHelper dbHelper;
    ViewPager mViewPager;
    TabsPageAdapter mTabsPageAdapter;
    TabLayout mTabLayout;




    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;
    SearchView searchView;
    MenuItem searchMenuItem;
    private boolean searchMenuEnableCheck=true;

    // COMPLETED (9) Create a Toast variable called mToast to store the current Toast
    /*
     * If we hold a reference to our Toast, we can cancel it (if it's showing)
     * to display a new Toast. If we didn't do this, Toasts would be delayed
     * in showing up if you clicked many list items in quick succession.
     */
    private Toast mToast;
    private Boolean toggleCheck=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        TextView title=(TextView)findViewById(getResources().getIdentifier("action_bar_title","id",getPackageName()));
        title.setText("World Houses");
        mTabsPageAdapter=new TabsPageAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout=(TabLayout)findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         *
         */
        /*dbHelper= new DbHelper(this);
        DataModel dataModel = new DataModel();
        dataArrayList.add(new DataModel("House 1","abc","def"));
        dataArrayList.add(new DataModel("House 2","abcd","efgh"));
        dataArrayList.add(new DataModel("House 1","abc","def"));
        dataArrayList.add(new DataModel("House 1","abc","def"));
        dataArrayList.add(new DataModel("House 1","abc","def"));
        dataArrayList.add(new DataModel("House 1","abc","def"));
        dataArrayList.add(new DataModel("House 1","abc","def"));


        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        *//*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         *//*
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        *//*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         *//*
        mNumbersList.setHasFixedSize(true);
        searchView=(SearchView)findViewById(R.id.search);

        // COMPLETED (13) Pass in this as the ListItemClickListener to the GreenAdapter constructor
        *//*
         * The GreenAdapter is responsible for displaying each item in the list.
         *//*
        //mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        mAdapter = new GreenAdapter(addObjectsToArrayList(dbHelper.getAllData()));
        Log.i("All data from database",""+addObjectsToArrayList(dbHelper.getAllData()).size());
        mNumbersList.setAdapter(mAdapter);*/
    }
    private void setupViewPager(ViewPager mViewPager) {
        TabsPageAdapter adapter= new TabsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CompleteCollectionFragment(), "Complete Collection");
        adapter.addFragment(new MissingCollectionFragment(), "Missing");
        adapter.addFragment(new DuplicateCollectionFragment(), "Duplicate");
        adapter.addFragment(new MyHousesFragment(),"Favorites");
        mViewPager.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        searchMenuItem = menu.findItem(R.id.search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*int itemId = item.getItemId();

        switch (itemId) {
            *//*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             *//*
            case R.id.action_refresh:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new GreenAdapter(dataArrayList);
                mNumbersList.setAdapter(mAdapter);
                return false;
            case R.id.toggle:
                supportInvalidateOptionsMenu();
                boolean isSwitched = mAdapter.toggleItemViewType();
                mNumbersList.setLayoutManager(isSwitched ? new LinearLayoutManager(this) : new GridLayoutManager(this, 3));
                mAdapter.notifyDataSetChanged();
                return false;
            case R.id.search:
                searchMenuEnableCheck=false;
                SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
                searchView = (SearchView) searchMenuItem.getActionView();

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setSubmitButtonEnabled(true);
                searchView.setOnQueryTextListener(this);
                return false;



        }*/

        return false;
    }

    // COMPLETED (10) Override ListItemClickListener's onListItemClick method
    /**
     * This is where we receive our callback from
    // * {@link //com.example.android.recyclerview.GreenAdapter.//ListItemClickListener}
     *
     * This callback is invoked when you click on an item in the list.
     *
    // * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    /*@Override
    public void onListItemClick(int clickedItemIndex) {
        // COMPLETED (11) In the beginning of the method, cancel the Toast if it isn't null
        *//*
         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
         * ensures that our new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         *
         * Comment out these three lines, run the app, and click on a bunch of
         * different items if you're not sure what I'm talking about.
         *//*
        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        *//*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         *//*
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }*/
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String constraint) {/*
        Log.i("Entered search query",constraint);
        ArrayList<DataModel> completeArrayList=new ArrayList<DataModel>();
        completeArrayList=addObjectsToArrayList(dbHelper.getAllData());
        if(constraint.length()==0){
            filterResults=completeArrayList;

        }else if(constraint!=null && constraint.length()>0){
            Log.i("Constraint is",""+constraint);

            ArrayList<DataModel> tempList = new ArrayList<DataModel>();
            //search content in list
            Log.i("data from database size",completeArrayList.size()+"");
            for(DataModel dataModel: completeArrayList){
                Log.i("filter name",dataModel.getName());
                String searchConstraint=constraint+"";
                if(dataModel.getName().contains(searchConstraint)){
                    tempList.add(dataModel);
                    Log.i("Temp list is",tempList+"");
                }

            }

            filterResults=tempList;



        }
        mAdapter=new GreenAdapter(filterResults);
        mNumbersList.setAdapter(mAdapter);

        return true;*/
    return false;
    }
}
