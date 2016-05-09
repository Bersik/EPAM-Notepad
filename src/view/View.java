package view;

/**
 * Created on 3:26 09.05.2016
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class View {
    public static final String MAIN_MENU = "\tMenu:\n1. Create note\n2. Search note\n" +
            "3. Show all notes\n4. Exit";
    public static final String SUB_MENU = "\tNote menu:\n1. Print note\n2. Print address\n3. Print zip address\n" +
            "4. Update note\n5. Back";

    public static final String HELLO_MESSAGE = "Hello!";

    public static final String ADDRESS_INPUT_MESSAGE = "Input address";
    public static final String FULL_NAME = "Input full name";
    public static final String SELECTED_NOTE = "Selected note: ";
    public static final String WRONG_MENU_ITEM = "Wrong menu item!";
    public static final String EMPTY_NOTEPAD = "Notepad is empty!";
    public static final String SEARCH_ERROR = "Notepad does not contain notes with that name";
    public static final String INPUT_ERROR= "Incorrect input. Repeat please!";
    public static final String WRONG_NUMBER_MESSAGE= "Wrong number! Number should be in range [%d;%d]. Repeat please!";

    public static final String SURNAME = "Surname";
    public static final String NAME = "Name";
    public static final String PATRONYMIC = "Patronymic";
    public static final String NICKNAME = "Nickname";
    public static final String COMMENT = "Comment";
    public static final String HOME_PHONE = "Home phone number";
    public static final String MOBILE_PHONE = "Mobile phone number";
    public static final String EMAIL = "Email";
    public static final String SKYPE = "Skype";
    public static final String GROUP = "Group";

    public static final String ZIP = "Zip";
    public static final String CITY = "City";
    public static final String STREET = "Street";
    public static final String BUILDING = "Building";
    public static final String APARTMENT = "Apartment";


    public static final String EMPTY_LIST = "";
    public static final String SELECT_NOTE = "";


    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printField(String message) {
        System.out.print(message + ": ");
    }

    public void formatMessage(String message, Object... args) {
        System.out.format(message, args);
    }

    public void printLine() {
        System.out.println();
    }

    public void printItemInList(int id, String item) {
        System.out.println("\t" + id + ") " + item);
    }

}
