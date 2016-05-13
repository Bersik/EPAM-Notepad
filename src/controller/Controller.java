package controller;

import constants.Regex;
import entity.Address;
import entity.Group;
import entity.Note;
import model.Notebook;
import view.View;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class
 *
 * @author Serhii Kisilchuk
 */
public class Controller {
    private Notebook notebook;
    private View view;

    public Controller(Notebook notebook, View view) {
        this.notebook = notebook;
        this.view = view;
    }

    /**
     * The Work method
     */
    public void processUser() {
        Scanner sc = new Scanner(System.in);
        int menuItem;

        view.printMessage(View.HELLO_MESSAGE);
        while (true) {
            view.printLine();
            view.printMessage(View.MAIN_MENU);
            Note note;
            menuItem = inputIntValue(sc);
            switch (menuItem) {
                case 1:
                    //append
                    note = readNote(sc);
                    notebook.addNote(note);
                    break;
                case 2:
                    //search
                    note = searchNote(sc);
                    if (note != null) {
                        view.printMessage(View.SELECTED_NOTE + note);
                        subMenu:
                        while (true) {
                            view.printMessage(View.SUB_MENU);
                            menuItem = inputIntValue(sc);
                            switch (menuItem) {
                                case 1:
                                    view.printMessage(note.toString());
                                    break;
                                case 2:
                                    view.printMessage(note.getAddress().getAddress());
                                    break;
                                case 3:
                                    view.printMessage(note.getAddress().getPostAddress());
                                    break;
                                case 4:
                                    updateNote(sc, note);
                                    break;
                                case 5:
                                    break subMenu;
                                default:
                                    view.printMessage(View.WRONG_MENU_ITEM);
                                    break;
                            }
                        }
                    } else {
                        view.printMessage(View.SEARCH_ERROR);
                    }
                    break;
                case 3:
                    //print all
                    List<Note> notes = notebook.getNotebook();
                    if (notes.isEmpty()) {
                        view.printMessage(View.EMPTY_NOTEPAD);
                    } else {
                        printNotebook(notes);
                    }
                    break;
                case 4:
                    //exit
                    return;
                default:
                    view.printMessage(View.WRONG_MENU_ITEM);
                    break;
            }
        }
    }

    /**
     * Read note from console
     *
     * @param sc instance of Scanner
     * @return created and filled Note
     */
    private Note readNote(Scanner sc) {
        Note note = new Note();
        note.setSurName(inputStringWithPattern(sc, Regex.NAME_REGEX, View.SURNAME));
        note.setName(inputStringWithPattern(sc, Regex.NAME_REGEX, View.NAME));
        note.setPatronymic(inputStringWithPattern(sc, Regex.NAME_REGEX, View.PATRONYMIC));
        note.setNickName(inputStringWithPattern(sc, Regex.NICKNAME_REGEX, View.NICKNAME));
        note.setComment(inputStringWithPattern(sc, Regex.COMMENT_REGEX, View.COMMENT, true));
        note.setGroup(inputGroup(sc));
        note.setHomePhone(inputStringWithPattern(sc, Regex.HOME_NUMBER_REGEX, View.HOME_PHONE));
        note.setMobilePhone(inputStringWithPattern(sc, Regex.MOBILE_NUMBER_REGEX, View.MOBILE_PHONE));
        note.setEmail(inputStringWithPattern(sc, Regex.EMAIL_REGEX, View.EMAIL));
        note.setSkype(inputStringWithPattern(sc, Regex.SKYPE_REGEX, View.SKYPE));

        view.printMessage(View.ADDRESS_INPUT_MESSAGE);
        note.setAddress(inputAddress(sc));

        Date now = new Date();
        note.setLastModifiedDate(now);
        note.setCreatedDate(now);
        return note;
    }


