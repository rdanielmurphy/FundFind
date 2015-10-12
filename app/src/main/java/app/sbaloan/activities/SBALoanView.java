package app.sbaloan.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import app.sbaloan.R;
import app.sbaloan.database.SBALoanDataBaseInterface;

public class SBALoanView extends Activity {
	private TextView txtViewTitle, txtViewAgency, txtViewUrl, txtViewDescr, txtViewType, txtViewIndustry, txtViewSpecialties, txtViewState;
	private String jsonString;
	private CheckBox chkBox;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loan_grant_view);

		txtViewTitle = (TextView) findViewById(R.id.txtViewTitle);
		txtViewAgency = (TextView) findViewById(R.id.txtViewAgency);
		txtViewIndustry = (TextView) findViewById(R.id.txtViewIndustry);
		txtViewDescr = (TextView) findViewById(R.id.txtViewDescription);
		txtViewSpecialties = (TextView) findViewById(R.id.txtViewSpecialties);
		txtViewType = (TextView) findViewById(R.id.txtViewType);
		txtViewState = (TextView) findViewById(R.id.txtViewState);
		txtViewUrl = (TextView) findViewById(R.id.txtViewUrl);
		chkBox = (CheckBox) findViewById(R.id.chkBoxSave);

		Bundle extras = getIntent().getExtras();
		txtViewTitle.setText(extras.getString("title"));
		txtViewAgency.setText(extras.getString("agency"));
		txtViewDescr.setText(extras.getString("descr"));
		txtViewType.setText(extras.getString("type"));
		txtViewIndustry.setText(extras.getString("industry"));
		txtViewSpecialties.setText(extras.getString("specialties"));
		txtViewState.setText(extras.getString("state"));
		txtViewUrl.setText(extras.getString("url"));
		jsonString = extras.getString("json");

		try {
			if (SBALoanDataBaseInterface.LoanIsSaved(txtViewTitle.getText().toString(), this.getApplicationContext()))
				chkBox.setChecked(true);
			else
				chkBox.setChecked(false);
		} catch (Exception e) {
			Toast.makeText(this.getApplicationContext(), "Could not connect to local database!", Toast.LENGTH_SHORT).show();
		}

		chkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					performSave();
				else
					performUnSave();
			}
		});
	}

	private void performSave() {
		try {
			SBALoanDataBaseInterface.AddLoan(txtViewTitle.getText().toString(), txtViewIndustry.getText().toString(), jsonString, this.getApplicationContext());
		} catch (Exception e) {
			Toast.makeText(this.getApplicationContext(), "Could not connect to local database!", Toast.LENGTH_SHORT).show();
		}
	}

	private void performUnSave() {
		try {
			SBALoanDataBaseInterface.DeleteLoan(txtViewTitle.getText().toString(), this.getApplicationContext());
		} catch (Exception e) {
			Toast.makeText(this.getApplicationContext(), "Could not connect to local database!", Toast.LENGTH_SHORT).show();
		}
	}
}
