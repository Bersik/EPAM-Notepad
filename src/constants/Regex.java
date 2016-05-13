package constants;

/**
 * Regex constants
 *
 * @author Serhii Kisilchuk
 */
public interface Regex {
    String NAME_REGEX = "^[A-Z][a-zA-Z'-]{0,20}$";
    String NICKNAME_REGEX = "^[a-zA-Z][\\w\\.,\\-]{2,20}$";
    String COMMENT_REGEX = "^.*$";
    String HOME_NUMBER_REGEX = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
    String MOBILE_NUMBER_REGEX = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
    String EMAIL_REGEX = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z0-9]{2,})$";
    String SKYPE_REGEX = "^[a-zA-Z][a-zA-Z0-9\\.,\\-_]{5,31}$";

    String ZIP_REGEX = "^\\d{5}(?:[-\\s]\\d{4})?$";
    String CITY_REGEX = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    String STREET_REGEX = "^[A-Z][\\w -']{2,20}$";
    String BUILDING_REGEX = "^\\d[\\w-]{0,5}$";
    String APARTMENT_REGEX = "^\\d{1,4}-?\\d{0,3}[a-zA-Z]?$";

    String SEARCH_FULL_NAME_REGEX = "^[a-zA-Z '-]{1,60}$";
}
