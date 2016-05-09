package constants;

/**
 * Regexp constants
 *
 * @author Serhii Kisilchuk
 */
public interface Regexp {
    String NAME_REGEXP = "^[A-Z][a-zA-Z'-]{0,20}$";
    String NICKNAME_REGEXP = "^[a-zA-Z][\\w\\.,\\-]{2,20}$";
    String COMMENT_REGEXP = "^.*$";
    String HOME_NUMBER_REGEXP = "^[+*#\\d- ]{1,20}$";
    String MOBILE_NUMBER_REGEXP = "^[+*#\\d- ]{1,20}$";
    String EMAIL_REGEXP = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z0-9]{2,})$";
    String SKYPE_REGEXP = "^[a-zA-Z][a-zA-Z0-9\\.,\\-_]{5,31}$";

    String ZIP_REGEXP = "^\\d{5}(?:[-\\s]\\d{4})?$";
    String CITY_REGEXP = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    String STREET_REGEXP = "^[A-Z][\\w -']{2,20}$";
    String BUILDING_REGEXP = "^\\d[\\w-]{0,5}$";
    String APARTMENT_REGEXP = "^\\d{1,4}-?\\d{0,3}[a-zA-Z]?$";

    String SEARCH_FULL_NAME_REGEXP = "^[a-zA-Z '-]{1,60}$";
}
