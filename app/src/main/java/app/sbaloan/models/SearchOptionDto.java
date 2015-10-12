package app.sbaloan.models;

/**
 * Created by danielmurphy on 10/10/15.
 */
public abstract class SearchOptionDto {
    private int resource;

    public SearchOptionDto(int btnResource) {
        resource = btnResource;
    }

    public int getResource() {
        return resource;
    }
}