    /**
     * Read new note field from console
     *
     * @param sc   instance of Scanner
     * @param note instance of note
     */
    private void updateNote(Scanner sc, Note note) {
        note.setSurName(updateField(sc, note.getSurName(), Regex.NAME_REGEX, View.SURNAME));
        note.setName(updateField(sc, note.getName(), Regex.NAME_REGEX, View.NAME));
        note.setPatronymic(updateField(sc, note.getPatronymic(), Regex.NAME_REGEX, View.PATRONYMIC));
        note.setNickName(updateField(sc, note.getNickName(), Regex.NICKNAME_REGEX, View.NICKNAME));
        note.setComment(updateField(sc, note.getComment(), Regex.COMMENT_REGEX, View.COMMENT, true));
        note.setGroup(inputGroup(sc));
        note.setHomePhone(updateField(sc, note.getHomePhone(), Regex.HOME_NUMBER_REGEX, View.HOME_PHONE));
        note.setMobilePhone(updateField(sc, note.getMobilePhone(), Regex.MOBILE_NUMBER_REGEX, View.MOBILE_PHONE));
        note.setEmail(updateField(sc, note.getEmail(), Regex.EMAIL_REGEX, View.EMAIL));
        note.setSkype(updateField(sc, note.getSkype(), Regex.SKYPE_REGEX, View.SKYPE));

        view.printMessage(View.ADDRESS_INPUT_MESSAGE);
        updateAddress(sc, note.getAddress());

        note.setLastModifiedDate(new Date());
    }

    /**
     * Looking note in notepad
     *
     * @param sc instance of Scanner
     * @return found Note
     */
    private Note searchNote(Scanner sc) {
        String fullName = inputStringWithPattern(sc, Regex.SEARCH_FULL_NAME_REGEX, View.FULL_NAME);
        List<Note> notes = notebook.findNotesByFullName(fullName);

        if (notes.isEmpty()) {
            view.printMessage(View.EMPTY_LIST);
            return null;
        }
        if (notes.size() == 1) {
            return notes.get(0);
        }
        view.printMessage(View.SELECT_NOTE);
        int i = 1;
        for (Note note : notes) {
            view.printItemInList(i++, note.toString());
        }
        int id = inputIntValueInRange(sc, 1, notes.size()) - 1;
        return notes.get(id);
    }

    /**
     * Read text line from console satisfying pattern
     *
     * @param sc      instance of Scanner
     * @param pattern regexp
     * @param field   name of field
     * @return text line
     */
    private String inputStringWithPattern(Scanner sc, String pattern, String field) {
        String line;
        while (true) {
            view.printField(field);
            line = sc.nextLine();
            if (line.matches(pattern))
                return line;
            else {
                view.printMessage(View.INPUT_ERROR);
            }
        }
    }

