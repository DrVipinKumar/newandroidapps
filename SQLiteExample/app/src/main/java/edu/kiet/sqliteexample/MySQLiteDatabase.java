package edu.kiet.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MySQLiteDatabase extends SQLiteOpenHelper {
    Context context;
    public static final String dbname="mydb";
    public static final int version=1;
    public static final String tblName="Student";
    public static final String col1="roll_no";
    public static final String col2="name";
    public static final String col3="course";
    String query="create table "+tblName+" ( "+col1+" integer, "+col2+" text, "+col3+" text )";
    public MySQLiteDatabase(Context context) {
        super(context, dbname, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
                db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              db.execSQL("drop table if exists "+tblName);
              onCreate(db);
    }
    long insertData(int roll_no, String name, String course)
    {
        ContentValues cv=new ContentValues();
        cv.put(col1,roll_no);
        cv.put(col2,name);
        cv.put(col3,course);
        SQLiteDatabase db=this.getWritableDatabase();
        long check=db.insert(tblName,null,cv);
        return check;
    }
    Cursor getDisplay()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor data=db.rawQuery("select * from "+tblName,null);
        return data;
    }
    long updateData(int roll_no,String name, String course)
    {
        ContentValues cv=new ContentValues();
        cv.put(col1,roll_no);
        cv.put(col2,name);
        cv.put(col3,course);
        SQLiteDatabase db =this.getWritableDatabase();
        long check=db.update(tblName,cv,col1+"=?",new String[]{""+roll_no});
        return check;
    }
    long deleteData(int roll_no)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long check=db.delete(tblName,col1+"=?",new String[]{""+roll_no});
        return check;
    }
}
