package app.sbaloan.models;

import org.json.JSONObject;

public class LoanGrantDto {
	private String state_name;
	private String gov_type;
	private String loan_type;
	private String agency;
	private String industry;
	private String title;
	private String description;
	private String url;
	private boolean is_general_purpose;
	private boolean is_development;
	private boolean is_exporting;
	private boolean is_contractor;
	private boolean is_green;
	private boolean is_military;
	private boolean is_minority;
	private boolean is_woman;
	private boolean is_disabled;
	private boolean is_rural;
	private boolean is_disaster;
	private String json_String;

	public LoanGrantDto(JSONObject o) throws Exception {
		json_String = o.toString();
		gov_type = (String) o.get("gov_type");
		if (!o.isNull("state_name"))
			state_name = (String) o.get("state_name");
		else
			state_name = null;
		loan_type = (String) o.get("loan_type");
		agency = (String) o.get("agency");
		if (!o.isNull("industry"))
			industry = (String) o.get("industry");
		else
			industry = "N/A";

		title = (String) o.get("title");
		description = (String) o.get("description");
		url = ((String) o.get("url"));
        if (url != null)
            url = url.replace(" ", "%20");
		is_general_purpose = stringToBoolean((String)o.get("is_general_purpose"));
		is_development = stringToBoolean((String)o.get("is_development"));
		is_exporting = stringToBoolean((String)o.get("is_exporting"));
		is_contractor = stringToBoolean((String)o.get("is_contractor"));
		is_green = stringToBoolean((String)o.get("is_green"));
		is_military = stringToBoolean((String)o.get("is_military"));
		is_minority = stringToBoolean((String)o.get("is_minority"));
		is_woman = stringToBoolean((String)o.get("is_woman"));
		is_disabled = stringToBoolean((String)o.get("is_disabled"));
		is_rural = stringToBoolean((String)o.get("is_rural"));
		is_disaster = stringToBoolean((String)o.get("is_disaster"));
	}

	private boolean stringToBoolean(String s) {
		if (s == null || s.trim().length() < 1)
			return false;

		Integer i = Integer.parseInt(s);
		if (i == 0)
			return false;

		return true;
	}

	public String getStateName() {
		return state_name;
	}

	public String getGovType() {
		return gov_type;
	}

	public String getLoanType() {
		return loan_type;
	}

	public String getAgency() {
		return agency;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustry() {
		return industry;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public boolean getIsGeneralPurpose() {
		return is_general_purpose;
	}

	public boolean getIsDevelopment() {
		return is_development;
	}

	public boolean getIsExporting() {
		return is_exporting;
	}

	public boolean getIsContractor() {
		return is_contractor;
	}

	public boolean getIsGreen() {
		return is_green;
	}

	public boolean getIsMilitary() {
		return is_military;
	}

	public boolean getIsMinority() {
		return is_minority;
	}

	public boolean getIsWoman() {
		return is_woman;
	}

	public boolean getIsDisabled() {
		return is_disabled;
	}

	public boolean getIsRural() {
		return is_rural;
	}

	public boolean getIsDisaster() {
		return is_disaster;
	}

	public String getOrigJSON() {
		return json_String;
	}
}