    /**
     * Read text line from console satisfying pattern
     *
     * @param sc      instance of Scanner
     * @param pattern regexp
     * @param field   name of field
     * @param empty   indicates whether the field be empty
     * @return text line
     */
    private String inputStringWithPattern(Scanner sc, String pattern, String field, boolean empty) {
        if (!empty)
            return inputStringWithPattern(sc, pattern, field);
        String line;
        while (true) {
            view.printField(field);
            if (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.matches(pattern)) {
                    return line;
                } else {
                    view.printMessage(View.INPUT_ERROR);
                }
            } else {
                return "";
            }
        }
    }

    /**
     * Read text line from console satisfying pattern and update it, if line is not empty
     *
     * @param sc         instance of Scanner
     * @param fieldValue field value
     * @param pattern    regexp
     * @param field      name of field
     * @return new value (or last, if {@code line} was empty)
     */
    private String updateField(Scanner sc, String fieldValue, String pattern, String field) {
        String line;
        while (true) {
            view.printField(field, fieldValue);
            line = sc.nextLine();
            if (line.isEmpty()) {
                return fieldValue;
            }
            if (line.matches(pattern))
                return line;
            else {
                view.printMessage(View.INPUT_ERROR);
            }
        }
    }

    /**
     * Read text line from console satisfying pattern and update it, if line is not empty
     *
     * @param sc         instance of Scanner
     * @param fieldValue field value
     * @param pattern    regexp
     * @param field      name of field
     * @param empty      indicates whether the field be empty
     * @return new value (or last, if {@code line} was empty)
     */
    private String updateField(Scanner sc, String fieldValue, String pattern, String field, boolean empty) {
        if (!empty)
            return inputStringWithPattern(sc, pattern, field);
        String line;
        while (true) {
            view.printField(field, fieldValue);
            if (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.matches(pattern)) {
                    return line;
                } else {
                    view.printMessage(View.INPUT_ERROR);
                }
            } else {
                return fieldValue;
            }
        }
    }


    /**
     * Read address from scanner
     *
     * @param sc instance of Scanner
     * @return address
     */
    private Address inputAddress(Scanner sc) {
        Address address = new Address();
        address.setZip(inputStringWithPattern(sc, Regex.ZIP_REGEX, View.ZIP));
        address.setCity(inputStringWithPattern(sc, Regex.CITY_REGEX, View.CITY));
        address.setStreet(inputStringWithPattern(sc, Regex.STREET_REGEX, View.STREET));
        address.setBuilding(inputStringWithPattern(sc, Regex.BUILDING_REGEX, View.BUILDING));
        address.setApartment(inputStringWithPattern(sc, Regex.APARTMENT_REGEX, View.APARTMENT));
        return address;
    }

    /**
     * Update address; read field values from scanner
     *
     * @param sc      instance of Scanner
     * @param address address
     */
    private void updateAddress(Scanner sc, Address address) {
        address.setZip(updateField(sc, address.getZip(), Regex.ZIP_REGEX, View.ZIP));
        address.setCity(updateField(sc, address.getCity(), Regex.CITY_REGEX, View.CITY));
        address.setStreet(updateField(sc, address.getStreet(), Regex.STREET_REGEX, View.STREET));
        address.setBuilding(updateField(sc, address.getBuilding(), Regex.BUILDING_REGEX, View.BUILDING));
        address.setApartment(updateField(sc, address.getApartment(), Regex.APARTMENT_REGEX, View.APARTMENT));
    }

    /**
     * Read group from scanner
     *
     * @param sc instance of Scanner
     * @return group
     */
    private Group inputGroup(Scanner sc) {
        Group[] groups = Group.values();
        view.printField(View.GROUP);
        view.printLine();
        for (int i = 0; i < groups.length; i++) {
            view.printItemInList((i + 1), groups[i].name());
        }
        int groupId = inputIntValueInRange(sc, 1, groups.length) - 1;
        return groups[groupId];
    }

    /**
     * Read int value from scanner in the range [min,max] (including boundary)
     *
     * @param sc  instance of Scanner
     * @param min min value
     * @param max max value
     * @return int value
     */
    private int inputIntValueInRange(Scanner sc, int min, int max) {
        int num;
        while (true) {
            if (!sc.hasNextInt()) {
                view.printMessage(View.INPUT_ERROR);
                sc.next();
            } else {
                num = sc.nextInt();
                sc.nextLine();
                if (num >= min && num <= max)
                    return num;
                else {
                    view.formatMessage(View.WRONG_NUMBER_MESSAGE, min, max);
                }
            }
        }
    }

    /**
     * Read int value
     *
     * @param sc instance of Scanner
     * @return int value
     */
    private int inputIntValue(Scanner sc) {
        while (true) {
            if (!sc.hasNextInt()) {
                view.printMessage(View.INPUT_ERROR);
                sc.next();
            } else {
                int num = sc.nextInt();
                sc.nextLine();
                return num;
            }
        }
    }

    /**
     * Print notebook
     *
     * @param notes notes
     */
    private void printNotebook(List<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            view.printItemInList(i + 1, notes.get(i).toString());
        }
    }

}
