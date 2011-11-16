package app.sbaloan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SBALoanApp extends Activity implements View.OnClickListener {
	private Button executeBtn;
	private Spinner stateSpinner;
	private Spinner typeSpinner;
	private Spinner industrySpinner;
	private Spinner genPurposeSpinner;
	private Spinner developmentSpinner;
	private Spinner exportingSpinner;
	private Spinner contractorSpinner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		executeBtn = (Button) findViewById(R.id.executeBtn);
		executeBtn.setOnClickListener(this);

		stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
		List<String> stateOptions = new ArrayList<String>();
		stateOptions.add("Federal");
		stateOptions.addAll(SBALoanConstants.STATES);
		populateSpinner(stateSpinner, stateOptions);

		typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
		populateSpinner(typeSpinner, addAllOption(SBALoanConstants.TYPES));

		industrySpinner = (Spinner) findViewById(R.id.industrySpinner);
		populateSpinner(industrySpinner, addAllOption(SBALoanConstants.INDUSTRIES));

		genPurposeSpinner = (Spinner) findViewById(R.id.genPurposeSpinner);
		populateSpinner(genPurposeSpinner, SBALoanConstants.VALUES);

		developmentSpinner = (Spinner) findViewById(R.id.developmentSpinner);
		populateSpinner(developmentSpinner, SBALoanConstants.VALUES);

		exportingSpinner = (Spinner) findViewById(R.id.exportingSpinner);
		populateSpinner(exportingSpinner, SBALoanConstants.VALUES);

		contractorSpinner = (Spinner) findViewById(R.id.contractorSpinner);
		populateSpinner(contractorSpinner, SBALoanConstants.VALUES);
	}

	private void populateSpinner(Spinner s, List<String> strings) {
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for (String string : strings)
			adapter.add(string);

		s.setAdapter(adapter);
	}

	private List<String> addAllOption(List<String> list) {
		List<String> newList = new ArrayList<String>();
		newList.add("All");
		newList.addAll(list);

		return newList;
	}

	/**
	 * Don't care means return null, else Yes = true and No = false
	 * 
	 * @param option
	 * @return
	 */
	private Boolean getStateOfBox(String option) {
		if (option.equals("Don't Care"))
			return null;

		if (option.equals("Yes"))
			return true;

		return false;
	}

	public void onClick(View view) {
		if (view.equals(executeBtn)) {
			Intent intent = new Intent(this, SBALoanList.class);

			intent.putExtra("gov_type", stateSpinner.getSelectedItem().toString().equals("Federal") ? "Federal" : "State");
			intent.putExtra("state", stateSpinner.getSelectedItem().toString());
			intent.putExtra("type", typeSpinner.getSelectedItem().toString().equals("All") ? null : typeSpinner.getSelectedItem().toString());
			intent.putExtra("industry", industrySpinner.getSelectedItem().toString().equals("All") ? null : industrySpinner.getSelectedItem().toString());
			if (getStateOfBox(genPurposeSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("genPurpose", getStateOfBox(genPurposeSpinner.getSelectedItem().toString()));
			if (getStateOfBox(developmentSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("development", getStateOfBox(developmentSpinner.getSelectedItem().toString()));
			if (getStateOfBox(exportingSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("exporting", getStateOfBox(exportingSpinner.getSelectedItem().toString()));
			if (getStateOfBox(contractorSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("contractor", getStateOfBox(contractorSpinner.getSelectedItem().toString()));

			startActivity(intent);
		}
	}

}