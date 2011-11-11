package app.sbaloan;

public class SearchDto {
	private String state_name, gov_type, loan_type, agency, industry;
	private Boolean is_general_purpose, is_development, is_exporting,
			is_contractor, is_green, is_military, is_minority, is_woman,
			is_disabled, is_rural, is_disaster;

	public SearchDto() {
	}

	public void setStateName(String param) {
		state_name = param;
	}

	public void setGovType(String param) {
		gov_type = param;
	}

	public void setLoanType(String param) {
		loan_type = param;
	}

	public void setAgency(String param) {
		agency = param;
	}

	public void setIndustry(String param) {
		industry = param;
	}

	public void setIsGeneralPurpose(boolean param) {
		is_general_purpose = param;
	}

	public void setIsDevelopment(boolean param) {
		is_development = param;
	}

	public void setIsExporting(boolean param) {
		is_exporting = param;
	}

	public void setIsContractor(boolean param) {
		is_contractor = param;
	}

	public void setIsGreen(boolean param) {
		is_green = param;
	}

	public void setIsMilitary(boolean param) {
		is_military = param;
	}

	public void setIsMinority(boolean param) {
		is_minority = param;
	}

	public void setIsWoman(boolean param) {
		is_woman = param;
	}

	public void setIsDisabled(boolean param) {
		is_disabled = param;
	}

	public void setIsRural(boolean param) {
		is_rural = param;
	}

	public void setIsDisaster(boolean param) {
		is_disaster = param;
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

	public String getIndustry() {
		return industry;
	}

	public Boolean getIsGeneralPurpose() {
		return is_general_purpose;
	}

	public Boolean getIsDevelopment() {
		return is_development;
	}

	public Boolean getIsExporting() {
		return is_exporting;
	}

	public Boolean getIsContractor() {
		return is_contractor;
	}

	public Boolean getIsGreen() {
		return is_green;
	}

	public Boolean getIsMilitary() {
		return is_military;
	}

	public Boolean getIsMinority() {
		return is_minority;
	}

	public Boolean getIsWoman() {
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
