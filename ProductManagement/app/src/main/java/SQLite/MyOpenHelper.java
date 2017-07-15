package SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import fragment.Fragment0;

public class MyOpenHelper extends SQLiteOpenHelper {
    final static String DB_NAME="merchandise.db";
    static final int DB_VERSION = 1;
    public final static  String SFTABLE_NAME = "sf_table";
    public final static  String BUYHISTORY_NAME = "buy_table";
    public final static  String SELLHISTORY_NAME = "sell_table";
    public final static  String ABANDONHISTORY_NAME = "abandon_table";

    
    protected SQLiteDatabase db;


	public MyOpenHelper(Context context) {
		super(context,DB_NAME, null, DB_VERSION);	 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//sell fragment table
		db.execSQL(                "CREATE TABLE " + SFTABLE_NAME + "("+
                "   Name TEXT NOT NULL, "+
                "   Qty TEXT NOT NULL"+
                
        "   "+");");
		//buy history table
		db.execSQL(                "CREATE TABLE " + BUYHISTORY_NAME + "("+
                "   BName TEXT NOT NULL, "+
                "   BQty TEXT NOT NULL,"+
                "   BPrice TEXT NOT NULL,"+
                "   BTime TEXT NOT NULL"+
                
        "   "+");");
		//sell history table
		db.execSQL(                "CREATE TABLE " + SELLHISTORY_NAME + "("+
                "   SName TEXT NOT NULL, "+
                "   SQty TEXT NOT NULL,"+
                "   SPrice TEXT NOT NULL,"+
                "   STime TEXT NOT NULL"+
                
        "   "+");");
		//abandon history table
		db.execSQL(                "CREATE TABLE " + ABANDONHISTORY_NAME + "("+
                "   AName TEXT NOT NULL, "+
                "   AQty TEXT NOT NULL,"+
                "   ATime TEXT NOT NULL"+
                
        "   "+");");
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SFTABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + BUYHISTORY_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + SELLHISTORY_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ABANDONHISTORY_NAME + ";");
        onCreate(db);
		// TODO Auto-generated method stub
		
	}

}