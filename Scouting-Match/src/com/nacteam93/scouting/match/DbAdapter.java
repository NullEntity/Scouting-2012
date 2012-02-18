package com.nacteam93.scouting.match;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {
	// Name of the SQL file
	private static final String DATABASE_NAME = "data";
	
	// User Table
	private static final String DATABASE_USER_TABLE = "users";
	private static final int DATABASE_USER_VERSION = 1;
	
	// User Schema
	public static final String KEY_USER_ROWID = "_id";
	public static final String KEY_USER_USERNAME = "username";
	public static final String KEY_USER_PASSWORD = "password";
	public static final String KEY_USER_ACCESS_LEVEL = "access";
	// Note: All passwords in the DB are hashed and salted
	/*?? WHY ?? IF USERS ARE USING OTHER PEOPLES PASSWORDS WE HAVE MORE SERIOUS ISSUES ON THIS TEAM. FOR SIMPLICITY'S SAKE I SAY NO - MBB
	 * WHY DO WE EVEN NEED TO WORRY ABOUT PASSWORDS...MORE IMPORTANT THINGS FIRST!!!
	 * die.
	 */
	
	/*private static String CREATE_USER_TABLE = 
			"CREATE TABLE " + DATABASE_USER_TABLE + " ("
				+ KEY_USER_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_USER_USERNAME + " TEXT NOT NULL, "
				+ KEY_USER_PASSWORD + " TEXT NOT NULL, "
				+ KEY_USER_ACCESS_LEVEL + " INTEGER NOT NULL);";*/
	
	// Match Data Table
	private static final String DATABASE_MATCH_DATA_TABLE = "match_data";
	private static final int DATABASE_MATCH_DATA_VERSION = 1;
	
	//Test Data Table
	private static final String DATABASE_TEST_DATA_TABLE = "test_data";
	private static final int DATABASE_TEST_VERSION = 1;
	
	// Match Data Schema
	public static final String KEY_MATCH_DATA_ROWID = "_id";
	public static final String KEY_MATCH_DATA_DAY	= "day";
	public static final String KEY_MATCH_DATA_TEAM	= "team";
	public static final String KEY_MATCH_DATA_MATCH = "match";
	
	public static final String KEY_MATCH_DATA_ZONE_01_HIGH 	= "zone_01_high";
	public static final String KEY_MATCH_DATA_ZONE_01_MID 	= "zone_01_mid";
	public static final String KEY_MATCH_DATA_ZONE_01_LOW 	= "zone_01_low";
	public static final String KEY_MATCH_DATA_ZONE_01_MISS	= "zone_01_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_02_HIGH	= "zone_02_high";
	public static final String KEY_MATCH_DATA_ZONE_02_MID	= "zone_02_mid";
	public static final String KEY_MATCH_DATA_ZONE_02_LOW	= "zone_02_low";
	public static final String KEY_MATCH_DATA_ZONE_02_MISS	= "zone_02_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_03_HIGH 	= "zone_03_high";
	public static final String KEY_MATCH_DATA_ZONE_03_MID 	= "zone_03_mid";
	public static final String KEY_MATCH_DATA_ZONE_03_LOW 	= "zone_03_low";
	public static final String KEY_MATCH_DATA_ZONE_03_MISS	= "zone_03_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_04_HIGH	= "zone_04_high";
	public static final String KEY_MATCH_DATA_ZONE_04_MID	= "zone_04_mid";
	public static final String KEY_MATCH_DATA_ZONE_04_LOW	= "zone_04_low";
	public static final String KEY_MATCH_DATA_ZONE_04_MISS	= "zone_04_miss";

	public static final String KEY_MATCH_DATA_ZONE_05_HIGH 	= "zone_05_high";
	public static final String KEY_MATCH_DATA_ZONE_05_MID 	= "zone_05_mid";
	public static final String KEY_MATCH_DATA_ZONE_05_LOW 	= "zone_05_low";
	public static final String KEY_MATCH_DATA_ZONE_05_MISS	= "zone_05_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_06_HIGH	= "zone_06_high";
	public static final String KEY_MATCH_DATA_ZONE_06_MID	= "zone_06_mid";
	public static final String KEY_MATCH_DATA_ZONE_06_LOW	= "zone_06_low";
	public static final String KEY_MATCH_DATA_ZONE_06_MISS	= "zone_06_miss";

	public static final String KEY_MATCH_DATA_ZONE_07_HIGH 	= "zone_07_high";
	public static final String KEY_MATCH_DATA_ZONE_07_MID 	= "zone_07_mid";
	public static final String KEY_MATCH_DATA_ZONE_07_LOW 	= "zone_07_low";
	public static final String KEY_MATCH_DATA_ZONE_07_MISS	= "zone_07_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_08_HIGH	= "zone_08_high";
	public static final String KEY_MATCH_DATA_ZONE_08_MID	= "zone_08_mid";
	public static final String KEY_MATCH_DATA_ZONE_08_LOW	= "zone_08_low";
	public static final String KEY_MATCH_DATA_ZONE_08_MISS	= "zone_08_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_09_HIGH 	= "zone_09_high";
	public static final String KEY_MATCH_DATA_ZONE_09_MID 	= "zone_09_mid";
	public static final String KEY_MATCH_DATA_ZONE_09_LOW 	= "zone_09_low";
	public static final String KEY_MATCH_DATA_ZONE_09_MISS	= "zone_09_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_10_HIGH 	= "zone_10_high";
	public static final String KEY_MATCH_DATA_ZONE_10_MID 	= "zone_10_mid";
	public static final String KEY_MATCH_DATA_ZONE_10_LOW 	= "zone_10_low";
	public static final String KEY_MATCH_DATA_ZONE_10_MISS	= "zone_10_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_11_HIGH 	= "zone_11_high";
	public static final String KEY_MATCH_DATA_ZONE_11_MID 	= "zone_11_mid";
	public static final String KEY_MATCH_DATA_ZONE_11_LOW 	= "zone_11_low";
	public static final String KEY_MATCH_DATA_ZONE_11_MISS	= "zone_11_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_12_HIGH 	= "zone_12_high";
	public static final String KEY_MATCH_DATA_ZONE_12_MID 	= "zone_12_mid";
	public static final String KEY_MATCH_DATA_ZONE_12_LOW 	= "zone_12_low";
	public static final String KEY_MATCH_DATA_ZONE_12_MISS	= "zone_12_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_13_HIGH 	= "zone_13_high";
	public static final String KEY_MATCH_DATA_ZONE_13_MID 	= "zone_13_mid";
	public static final String KEY_MATCH_DATA_ZONE_13_LOW 	= "zone_13_low";
	public static final String KEY_MATCH_DATA_ZONE_13_MISS	= "zone_13_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_14_HIGH 	= "zone_14_high";
	public static final String KEY_MATCH_DATA_ZONE_14_MID 	= "zone_14_mid";
	public static final String KEY_MATCH_DATA_ZONE_14_LOW 	= "zone_14_low";
	public static final String KEY_MATCH_DATA_ZONE_14_MISS	= "zone_14_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_15_HIGH 	= "zone_15_high";
	public static final String KEY_MATCH_DATA_ZONE_15_MID 	= "zone_15_mid";
	public static final String KEY_MATCH_DATA_ZONE_15_LOW 	= "zone_15_low";
	public static final String KEY_MATCH_DATA_ZONE_15_MISS	= "zone_15_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_16_HIGH 	= "zone_16_high";
	public static final String KEY_MATCH_DATA_ZONE_16_MID 	= "zone_16_mid";
	public static final String KEY_MATCH_DATA_ZONE_16_LOW 	= "zone_16_low";
	public static final String KEY_MATCH_DATA_ZONE_16_MISS	= "zone_16_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_17_HIGH 	= "zone_17_high";
	public static final String KEY_MATCH_DATA_ZONE_17_MID 	= "zone_17_mid";
	public static final String KEY_MATCH_DATA_ZONE_17_LOW 	= "zone_17_low";
	public static final String KEY_MATCH_DATA_ZONE_17_MISS	= "zone_17_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_18_HIGH 	= "zone_18_high";
	public static final String KEY_MATCH_DATA_ZONE_18_MID 	= "zone_18_mid";
	public static final String KEY_MATCH_DATA_ZONE_18_LOW 	= "zone_18_low";
	public static final String KEY_MATCH_DATA_ZONE_18_MISS	= "zone_18_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_19_HIGH 	= "zone_19_high";
	public static final String KEY_MATCH_DATA_ZONE_19_MID 	= "zone_19_mid";
	public static final String KEY_MATCH_DATA_ZONE_19_LOW 	= "zone_19_low";
	public static final String KEY_MATCH_DATA_ZONE_19_MISS	= "zone_19_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_20_HIGH 	= "zone_20_high";
	public static final String KEY_MATCH_DATA_ZONE_20_MID 	= "zone_20_mid";
	public static final String KEY_MATCH_DATA_ZONE_20_LOW 	= "zone_20_low";
	public static final String KEY_MATCH_DATA_ZONE_20_MISS	= "zone_20_miss";
	
	public static final String KEY_MATCH_DATA_ZONE_21_HIGH 	= "zone_21_high";
	public static final String KEY_MATCH_DATA_ZONE_21_MID 	= "zone_21_mid";
	public static final String KEY_MATCH_DATA_ZONE_21_LOW 	= "zone_21_low";
	public static final String KEY_MATCH_DATA_ZONE_21_MISS	= "zone_21_miss";
	
	public static final String KEY_TIME = "time";
	public static final String KEY_ZONE = "zone";
	public static final String KEY_HOOP = "target";
	public static final String KEY_TEST_ROWID = "_id";
			
	private static final String CREATE_TEST_TABLE =
			"CREATE TABLE " + DATABASE_TEST_DATA_TABLE + " ("
							+ KEY_TEST_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
							+ KEY_ZONE + " INTEGER NOT NULL, "	
							+ KEY_HOOP + " INTEGER NOT NULL" + ");";
	
	/*private static final String CREATE_MATCH_DATA_TABLE =
			"CREATE TABLE " + DATABASE_MATCH_DATA_TABLE + " ("
				+ KEY_MATCH_DATA_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_MATCH_DATA_DAY + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_TEAM + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_MATCH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_01_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_01_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_01_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_01_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_02_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_02_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_02_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_02_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_03_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_03_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_03_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_03_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_04_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_04_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_04_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_04_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_05_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_05_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_05_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_05_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_06_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_06_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_06_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_06_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_07_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_07_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_07_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_07_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_08_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_08_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_08_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_08_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_09_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_09_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_09_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_09_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_10_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_10_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_10_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_10_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_11_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_11_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_11_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_11_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_12_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_12_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_12_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_12_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_13_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_13_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_13_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_13_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_14_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_14_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_14_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_14_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_15_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_15_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_15_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_15_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_16_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_16_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_16_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_16_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_17_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_17_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_17_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_17_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_18_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_18_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_18_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_18_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_19_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_19_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_19_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_19_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_20_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_20_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_20_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_20_MISS + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_21_HIGH + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_21_MID + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_21_LOW + " INTEGER NOT NULL, "
				+ KEY_MATCH_DATA_ZONE_21_MISS + " INTEGER NOT NULL);";

	// Match Schedule
	private static final String DATABASE_MATCH_SCHEDULE_TABLE = "match_schedule";
	private static final int DATABASE_MATCH_SCHEDULE_VERSION = 1;
	
	// Match Schedule Schema
	public static final String KEY_MATCH_SCHEDULE_ROWID = "_id";
	public static final String KEY_MATCH_SCHEDULE_TIME	= "time";
	public static final String KEY_MATCH_SCHEDULE_DAY	= "day";
	public static final String KEY_MATCH_SCHEDULE_RED_1 = "red_1";
	public static final String KEY_MATCH_SCHEDULE_RED_2 = "red_2";
	public static final String KEY_MATCH_SCHEDULE_RED_3 = "red_3";
	public static final String KEY_MATCH_SCHEDULE_BLU_1 = "blu_1";
	public static final String KEY_MATCH_SCHEDULE_BLU_2 = "blu_2";
	public static final String KEY_MATCH_SCHEDULE_BLU_3 = "blu_3";
	
	private static final String CREATE_MATCH_SCHEDULE_TABLE =
			"CREATE TABLE " + DATABASE_MATCH_SCHEDULE_TABLE + " ("
				+ KEY_MATCH_SCHEDULE_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_MATCH_SCHEDULE_TIME + " TEXT NOT NULL, "
				+ KEY_MATCH_SCHEDULE_DAY + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_RED_1 + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_RED_2 + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_RED_3 + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_BLU_1 + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_BLU_2 + " INTEGER NOT NULL, "
				+ KEY_MATCH_SCHEDULE_BLU_3 + " INTEGER NOT NULL);";*/
	
	private DatabaseHelper m_DbHelper;
	private SQLiteDatabase m_Db;
	
	private final Context m_Context;
		
	public DbAdapter(Context ctx) {
		this.m_Context = ctx;
	}
	
	public DbAdapter open() throws android.database.SQLException {
		m_DbHelper = new DatabaseHelper(m_Context);
		m_Db = m_DbHelper.getWritableDatabase();
		Log.d("DB-SM", "DatabaseHelper created.");
		return this;
	}
	
	public void close() {
		m_DbHelper.close();
		Log.d("DB-SM", "DatabaseHelper closed.");
	}
	
	public long createScore(int section, int hoop){
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_ZONE, section);
		initialValues.put(KEY_HOOP, hoop);
		
		Log.d("DB-SM", "initialValues ready to be inserted to Database.");
		
		return m_Db.insert(DATABASE_TEST_DATA_TABLE, null, initialValues);
	}
	
	public boolean updateScore(long rowId, int section, int hoop){
		ContentValues args = new ContentValues();
		
		args.put(KEY_ZONE, section);
		args.put(KEY_HOOP, hoop);
		
		Log.d("DB-SM", "args ready to be updated to Database.");
		
		return m_Db.update(DATABASE_TEST_DATA_TABLE, args, KEY_TEST_ROWID + "=" + rowId, null) > 0;
	}
	
	public Cursor fetchAllScores() {
		Log.d("DB-SM", "All scores are being fetched.");
		return m_Db.query(DATABASE_TEST_DATA_TABLE, new String[] {KEY_TEST_ROWID, KEY_ZONE}, null, null, null, 
			null, null);
	}
	
	public Cursor fetchScore(long rowId) throws SQLException{
		Cursor mCursor = m_Db.query(true, DATABASE_TEST_DATA_TABLE, new String[] {KEY_TEST_ROWID, KEY_ZONE}, 
			KEY_USER_ROWID + "=" + rowId, null, null, null, null, null);
		
		if (mCursor != null){
			mCursor.moveToFirst();
		}
		Log.d("DB-SM", "Score (Row " + Long.toString(rowId) + ") has been fetched.");
		return mCursor;
	}
	
	public class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_TEST_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TEST_TABLE);
			Log.d("DB-SM", "Test Database created.");
			//db.execSQL(CREATE_USER_TABLE);		
			//db.execSQL(CREATE_MATCH_SCHEDULE_TABLE);
			//db.execSQL(CREATE_MATCH_DATA_TABLE);
		}

		@Override
		/*NEED TO FIX. THE ENTIRE POINT OF AN UPGRADE FUNCTION IS TO SAVE DATA WHEN A 
		 * DATABASE FORMAT CHANGES - MBB(non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// We should warn the user that that data is being lost.
			db.execSQL("DROP TABLE IF EXISTS " 
							+ DATABASE_TEST_DATA_TABLE //+ ", "
							//+ DATABASE_USER_TABLE + ", "
							//+ DATABASE_MATCH_SCHEDULE_TABLE + ", "
							//+ DATABASE_MATCH_DATA_TABLE 
							+ ";"
						);
			
			// But we won't.
			db.execSQL(CREATE_TEST_TABLE);
			//db.execSQL(CREATE_USER_TABLE);
			//db.execSQL(CREATE_MATCH_SCHEDULE_TABLE);
			//db.execSQL(CREATE_MATCH_DATA_TABLE);
			
			// Should Do: Warn user that all data will be deleted
			// Will Do: Nada.
			Log.d("DB-SM", "Database upgraded.");
		}
	}
}