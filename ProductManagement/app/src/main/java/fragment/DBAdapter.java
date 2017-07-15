package fragment;

import java.util.ArrayList;
import java.util.Date;

import SQLite.MyOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	 MyOpenHelper helper;
	 SQLiteDatabase db;
	 
	 public DBAdapter(Context context){
		 helper=new MyOpenHelper(context);
		 db=helper.getWritableDatabase();
	 }
	 
	 public Cursor getAllList(String TABLE_NAME){
		 switch(TABLE_NAME){
		 case "SFTABLE_NAME":
			 return db.query(MyOpenHelper.SFTABLE_NAME, null, null, null, null, null, null);
		 case "BUYHISTORY_NAME":
			 return db.query(MyOpenHelper.BUYHISTORY_NAME, null, null, null, null, null, null);
		 case "SELLHISTORY_NAME":
			 return db.query(MyOpenHelper.SELLHISTORY_NAME, null, null, null, null, null, null);
		 default :
			 return db.query(MyOpenHelper.ABANDONHISTORY_NAME, null, null, null, null, null, null);
		 }

	 }
	 
	 public void sfinsert(String name,String qty){
	      ContentValues values = new ContentValues();
	      values.put("Name", name);
	      values.put("Qty", qty);
	      db.insertOrThrow(MyOpenHelper.SFTABLE_NAME, null, values);
	 }
	 
	 
	 public void buyinsert(String name,String qty,String price){
		  Date dateNow = new Date ();
	      ContentValues values = new ContentValues();
	      values.put("BName", name);
	      values.put("BQty", qty);
	      values.put("BPrice", price);
	      values.put("BTime", dateNow.toString());
	      db.insertOrThrow(MyOpenHelper.BUYHISTORY_NAME, null, values);
	 }
	 
	 public void sellinsert(String name,String qty,String price){
		  Date dateNow = new Date ();
	      ContentValues values = new ContentValues();
	      values.put("SName", name);
	      values.put("SQty", qty);
	      values.put("SPrice", price);
	      values.put("STime", dateNow.toString());
	      db.insertOrThrow(MyOpenHelper.SELLHISTORY_NAME, null, values);
	 }
	 
	 public void abandoninsert(String name,String qty){
		  Date dateNow = new Date ();
	      ContentValues values = new ContentValues();
	      values.put("AName", name);
	      values.put("AQty", qty);
	      values.put("ATime", dateNow.toString());
	      db.insertOrThrow(MyOpenHelper.ABANDONHISTORY_NAME, null, values);
	 }
	 public DBAdapter open(){
		 db=helper.getReadableDatabase();
		 return this;
	 }
	 public void close(){
		 helper.close();
	 }
	 public boolean checkName(String checkname){
		 open();
		 Cursor c=db.query(MyOpenHelper.SFTABLE_NAME, null, null, null, null, null, null);
		 ArrayList<String> name=new ArrayList<String>();
		 if(c.moveToFirst()){
			 do{
				 name.add(c.getString(c.getColumnIndex("Name")));
			 }while(c.moveToNext());				
		 }
		 return name.contains(checkname);
	 }
	 public void addQty(String name,int insertq){
		 String sql="SELECT Qty FROM sf_table WHERE Name LIKE '%' || ? || '%'";
		 Cursor c=db.rawQuery(sql, new String[]{name});
		 c.moveToFirst();
		 String qty1=c.getString(0);
		 int q=new Integer(qty1).intValue();
		 int comq=q+insertq;
		 String comqty=Integer.toString(comq);
				 
		 ContentValues updateValue=new ContentValues();
		 updateValue.put("Qty",comqty);
		 db.update(MyOpenHelper.SFTABLE_NAME, updateValue, "Name=?", new String[]{name});	 
	 }
	 public void decreaseQty(String name,int insertq){
		 String sql="SELECT Qty FROM sf_table WHERE Name LIKE '%' || ? || '%'";
		 Cursor c=db.rawQuery(sql, new String[]{name});
		 c.moveToFirst();
		 String qty1=c.getString(0);
		 int q=new Integer(qty1).intValue();
		 int comq=q-insertq;
		 if(comq>0){
			 String comqty=Integer.toString(comq);
			 
			 ContentValues updateValue=new ContentValues();
			 updateValue.put("Qty",comqty);
			 db.update(MyOpenHelper.SFTABLE_NAME, updateValue, "Name=?", new String[]{name});	
		 }else{
			 deleterecord("SFTABLE_NAME", name,null);
		 }
		
	 }
	 public synchronized void deleterecord(String TABLE_NAME,String columname,String time){
		 if(TABLE_NAME.equals("SFTABLE_NAME")){
			 db.delete(MyOpenHelper.SFTABLE_NAME,"Name=?", new String[]{columname});
			 db.close();
		 }else if(TABLE_NAME.equals("BUYHISTORY_NAME")){
			 db.delete(MyOpenHelper.BUYHISTORY_NAME,"BName=? AND BTime=?", new String[]{columname,time});
			 db.close();
		 }else if(TABLE_NAME.equals("SELLHISTORY_NAME")){
			 db.delete(MyOpenHelper.SELLHISTORY_NAME,"SName=?", new String[]{columname});
			 db.close();
		 }else if(TABLE_NAME.equals("ABANDONHISTORY_NAME")){
			 db.delete(MyOpenHelper.ABANDONHISTORY_NAME,"AName=?", new String[]{columname});
			 db.close();
		 }

		 
	 }

}
