package app.sbaloan.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.dd.morphingbutton.MorphingButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.sbaloan.R;
import app.sbaloan.models.IndustrySearchOptionDto;
import app.sbaloan.models.MiscSearchOptionDto;
import app.sbaloan.models.SearchDto;
import app.sbaloan.models.SearchOptionDto;
import app.sbaloan.models.TypeSearchDto;
import app.sbaloan.ui.OnOffMorphingButton;
import app.sbaloan.utils.SBALoanConstants;

/**
 * Created by danielmurphy on 10/10/15.
 */
public class SearchOptionsFragment extends Fragment {
    private View rootView;
    private TextView tvAreaDetails, tvIndustriesDetails, tvTypesDetails, tvMiscDetails;
    private Switch federalSwitch, stateSwitch;
    private Spinner stateSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_options_view, container, false);

        //configure area
        stateSpinner = (Spinner) rootView.findViewById(R.id.stateSpinner);
        List<String> stateOptions = new ArrayList<String>();
        stateOptions.addAll(SBALoanConstants.STATES);
        populateSpinner(stateSpinner, stateOptions);
        tvAreaDetails = (TextView) rootView.findViewById(R.id.tvAreasDetails);
        federalSwitch = (Switch) rootView.findViewById(R.id.federalSwitch);
        federalSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                resetHeaders();
            }
        });
        stateSwitch = (Switch) rootView.findViewById(R.id.stateSwitch);
        stateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                resetHeaders();
            }
        });
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                resetHeaders();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                resetHeaders();
            }
        });

        //configure industries
        for (Map.Entry<String, IndustrySearchOptionDto> entry : SBALoanConstants.INDUSTRY.entrySet())
            buildOnOffMorphButton(entry.getKey(), entry.getValue());
        tvIndustriesDetails = (TextView) rootView.findViewById(R.id.tvIndustriesDetails);

        //configure type
        for (Map.Entry<String, TypeSearchDto> entry : SBALoanConstants.TYPE.entrySet())
            buildOnOffMorphButton(entry.getKey(), entry.getValue());
        tvTypesDetails = (TextView) rootView.findViewById(R.id.tvTypesDetails);

        //configure misc buttons
        for (Map.Entry<String, MiscSearchOptionDto> entry : SBALoanConstants.MISC.entrySet())
            buildOnOffMorphButton(entry.getKey(), entry.getValue(), false);
        tvMiscDetails = (TextView) rootView.findViewById(R.id.tvMiscDetails);

        resetHeaders();

        return rootView;
    }

    private void buildOnOffMorphButton(String text, SearchOptionDto dto) {
        buildOnOffMorphButton(text, dto, true);
    }

    private void buildOnOffMorphButton(String text, SearchOptionDto dto, boolean on) {
        final OnOffMorphingButton btnMorph = (OnOffMorphingButton) rootView.findViewById(dto.getResource());
        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMorph.switchState();
                resetHeaders();
            }
        });
        btnMorph.setMorphOnParams(buildOnParams(text));
        btnMorph.setMorphOffParams(buildOffParams("Add " + text));
        if (on)
            btnMorph.setOn();
        else
            btnMorph.setOff();
    }

    private void resetHeaders() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    String areaText = "";
                    if (federalSwitch.isChecked()) {
                        areaText += "Federal";
                    }
                    if (stateSwitch.isChecked()) {
                        if (federalSwitch.isChecked())
                            areaText += " and ";
                        areaText += stateSpinner.getSelectedItem().toString();
                    }
                    if (areaText.length() == 0)
                        areaText = "Select an Area of Search";
                    tvAreaDetails.setText(areaText);

                    tvTypesDetails.setText(calculateString(SBALoanConstants.TYPE));
                    tvIndustriesDetails.setText(calculateString(SBALoanConstants.INDUSTRY));
                    tvMiscDetails.setText(calculateString(SBALoanConstants.MISC));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String calculateString(Map<String, ? extends SearchOptionDto> map) {
        String text = "";
        int count = 0;

        for (Map.Entry<String, ? extends SearchOptionDto> entry : map.entrySet()) {
            OnOffMorphingButton btn = (OnOffMorphingButton) (rootView.findViewById(entry.getValue().getResource()));
            if (btn.isButtonOn()) {
                count++;
                if (count > 3)
                    text = count + " filters";
                else if (count == SBALoanConstants.INDUSTRY.size())
                    text = "All";
                else {
                    if (count > 1)
                        text = text + "/";
                    text = text + entry.getKey();
                }
            }
        }

        return text;
    }

    private MorphingButton.Params buildOffParams(String text) {
        MorphingButton.Params params = MorphingButton.Params.create()
                .duration(getResources().getInteger(R.integer.mb_animation))
                .cornerRadius((int) getResources().getDimension(R.dimen.mb_corner_radius_4))
                .width((int) getResources().getDimension(R.dimen.mb_width_175))
                .height((int) getResources().getDimension(R.dimen.mb_height_56))
                .color(getResources().getColor(R.color.mb_red))
                .colorPressed(getResources().getColor(R.color.mb_red_dark))
                .text(text);
        return params;
    }

    private MorphingButton.Params buildOnParams(String text) {
        MorphingButton.Params params = MorphingButton.Params.create()
                .duration(getResources().getInteger(R.integer.mb_animation))
                .cornerRadius((int) getResources().getDimension(R.dimen.mb_corner_radius_4))
                .width((int) getResources().getDimension(R.dimen.mb_width_120))
                .height((int) getResources().getDimension(R.dimen.mb_height_56))
                .color(getResources().getColor(R.color.mb_green))
                .colorPressed(getResources().getColor(R.color.mb_green_dark))
                .text(text)
                .icon(R.drawable.check);
        return params;
    }

    private void populateSpinner(Spinner s, List<String> strings) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (String string : strings)
            adapter.add(string);

        s.setAdapter(adapter);
    }

    public List<String> getSelectedTypes() throws Exception {
        List<String> list = new ArrayList<String>();

        for (Map.Entry<String, TypeSearchDto> entry : SBALoanConstants.TYPE.entrySet()) {
            if (((OnOffMorphingButton)rootView.findViewById(SBALoanConstants.TYPE.get(entry.getKey()).getResource())).isButtonOn())
                list.add(entry.getKey());
        }

        return list;
    }

    public List<String> getSelectedIndustries() throws Exception {
        List<String> list = new ArrayList<String>();

        for (Map.Entry<String, IndustrySearchOptionDto> entry : SBALoanConstants.INDUSTRY.entrySet()) {
            if (((OnOffMorphingButton)rootView.findViewById(SBALoanConstants.INDUSTRY.get(entry.getKey()).getResource())).isButtonOn())
                list.add(entry.getValue().getFullTitle());
        }

        return list;
    }

    public boolean getButtonState(String btn) {
        return ((OnOffMorphingButton)rootView.findViewById(SBALoanConstants.MISC.get(btn).getResource())).isButtonOn();
    }

    public SearchDto getSearchDto() throws Exception {
        SearchDto dto = new SearchDto();
        int govType = -1;
        if (federalSwitch.isChecked() && stateSwitch.isChecked())
            govType = SearchDto.GOV_FEDERAL_AND_STATE;
        else if (federalSwitch.isChecked() && !stateSwitch.isChecked())
            govType = SearchDto.GOV_FEDERAL_ONLY;
        else if (!federalSwitch.isChecked() && stateSwitch.isChecked())
            govType = SearchDto.GOV_STATE_ONLY;
        else {
            throw new Exception("Please select Federal or State government");
        }
        dto.setGovType(govType);
        dto.setStateName(stateSpinner.getSelectedItem().toString());
        dto.setLoanTypes(getSelectedTypes());
        dto.setIndustries(getSelectedIndustries());
        dto.setIsGeneralPurpose(getButtonState(SBALoanConstants.MISC_GEN_PURP));
        dto.setIsDevelopment(getButtonState(SBALoanConstants.MISC_DEV));
        dto.setIsExporting(getButtonState(SBALoanConstants.MISC_EXPORTING));
        dto.setIsContractor(getButtonState(SBALoanConstants.MISC_CONTRACTOR));
        dto.setIsGreen(getButtonState(SBALoanConstants.MISC_GREEN));
        dto.setIsMilitary(getButtonState(SBALoanConstants.MISC_MILITARY));
        dto.setIsMinority(getButtonState(SBALoanConstants.MISC_MINORITY));
        dto.setIsWoman(getButtonState(SBALoanConstants.MISC_WOMAN));
        dto.setIsDisabled(getButtonState(SBALoanConstants.MISC_DISABLED));
        dto.setIsRural(getButtonState(SBALoanConstants.MISC_RURAL));
        dto.setIsDisaster(getButtonState(SBALoanConstants.MISC_DISASTER));

        return dto;
    }

    @Override
    public String toString() {
        return "New Search";
    }
}