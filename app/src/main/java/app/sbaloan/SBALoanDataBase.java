package app.sbaloan;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SBALoanDataBase {

	private static final String TITLE = "title";
	private static final String JSON = "json";
	private static final String INDUSTRY = "industy";// need to save this separately because there is a but in the API.

	private static final String TABLE_NAME = "loans";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "loan_grant.db";

	/*
	 * private static final String AGENCY = "agency"; private static final String DESCRIP = "description"; private static final String INDUSTRY = "industry";
	 * private static final String LOAN_TYPE = "loan_type"; private static final String STATE = "state"; private static final String GOV_TYPE = "gov_type";
	 * private static final String URL = "url"; private static final String GEN_PUR = "gen_pur"; private static final String DEV = "dev"; private static final
	 * String EXPORT = "exporting"; private static final String CONTRACT = "contractor"; private static final String GOV_TYPE = "gov_type"; private static final
	 * String URL = "url"; boolean is_general_purpose; boolean is_development; boolean is_exporting; boolean is_contractor; boolean is_green; boolean
	 * is_military; boolean is_minority; boolean is_woman; boolean is_disabled; boolean is_rural; boolean is_disaster;
	 */
	private SQLiteDatabase db;
	private final Context context;
	private Helper myDbHelper;

	// constructor create the wrapper to open and close the db
	public SBALoanDataBase(Context cxt) {
		context = cxt;
		myDbHelper = new Helper(context);
		db = myDbHelper.getWritableDatabase();
	}

	public List<LoanGrantDto> getEntries() {
		List<LoanGrantDto> list = new ArrayList<LoanGrantDto>();

		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

		int iNumOfEntries = c.getCount();
		c.moveToFirst();
		for (int i = 0; i < iNumOfEntries; i++) {
			int columnJSON = c.getColumnIndex(JSON);
			int columnIndustry = c.getColumnIndex(INDUSTRY);

			try {
				LoanGrantDto loan = new LoanGrantDto(new JSONObject(c.getString(columnJSON)));
				loan.setIndustry(c.getString(columnIndustry));
				list.add(loan);
			} catch (Exception e) {
				Log.e("could not build json object from db", e.getMessage());
			}

			c.moveToNext();
		}
		return list;
	}

	public int NumberOfEntries() {
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

		return c.getCount();
	}

	public void AddLoan(String title, String industry, String origJSON) {
		ContentValues cv = new ContentValues();
		cv.put(TITLE, title);
		cv.put(JSON, origJSON);
		cv.put(INDUSTRY, industry);
		db.insert(TABLE_NAME, null, cv);

		// db.rawQuery("INSERT INTO " + TABLE_NAME + "	VALUES ('" + title + "','" + origJSON + "','" + industry + "')", null);
	}

	public void DeleteLoan(String title) {
		String deletequery[] = {title};
		String whereClause = TITLE + "=?";
		db.delete(TABLE_NAME, whereClause, deletequery);
		
		//db.rawQuery("DELETE FROM " + TABLE_NAME + " WHERE " + TITLE + " = '" + title + "';", null);
	}

	public boolean LoanIsSaved(String title) {
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TITLE + " = '" + title + "';", null);

		return c.getCount() > 0;
	}

	class Helper extends SQLiteOpenHelper {

		public Helper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public Helper(Context context, String name, CursorFactory factory, int version) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public SQLiteDatabase getDb(Context context) {
			return getWritableDatabase();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME + "(" + TITLE + " text primary key, " + JSON + " text, " + INDUSTRY + " text" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}
