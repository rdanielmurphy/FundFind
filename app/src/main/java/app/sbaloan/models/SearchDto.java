package app.sbaloan.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SearchDto implements Parcelable{
    public final static int GOV_FEDERAL_ONLY = 0;
    public final static int GOV_STATE_ONLY = 1;
    public final static int GOV_FEDERAL_AND_STATE = 2;

	private String state_name;
    private int gov_type;
	private Boolean is_general_purpose, is_development, is_exporting, is_contractor, is_green, is_military, is_minority, is_woman, is_disabled, is_rural,
			is_disaster;
    private boolean include_misc_filters = false;
    private List<String> industries, loan_types;

	public SearchDto() {
        industries = new ArrayList<String>();
        loan_types = new ArrayList<String>();
	}

    public SearchDto(Parcel in) {
        this();

        state_name = in.readString();
        gov_type = in.readInt();
        is_general_purpose = in.readByte() != 0;
        is_development = in.readByte() != 0;
        is_exporting = in.readByte() != 0;
        is_contractor = in.readByte() != 0;
        is_green = in.readByte() != 0;
        is_military = in.readByte() != 0;
        is_minority = in.readByte() != 0;
        is_woman = in.readByte() != 0;
        is_disabled = in.readByte() != 0;
        is_rural = in.readByte() != 0;
        is_disaster = in.readByte() != 0;
        include_misc_filters = in.readByte() != 0;
        in.readStringList(industries);
        in.readStringList(loan_types);
    }

    public void setStateName(String param) {
		state_name = param;
	}

	public void setGovType(int param) {
		gov_type = param;
	}

	public void setLoanTypes(List<String> param) {
        loan_types.addAll(param);
	}

	public void setIndustries(List<String> param) {
        industries.addAll(param);
	}

	public void setIsGeneralPurpose(boolean param) {
		is_general_purpose = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsDevelopment(boolean param) {
		is_development = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsExporting(boolean param) {
		is_exporting = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsContractor(boolean param) {
		is_contractor = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsGreen(boolean param) {
		is_green = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsMilitary(boolean param) {
		is_military = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsMinority(boolean param) {
		is_minority = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsWoman(boolean param) {
		is_woman = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsDisabled(boolean param) {
		is_disabled = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsRural(boolean param) {
		is_rural = param;
        if (param)
            include_misc_filters = true;
    }

	public void setIsDisaster(boolean param) {
		is_disaster = param;
        if (param)
            include_misc_filters = true;
    }

    public void setIncludeMiscFilters(boolean param) {
        include_misc_filters = param;
    }

	public String getStateName() {
		return state_name;
	}

	public int getGovType() {
		return gov_type;
	}

	public List<String> getLoanType() {
		return loan_types;
	}

	public List<String> getIndustry() {
		return industries;
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

	public Boolean getIsDisabled() {
		return is_disabled;
	}

	public Boolean getIsRural() {
		return is_rural;
	}

	public Boolean getIsDisaster() {
		return is_disaster;
	}

    public Boolean getFilterMisc() {
        return include_misc_filters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel in, int arg1) {
        in.writeString(state_name);
        in.writeInt(gov_type);
        in.writeByte((byte) (is_general_purpose ? 1 : 0));
        in.writeByte((byte) (is_development ? 1 : 0));
        in.writeByte((byte) (is_exporting ? 1 : 0));
        in.writeByte((byte) (is_contractor ? 1 : 0));
        in.writeByte((byte) (is_green ? 1 : 0));
        in.writeByte((byte) (is_military ? 1 : 0));
        in.writeByte((byte) (is_minority ? 1 : 0));
        in.writeByte((byte) (is_woman ? 1 : 0));
        in.writeByte((byte) (is_disabled ? 1 : 0));
        in.writeByte((byte) (is_rural ? 1 : 0));
        in.writeByte((byte) (is_disaster ? 1 : 0));
        in.writeByte((byte) (include_misc_filters ? 1 : 0));
        in.writeStringList(industries);
        in.writeStringList(loan_types);
    }

    public static final Parcelable.Creator<SearchDto> CREATOR = new Parcelable.Creator<SearchDto>() {

        @Override
        public SearchDto createFromParcel(Parcel in) {
            return new SearchDto(in);
        }

        @Override
        public SearchDto[] newArray(int size) {
            return new SearchDto[size];
        }

    };
}
