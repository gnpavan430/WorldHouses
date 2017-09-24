package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;

/**
 * Created by x085271 on 8/23/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String Database_Name="Work.db";
    public static final String Table_Name="WorkTable";
    public static final String MyHouses_Table="MyHousesTable";
    public static final String Dupliates_Table="DuplicatesTable";
    public static final String Favorites_Table="FavoritesTable";

    DbHelper dbHelper;
    private static final String ID="_ID";
    private static final String NAME="name";
    //private static final String TABLENAME="ImageTable";
    private static final String DESCRIPTION="Surname";
    private static final String CITY="Marks";
    private static final String LAT_ID="LATID";
    private static final String LONG_ID="LONGID";
    //private static final String ID="_ID";
    private static final String Image= "IMAGE";
    String[] columns = new String[]{ID,NAME,DESCRIPTION,LAT_ID,LONG_ID,CITY};
   // private static final String createQuery="Create Table"+ Table_Name + "(" + col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Image +" BLOB "+ col2+ " TEXT,"+col3 +" Text, "+ col4+ " Text "+")";
    public DbHelper(Context context) {
        super(context, Database_Name, null, 1);



    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // sqLiteDatabase.execSQL("Create table"+"("+Table_Name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT,MARKS Integer"+")");
        sqLiteDatabase.execSQL(" CREATE TABLE " + Table_Name + " (" +
                ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DESCRIPTION + " TEXT, "+
                LAT_ID + " TEXT, "+
                LONG_ID + " TEXT, "+
                CITY + " TEXT );");
        sqLiteDatabase.execSQL(" CREATE TABLE " + MyHouses_Table + " (" +
                ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DESCRIPTION + " TEXT, "+
                LAT_ID + " TEXT, "+
                LONG_ID + " TEXT, "+
                CITY + " TEXT );");
        sqLiteDatabase.execSQL(" CREATE TABLE " + Dupliates_Table + " (" +
                ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DESCRIPTION + " TEXT, "+
                LAT_ID + " TEXT, "+
                LONG_ID + " TEXT, "+
                CITY + " TEXT );");
        //sqLiteDatabase.execSQL(createQuery);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAllData(String table_Name){
        SQLiteDatabase db = getWritableDatabase();

        Cursor res=db.rawQuery("Select * from "+ table_Name,null);
        return res;


    }
    public Cursor getSpecificData(int number){
        SQLiteDatabase db = getWritableDatabase();
        String num=number+"";

        //Cursor res=db.rawQuery("Select * from "+Table_Name+"where"+col1=num,null);
        //db.rawQuery("SELECT * FROM "+ Table_Name +" WHERE "+ col2 +"= "+num,null);
        Cursor res= db.query(Table_Name,columns, ID + " = " + number ,null,null,null,null);


        return res;


    }
    public boolean insertData(String table_Name,String name, String surname, String latid,String longid,String city){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //contentValues.put(Image,image);
        contentValues.put(NAME,name);
        contentValues.put(DESCRIPTION,surname);
        contentValues.put(LAT_ID,latid);
        contentValues.put(LONG_ID,longid);
        contentValues.put(CITY,city);
       long result = 0;
        switch (table_Name){
            case Table_Name:
                result= db.insert(Table_Name,null,contentValues);
                break;
            case MyHouses_Table:
                result= db.insert(MyHouses_Table,null,contentValues);
                break;
            case Dupliates_Table:
                result=db.insert(Dupliates_Table,null,contentValues);
                break;
            case Favorites_Table:
                result=db.insert(Favorites_Table,null,contentValues);
                break;

        }
        if(result==-1){
            return false;

        }
        else{
            return true;
        }

    }
    public boolean updateData(String id,String name, String surname, String latid,String longid,String city){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(DESCRIPTION,surname);
        contentValues.put(LAT_ID,latid);
        contentValues.put(LONG_ID,longid);
        contentValues.put(CITY,city);
        db.update(Table_Name,contentValues,"id=?",new String[]{id,});
        return true;
    }
    public void deleteDataFromDatabase(String query){
        SQLiteDatabase database=getWritableDatabase();



    }




    
}
