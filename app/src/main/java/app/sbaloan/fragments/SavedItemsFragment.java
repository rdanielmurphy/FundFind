package app.sbaloan.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
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

import java.util.List;

import app.sbaloan.R;
import app.sbaloan.database.SBALoanDataBaseInterface;
import app.sbaloan.activities.SBALoanView;
import app.sbaloan.models.LoanGrantDto;

/**
 * Created by danielmurphy on 10/10/15.
 */
public class SavedItemsFragment extends ListFragment {
    private List<LoanGrantDto> _list;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.loans_grants_list, container, false);

        return rootView;
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), SBALoanView.class);
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

    private void buildList() {
        final ProgressBar progress = (ProgressBar) rootView.findViewById(R.id.progressBar1);

        new Thread(new Runnable() {
            public void run() {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            progress.setVisibility(ProgressBar.VISIBLE);
                        }
                    });

                    _list = SBALoanDataBaseInterface.getEntries(getActivity().getApplicationContext());
                    final ListAdapter adapter = new LoanGrantListAdapter(getActivity(), _list);

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                setListAdapter(adapter);
                                progress.setVisibility(ProgressBar.GONE);
                            } catch (Exception e1) {
                                Toast.makeText(getActivity(), "Error...could not generate search. See log.", Toast.LENGTH_LONG).show();
                                Log.e("search exception", e1.getMessage());
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error...could not generate search. See log.", Toast.LENGTH_LONG).show();
                    Log.e("search exception", e.getMessage());
                } finally {

                }
            }
        }).start();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            buildList();
        }
    }

    @Override
    public String toString() {
        return "Saved Items";
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