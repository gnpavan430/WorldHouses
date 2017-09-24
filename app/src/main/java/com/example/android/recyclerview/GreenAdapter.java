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

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DbHelper;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter GreenAdapter because its
 * contents are green.
 */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder>  {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    static DataModel dataModel = new DataModel();

    // COMPLETED (3) Create a final private ListItemClickListener called mOnClickListener
    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    //final private ListItemClickListener mOnClickListener;

    /*
     * The number of ViewHolders that have been created. Typically, you can figure out how many
     * there should be by determining how many list items fit on your screen at once and add 2 to 4
     * to that number. That isn't the exact formula, but will give you an idea of how many
     * ViewHolders have been created to display any given RecyclerView.
     *
     * Here's some ASCII art to hopefully help you understand:
     *
     *    ViewHolders on screen:
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 0 |
     *        *-----------------------------*
     *        |         ViewHolder index: 1 |
     *        *-----------------------------*
     *        |         ViewHolder index: 2 |
     *        *-----------------------------*
     *        |         ViewHolder index: 3 |
     *        *-----------------------------*
     *        |         ViewHolder index: 4 |
     *        *-----------------------------*
     *        |         ViewHolder index: 5 |
     *        *-----------------------------*
     *        |         ViewHolder index: 6 |
     *        *-----------------------------*
     *        |         ViewHolder index: 7 |
     *        *-----------------------------*
     *
     *    Extra ViewHolders (off screen)
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 8 |
     *        *-----------------------------*
     *        |         ViewHolder index: 9 |
     *        *-----------------------------*
     *        |         ViewHolder index: 10|
     *        *-----------------------------*
     *        |         ViewHolder index: 11|
     *        *-----------------------------*
     *
     *    Total number of ViewHolders = 11
     */
    private static int viewHolderCount;

    private int mNumberItems;


    private ArrayList<DataModel> dataModelArrayList= new ArrayList<DataModel>();
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    public static boolean isSwitchView = true;



    // COMPLETED (1) Add an interface called ListItemClickListener
    // COMPLETED (2) Within that interface, define a void method called onListItemClick that takes an int as a parameter
    /**
     * The interface that receives onClick messages.
     */
   /* public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }*/

    // COMPLETED (4) Add a ListItemClickListener as a parameter to the constructor and store it in mOnClickListener
    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *  @param numberOfItems Number of items to display in list
    // * @param listener Listener for list item clicks
     */
    public GreenAdapter(ArrayList<DataModel> numberOfItems) {
        dataModelArrayList = numberOfItems;

        //mOnClickListener = listener;
        viewHolderCount = 0;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View itemView;
        int layoutIdForListItem = R.layout.number_list_item;
        if(viewType==LIST_ITEM){
            itemView= LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.number_list_item, null);
        }
        else{
           itemView= LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.number_grid_item, null);
        }

       /* LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;


        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);*/
        NumberViewHolder viewHolder = new NumberViewHolder(itemView);

//        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        //viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
       //holder.bind(position);
        //holder.dataTextView.setText(dataModelArrayList.get(position).getName());
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



    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available
     */
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

    // COMPLETED (5) Implement OnClickListener in the NumberViewHolder class
    /**
     * Cache of the children views for a list item.
     */
    static class NumberViewHolder extends RecyclerView.ViewHolder
         {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        // Will display which ViewHolder is displaying this data
        TextView viewHolderIndex,dataTextView,descriptionTextView,cityTextView;
             ImageButton imageButton;
             DbHelper dbHelper;
           static String name,surname,latid,longid,city;



        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(final View itemView) {
            super(itemView);

            //listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
           // viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            dataTextView = (TextView) itemView.findViewById(R.id.textView);
            descriptionTextView=(TextView)itemView.findViewById(R.id.descriptionTextView);
            cityTextView=(TextView)itemView.findViewById(R.id.cityTextView);
            imageButton=(ImageButton)itemView.findViewById(R.id.moreoptionsimagebutton);
            dbHelper= new DbHelper(itemView.getContext());
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("View is clicked","view clicked is"+getAdapterPosition());
                    Intent intent= new Intent(view.getContext(),HouseDetailsActivity.class);
                    intent.putExtra("Extra name",getAdapterPosition());
                    view.getContext().startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(view.getContext(),"View long pressed",Toast.LENGTH_LONG).show();
                    return true;
                }

            });
            imageButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(view.getContext(),imageButton);
                    popupMenu.inflate(R.menu.popupoptions);
                    Log.i("adapter position",""+getAdapterPosition());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Cursor res=dbHelper.getSpecificData(getAdapterPosition()+1);
                            while(res.moveToNext()){
                                name=res.getString(1);
                                surname=res.getString(2);
                                latid=res.getString(3);
                                longid=res.getString(4);
                                city=res.getString(5);
                            }
                            switch(item.getItemId()){



                                case R.id.myhouses:
                                    //handle menu1 click
                                    dbHelper.insertData(dbHelper.MyHouses_Table,name,surname,latid,longid,city);

                                    break;
                                case R.id.duplicates:
                                    //handle menu2 click
                                    dbHelper.insertData(dbHelper.Dupliates_Table,name,surname,latid,longid,city);
                                    break;
                                case R.id.favorites:
                                    //handle menu3 click
                                    dbHelper.insertData(dbHelper.Favorites_Table,name,surname,latid,longid,city);
                                    break;

                            }
                            return false;
                        }
                    });
                    popupMenu.show();

                }
            });
        }


        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(NumberViewHolder holder,int listIndex) {
           // listItemNumberView.setText(String.valueOf(listIndex));
           // dataTextView.setText(dataModel.getName());
            holder.dataTextView.setText(dataModel.getName());
        }


        // COMPLETED (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        /*@Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }*/

    }
}
