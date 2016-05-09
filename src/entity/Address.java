package entity;

/**
 * Address
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class Address {
    private String zip;
    private String city;
    private String street;
    private String building;
    private String apartment;

    public Address() {
    }

    public Address(String zip, String city, String street, String building, String apartment) {
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    /**
     * Return post address like
     * <p>
     * Street 3, 4
     * City
     * 05134
     * </p>
     */
    public String getPostAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(street).append(" ").append(building).append(", ").append(apartment).append("\n").append(city).
                append("\n").append(zip);
        return sb.toString();
    }

    /**
     * Return address like
     * <p>City, street 3, 4</p>
     */
    public String getAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(city).append(", ").append(street).append(" ").append(building).append(", ").append(apartment);
        return sb.toString();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return getAddress();
    }
}
