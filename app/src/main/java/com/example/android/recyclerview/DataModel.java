package com.example.android.recyclerview;

/**
 * Created by X085271 on 9/19/2017.
 */

public class DataModel {
    public  String name;
    public  String surname;
    public  String marks;
    DataModel(){

    }

    public  String getName() {
        return name;
    }

    public  String getSurname() {
        return surname;
    }

    public  String getMarks() {
        return marks;
    }


    public DataModel(String name,String surname,String marks){
        this.name=name;
        this.surname=surname;
        this.marks=marks;
    }
}