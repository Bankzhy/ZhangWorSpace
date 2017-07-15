package SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SellHistoryOpenHelper extends SQLiteOpenHelper{
	   static final String DB_NAME = "SellHistory.db";
	    static final int DB_VERSION = 1;
	    public static final String TABLE_NAME = "SellHistory";
	    protected SQLiteDatabase db;


		public SellHistoryOpenHelper(Context context) {
			super(context,DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(                "CREATE TABLE " + TABLE_NAME + "("+
	                "   Name TEXT NOT NULL, "+
	                "   Qty TEXT NOT NULL,"+
	                "   Price TEXT NOT NULL"+
	                "   Time TEXT NOT NULL" +
	        "   "+");");
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
	        onCreate(db);
			// TODO Auto-generated method stub
			
		}


}
