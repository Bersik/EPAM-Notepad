package controller;

import constants.Regexp;
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
                                    //TODO note update
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
        note.setSurName(inputStringWithPattern(sc, Regexp.NAME_REGEXP, View.SURNAME));
        note.setName(inputStringWithPattern(sc, Regexp.NAME_REGEXP, View.NAME));
        note.setPatronymic(inputStringWithPattern(sc, Regexp.NAME_REGEXP, View.PATRONYMIC));
        note.setNickName(inputStringWithPattern(sc, Regexp.NICKNAME_REGEXP, View.NICKNAME));
        note.setComment(inputStringWithPattern(sc, Regexp.COMMENT_REGEXP, View.COMMENT, true));
        note.setGroup(inputGroup(sc));
        note.setHomePhone(inputStringWithPattern(sc, Regexp.HOME_NUMBER_REGEXP, View.HOME_PHONE));
        note.setMobilePhone(inputStringWithPattern(sc, Regexp.MOBILE_NUMBER_REGEXP, View.MOBILE_PHONE));
        note.setEmail(inputStringWithPattern(sc, Regexp.EMAIL_REGEXP, View.EMAIL));
        note.setSkype(inputStringWithPattern(sc, Regexp.SKYPE_REGEXP, View.SKYPE));

        view.printMessage(View.ADDRESS_INPUT_MESSAGE);
        note.setAddress(inputAddress(sc));

        Date now = new Date();
        note.setLastModifiedDate(now);
        note.setCreatedDate(now);
        return note;
    }

    /**
     * Looking note in notepad
     *
     * @param sc instance of Scanner
     * @return found Note
     */
    private Note searchNote(Scanner sc) {
        String fullName = inputStringWithPattern(sc, Regexp.SEARCH_FULL_NAME_REGEXP, View.FULL_NAME);
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
     * Read address from scanner
     *
     * @param sc instance of Scanner
     * @return address
     */
    private Address inputAddress(Scanner sc) {
        Address address = new Address();
        address.setZip(inputStringWithPattern(sc, Regexp.ZIP_REGEXP, View.ZIP));
        address.setCity(inputStringWithPattern(sc, Regexp.CITY_REGEXP, View.CITY));
        address.setStreet(inputStringWithPattern(sc, Regexp.STREET_REGEXP, View.STREET));
        address.setBuilding(inputStringWithPattern(sc, Regexp.BUILDING_REGEXP, View.BUILDING));
        address.setApartment(inputStringWithPattern(sc, Regexp.APARTMENT_REGEXP, View.APARTMENT));
        return address;
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
        for (Note note : notes) {
            view.printMessage(note.toString());
        }
    }

}
