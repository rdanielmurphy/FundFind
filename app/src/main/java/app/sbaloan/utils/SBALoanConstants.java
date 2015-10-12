package app.sbaloan.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.sbaloan.R;
import app.sbaloan.models.IndustrySearchOptionDto;
import app.sbaloan.models.MiscSearchOptionDto;
import app.sbaloan.models.SearchOptionDto;
import app.sbaloan.models.TypeSearchDto;

public class SBALoanConstants {
	public static final List<String> VALUES = Arrays.asList("Don't Care", "Yes", "No");

    public static final List<String> STATES = Arrays.asList("AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL",
            "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MH", "MA", "MI", "FM", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP",
            "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY");

	public static final List<String> TYPES = Arrays.asList("Grant", "Loan", "Venture Capital", "Tax Incentive");

    public static final List<String> INDUSTRIES = Arrays.asList("Agriculture", "Child Care", "Environmental Management", "Health Care", "Manufacturing",
            "Technology", "Tourism");

    public static final String MISC_GEN_PURP = "General Purpose";
    public static final String MISC_DEV = "Development";
    public static final String MISC_EXPORTING = "Exporting";
    public static final String MISC_CONTRACTOR = "Contractor";
    public static final String MISC_GREEN = "Green";
    public static final String MISC_MILITARY = "Military";
    public static final String MISC_MINORITY = "Minority";
    public static final String MISC_WOMAN = "Woman";
    public static final String MISC_DISABLED = "Disabled";
    public static final String MISC_RURAL = "Rural";
    public static final String MISC_DISASTER = "Disaster";

    public static final Map<String, IndustrySearchOptionDto> INDUSTRY = new HashMap<String, IndustrySearchOptionDto>();
    public static final Map<String, TypeSearchDto> TYPE = new HashMap<String, TypeSearchDto>();
    public static final Map<String, MiscSearchOptionDto> MISC = new HashMap<String, MiscSearchOptionDto>();

    static {
        INDUSTRY.put("Agriculture", new IndustrySearchOptionDto(R.id.btnAgriculture, INDUSTRIES.get(0)));
        INDUSTRY.put("Child Care", new IndustrySearchOptionDto(R.id.btnChildCare, INDUSTRIES.get(1)));
        INDUSTRY.put("Enviro Management", new IndustrySearchOptionDto(R.id.btnEnviroManagement, INDUSTRIES.get(2)));
        INDUSTRY.put("Health Care", new IndustrySearchOptionDto(R.id.btnHealthCare, INDUSTRIES.get(3)));
        INDUSTRY.put("Manufacturing", new IndustrySearchOptionDto(R.id.btnManufacturing, INDUSTRIES.get(4)));
        INDUSTRY.put("Technology", new IndustrySearchOptionDto(R.id.btnTechnology, INDUSTRIES.get(5)));
        INDUSTRY.put("Tourism", new IndustrySearchOptionDto(R.id.btnTourism, INDUSTRIES.get(6)));

        TYPE.put(TYPES.get(0), new TypeSearchDto(R.id.btnGrants));
        TYPE.put(TYPES.get(1), new TypeSearchDto(R.id.btnLoans));
        TYPE.put(TYPES.get(2), new TypeSearchDto(R.id.btnVentureCapital));
        TYPE.put(TYPES.get(3), new TypeSearchDto(R.id.btnTaxIncentives));

        MISC.put(MISC_GEN_PURP, new MiscSearchOptionDto(R.id.btnGenPurpose));
        MISC.put(MISC_DEV, new MiscSearchOptionDto(R.id.btnDevelopment));
        MISC.put(MISC_EXPORTING, new MiscSearchOptionDto(R.id.btnExporting));
        MISC.put(MISC_CONTRACTOR, new MiscSearchOptionDto(R.id.btnContractor));
        MISC.put(MISC_GREEN, new MiscSearchOptionDto(R.id.btnGreen));
        MISC.put(MISC_MILITARY, new MiscSearchOptionDto(R.id.btnMilitary));
        MISC.put(MISC_MINORITY, new MiscSearchOptionDto(R.id.btnMinority));
        MISC.put(MISC_WOMAN, new MiscSearchOptionDto(R.id.btnWoman));
        MISC.put(MISC_DISABLED, new MiscSearchOptionDto(R.id.btnDisabled));
        MISC.put(MISC_RURAL, new MiscSearchOptionDto(R.id.btnRural));
        MISC.put(MISC_DISASTER, new MiscSearchOptionDto(R.id.btnDisaster));
    }
}
