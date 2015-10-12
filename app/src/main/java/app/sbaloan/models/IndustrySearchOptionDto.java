package app.sbaloan.models;

/**
 * Created by danielmurphy on 10/10/15.
 */
public class IndustrySearchOptionDto extends SearchOptionDto {
    private String fullTitle;

    public IndustrySearchOptionDto(int btnResource, String fullTitle) {
        super(btnResource);

        this.fullTitle = fullTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }
}
