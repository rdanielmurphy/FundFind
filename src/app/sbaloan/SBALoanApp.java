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
	private Button executeBtn, searchBtn;
	private Spinner stateSpinner;
	private Spinner typeSpinner;
	private Spinner industrySpinner;
	private Spinner genPurposeSpinner;
	private Spinner developmentSpinner;
	private Spinner exportingSpinner;
	private Spinner contractorSpinner;
	private Spinner womenSpinner;
	private Spinner ruralSpinner;
	private Spinner militarySpinner;
	private Spinner greenSpinner;
	private Spinner disabledSpinner;
	private Spinner disasterSpinner;
	private Spinner minoritySpinner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		executeBtn = (Button) findViewById(R.id.executeBtn);
		executeBtn.setOnClickListener(this);
		searchBtn = (Button) findViewById(R.id.savedBtn);
		searchBtn.setOnClickListener(this);

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

		womenSpinner = (Spinner) findViewById(R.id.womenSpinner);
		populateSpinner(womenSpinner, SBALoanConstants.VALUES);

		ruralSpinner = (Spinner) findViewById(R.id.ruralSpinner);
		populateSpinner(ruralSpinner, SBALoanConstants.VALUES);

		militarySpinner = (Spinner) findViewById(R.id.militarySpinner);
		populateSpinner(militarySpinner, SBALoanConstants.VALUES);

		greenSpinner = (Spinner) findViewById(R.id.greenSpinner);
		populateSpinner(greenSpinner, SBALoanConstants.VALUES);

		disabledSpinner = (Spinner) findViewById(R.id.disabledSpinner);
		populateSpinner(disabledSpinner, SBALoanConstants.VALUES);

		disasterSpinner = (Spinner) findViewById(R.id.disasterSpinner);
		populateSpinner(disasterSpinner, SBALoanConstants.VALUES);

		minoritySpinner = (Spinner) findViewById(R.id.minoritySpinner);
		populateSpinner(minoritySpinner, SBALoanConstants.VALUES);
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
			if (getStateOfBox(womenSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("women", getStateOfBox(womenSpinner.getSelectedItem().toString()));
			if (getStateOfBox(ruralSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("rural", getStateOfBox(ruralSpinner.getSelectedItem().toString()));
			if (getStateOfBox(militarySpinner.getSelectedItem().toString()) != null)
				intent.putExtra("military", getStateOfBox(militarySpinner.getSelectedItem().toString()));
			if (getStateOfBox(greenSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("green", getStateOfBox(greenSpinner.getSelectedItem().toString()));
			if (getStateOfBox(disabledSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("disabled", getStateOfBox(disabledSpinner.getSelectedItem().toString()));
			if (getStateOfBox(disasterSpinner.getSelectedItem().toString()) != null)
				intent.putExtra("disaster", getStateOfBox(disasterSpinner.getSelectedItem().toString()));
			if (getStateOfBox(minoritySpinner.getSelectedItem().toString()) != null)
				intent.putExtra("minority", getStateOfBox(minoritySpinner.getSelectedItem().toString()));

			startActivity(intent);
		} else if (view.equals(searchBtn)) {
			Intent intent = new Intent(this, SBALoanSavedList.class);
			startActivity(intent);
		}
	}

}