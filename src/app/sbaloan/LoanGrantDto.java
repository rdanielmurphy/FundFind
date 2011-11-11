package app.sbaloan;

import org.json.JSONObject;

public class LoanGrantDto {
	String state_name;
	String gov_type;
	String loan_type;
	String agency;
	String industry;
	String title;
	String description;
	String url;
	boolean is_general_purpose;
	boolean is_development;
	boolean is_exporting;
	boolean is_contractor;
	boolean is_green;
	boolean is_military;
	boolean is_minority;
	boolean is_woman;
	boolean is_disabled;
	boolean is_rural;
	boolean is_disaster;

	public LoanGrantDto(JSONObject o) throws Exception {
		gov_type = (String) o.get("gov_type");
		if (!o.isNull("state_name"))
			state_name = (String) o.get("state_name");
		else
			state_name = gov_type;
		loan_type = (String) o.get("loan_type");
		agency = (String) o.get("agency");
		if (!o.isNull("industry"))
			industry = (String) o.get("industry");
		else
			industry = "N/A";

		title = (String) o.get("title");
		description = (String) o.get("description");
		url = (String) o.get("url");
		is_general_purpose = intToBoolean((Integer) o.get("is_general_purpose"));
		is_development = intToBoolean((Integer) o.get("is_development"));
		is_exporting = intToBoolean((Integer) o.get("is_exporting"));
		is_contractor = intToBoolean((Integer) o.get("is_contractor"));
		is_green = intToBoolean((Integer) o.get("is_green"));
		is_military = intToBoolean((Integer) o.get("is_military"));
		is_minority = intToBoolean((Integer) o.get("is_minority"));
		is_woman = intToBoolean((Integer) o.get("is_woman"));
		is_disabled = intToBoolean((Integer) o.get("is_disabled"));
		is_rural = intToBoolean((Integer) o.get("is_rural"));
		is_disaster = intToBoolean((Integer) o.get("is_disaster"));
	}

	private boolean intToBoolean(Integer i) {
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

}
