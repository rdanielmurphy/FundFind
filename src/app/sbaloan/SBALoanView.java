package app.sbaloan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SBALoanView extends Activity {
	private TextView txtViewTitle, txtViewAgency, txtViewUrl, txtViewDescr, txtViewType, txtViewIndustry, txtViewSpecialties, txtViewState;

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

		Bundle extras = getIntent().getExtras();
		txtViewTitle.setText(extras.getString("title"));
		txtViewAgency.setText(extras.getString("agency"));
		txtViewDescr.setText(extras.getString("descr"));
		txtViewType.setText(extras.getString("type"));
		txtViewIndustry.setText(extras.getString("industry"));
		txtViewSpecialties.setText(extras.getString("specialties"));
		txtViewState.setText(extras.getString("state"));
		txtViewUrl.setText(extras.getString("url"));
	}
}
