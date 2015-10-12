package app.sbaloan.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.sbaloan.R;
import app.sbaloan.api.SBALoanDataInterface;
import app.sbaloan.models.LoanGrantDto;
import app.sbaloan.models.SearchDto;

public class SBALoanList extends ListActivity {
	private List<LoanGrantDto> _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loans_grants_list);

		// create searchdto based on information
		final SearchDto searchDto = getIntent().getExtras().getParcelable(Main.SEARCH_DTO);

		final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar1);

		// Start lengthy operation in a background thread
		new Thread(new Runnable() {
			public void run() {
				try {
					progress.setVisibility(ProgressBar.VISIBLE);

					_list = new ArrayList<LoanGrantDto>();
					Map<String, LoanGrantDto> map = null;
					try {
						map = SBALoanDataInterface.getInstance().search(searchDto, searchDto.getStateName());
					} catch (Exception e) {
						throw new Exception("Error in retrieving data.");
					}
					for (Map.Entry<String, LoanGrantDto> entry : map.entrySet())
						_list.add(entry.getValue());

					final ListAdapter adapter = new LoanGrantListAdapter(SBALoanList.this, _list);

					runOnUiThread(new Runnable() {
						public void run() {
							try {
								setListAdapter(adapter);
								progress.setVisibility(ProgressBar.GONE);
							} catch (Exception e1) {
								Toast.makeText(getApplicationContext(), "Error...could not generate search. See log.", Toast.LENGTH_LONG).show();
								Log.e("search exception", e1.getMessage());
							}
						}
					});
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error...could not generate search. See log.", Toast.LENGTH_LONG).show();
					Log.e("search exception", e.getMessage());
				} finally {

				}
			}
		}).start();
	}

    @Override
    public void onDestroy() {
        super.onDestroy();

        finish();
    }

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Intent intent = new Intent(this, SBALoanView.class);
		intent.putExtra("title", _list.get(position).getTitle());
		intent.putExtra("agency", _list.get(position).getAgency());
		intent.putExtra("descr", _list.get(position).getDescription());
		intent.putExtra("type", _list.get(position).getLoanType());
		intent.putExtra("industry", _list.get(position).getIndustry());
		intent.putExtra("state", _list.get(position).getStateName());
		intent.putExtra("specialties", getSpecialtyString(_list.get(position)));
		intent.putExtra("url", _list.get(position).getUrl());
		intent.putExtra("json", _list.get(position).getOrigJSON());
		startActivity(intent);
	}

	private String getSpecialtyString(LoanGrantDto dto) {
		StringBuilder sb = new StringBuilder();

		if (dto.getIsContractor())
			sb.append("For Contractors\n");
		if (dto.getIsDevelopment())
			sb.append("For Development\n");
		if (dto.getIsDisabled())
			sb.append("For the Disabled\n");
		if (dto.getIsDisaster())
			sb.append("For Disaters\n");
		if (dto.getIsExporting())
			sb.append("For Exporting\n");
		if (dto.getIsGeneralPurpose())
			sb.append("For General Purpose\n");
		if (dto.getIsGreen())
			sb.append("For Green Businesses\n");
		if (dto.getIsMilitary())
			sb.append("For Military\n");
		if (dto.getIsMinority())
			sb.append("For Minorities\n");
		if (dto.getIsRural())
			sb.append("For Rural Businesses\n");
		if (dto.getIsWoman())
			sb.append("For Women\n");

		if (sb.length() < 1)
			sb.append("No specialties.");

		return sb.toString();
	}

	class LoanGrantListAdapter extends ArrayAdapter {
		private Activity context;
		private List<LoanGrantDto> list;

		@SuppressWarnings("unchecked")
		LoanGrantListAdapter(Activity context, List<LoanGrantDto> list) {
			super(context, R.layout.loans_grants_item, list);

			this.context = context;
			this.list = list;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = context.getLayoutInflater();

				row = inflater.inflate(R.layout.loans_grants_item, null);
			}

			TextView title = (TextView) row.findViewById(R.id.txtViewTitle);
			title.setText(list.get(position).getTitle());
			TextView agency = (TextView) row.findViewById(R.id.txtViewAgency);
			agency.setText(list.get(position).getAgency());
			TextView descr = (TextView) row.findViewById(R.id.txtViewSmall);
			descr.setText(list.get(position).getDescription());

			return (row);
		}
	}
}
