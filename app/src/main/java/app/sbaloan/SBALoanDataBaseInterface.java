package app.sbaloan;

import java.util.List;

import android.content.Context;

public class SBALoanDataBaseInterface {
	private static SBALoanDataBase _db;

	private SBALoanDataBaseInterface() {

	}

	private static void setUpDb(Context context) {
		_db = new SBALoanDataBase(context);
	}

	public static List<LoanGrantDto> getEntries(Context context) {
		if (_db == null)
			setUpDb(context);

		return _db.getEntries();
	}

	public static int NumberOfEntries(Context context) {
		if (_db == null)
			setUpDb(context);

		return _db.NumberOfEntries();
	}

	public static void AddLoan(String title, String industry, String origJSON, Context context) {
		if (_db == null)
			setUpDb(context);

		_db.AddLoan(title, industry, origJSON);
	}

	public static void DeleteLoan(String title, Context context) {
		if (_db == null)
			setUpDb(context);

		_db.DeleteLoan(title);
	}

	public static boolean LoanIsSaved(String title, Context context) {
		if (_db == null)
			setUpDb(context);

		return _db.LoanIsSaved(title);
	}
}
