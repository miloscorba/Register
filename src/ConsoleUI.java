import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** register.ArrayRegister of persons. */
    //private Register register;
    private ListRegister register;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * In JDK 6 use Console class instead.
     * @see
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    };

    public ConsoleUI(ListRegister register) {
        this.register = register;
    }

    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    try {
                        printRegister();
                    } catch (NullPointerException e) {

                    }
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    try {
                        findInRegister();
                    } catch (Exception e) {

                    }
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private String readLine() {
        //In JDK 6.0 and above Console class can be used
        //return System.console().readLine();

        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private Option showMenu() {
        System.out.println("Menu.");
        for (Option option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");

        int selection = -1;
        do {
            System.out.println("Option: ");
            try {
                selection = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter option!");
            }
        } while (selection <= 0 || selection > Option.values().length);

        return Option.values()[selection - 1];
    }


    private void printRegister() throws NullPointerException {
        Collections.sort(register.getPersonList(), Person.StuNameComparator);
        System.out.println(ANSI_YELLOW + "------------ Telefonny zoznam ------------");
        if(register.getSize() > 0){
            for(int personInRegister = 0; personInRegister <=register.getSize()-1; personInRegister++){
                System.out.println(personInRegister+1 + ". " + register.getPerson(personInRegister).getName()
                        + " (" + register.getPerson(personInRegister).getPhoneNumber() + ")");
            }
        }
        System.out.println("------------------------------------------" + ANSI_RESET);
    }

    private void addToRegister() {
        System.out.println("Enter new Name: ");
        String name = readLine();
        System.out.println("Enter new Phone Number: ");
        String phoneNumber = readLine();


        if(register.findPersonByName(name) == null && register.findPersonByPhoneNumber(phoneNumber) == null) {
            register.addPerson(new Person(name, phoneNumber));
            System.out.println(ANSI_YELLOW + "-----------------");
            System.out.println("New person in register:");
            System.out.println(name + " (" + phoneNumber + ")\n" + ANSI_RESET);
        }


    }

    //TODO: Implement the method updateRegister
    private void updateRegister() {
        System.out.println("Enter name for update: ");
        String name = readLine();
        Person p;
            p = register.findPersonByName(name);
            System.out.println("Enter new name for update: ");
            String newName = readLine();
            System.out.println("Enter new phone number for update: ");
            String newPhone = readLine();
            if(register.findPersonByName(newName) == null || register.findPersonByPhoneNumber(newPhone) == null) {
                p.setName(newName);
                p.setPhoneNumber(newPhone);
            }
                else {
                    System.out.println(ANSI_RED + "----------------------------");
                    System.out.println("Dany clovek sa uz v zozname nachadza \n" + ANSI_RESET);
                }
    }

    //TODO: Implement the method findInRegister
    private void findInRegister() {
        System.out.println("Enter name or phone number: ");
        String namePhone = readLine();
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(namePhone);
        Person p = null;
        if(matcher.matches()){
            try {
                p = register.findPersonByPhoneNumber(namePhone);
                System.out.println(ANSI_YELLOW + "--------------------");
                System.out.println(p.getName() + " (" + p.getPhoneNumber() + ")");
                System.out.println(ANSI_YELLOW + "--------------------" + ANSI_RESET);
            } catch (NullPointerException e) {
                throw new NullPointerException(ANSI_RED + "NOT in register" + ANSI_RESET);
            }

        } else {
            try {
                p = register.findPersonByName(namePhone);
                System.out.println(ANSI_YELLOW + "--------------------");
                System.out.println(p.getName() + " (" + p.getPhoneNumber() + ")");
                System.out.println(ANSI_YELLOW + "--------------------" + ANSI_RESET);
            } catch (NullPointerException e) {
                throw new NullPointerException(ANSI_RED + "NOT in register" + ANSI_RESET);
            }
        }



    }

    private void removeFromRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine());
        if(index > 0 && index <= register.getSize() ) {
            Person person = register.getPerson(index - 1);
            register.removePerson(person);
        }
        else {
            System.out.println(ANSI_RED +"----------------------------------");
            System.out.println("Entered number of index is NOT valid!"+ANSI_RESET);
        }
    }

}