import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** register.Register of persons. */
    private Register register;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    /**
     * In JDK 6 use Console class instead.
     * @see ()
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    };
    
    public ConsoleUI(Register register) {
        this.register = register;
    }
    
    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
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
                    findInRegister();
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
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);
        
        return Option.values()[selection - 1];
    }
    
    private void printRegister() {
        int personIterator = 0;
        System.out.println(ANSI_YELLOW + "------------------------");
        System.out.println("Telefonny zoznam:");
        if(register.getCount() == 0){
            System.out.println("Prazdny zoznam! Vlozte polozky");
        } else {
            while (personIterator != register.getCount()) {
                Person person = register.getPerson(personIterator);
                System.out.println(personIterator + 1 + ". " + person.getName() + " (" + person.getPhoneNumber() + ")");
                personIterator++;
            }
            }
        System.out.println("------------------------" + ANSI_RESET);

    }
    
    private void addToRegister() {
        System.out.println("Enter Name: ");
        String name = readLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();

        try {
            register.addPerson(new Person(name, phoneNumber));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateRegister() {
        System.out.println("Enter Name for Update: ");
        String name = readLine();
        Person person = register.findPersonByName(name);
        if(person == null){
            System.out.println(ANSI_RED + "----------------");
            System.out.println("Meno neexistuje!");
            System.out.println("----------------" + ANSI_RESET);
            return;
        }
        System.out.println("Enter NEW name: ");
        String newName = readLine();
        if (register.findPersonByName(newName) != null) {
            System.out.println(ANSI_RED + "----------------");
            System.out.println("Meno sa uz v zozname nachadza!");
            System.out.println("----------------" + ANSI_RESET);
            return;
        }
        System.out.println("Enter NEW number: ");
        String newNumber = readLine();
        if (register.findPersonByPhoneNumber(newNumber) != null) {
            System.out.println(ANSI_RED + "----------------");
            System.out.println("Cislo sa uz v zozname nachadza!");
            System.out.println("----------------" + ANSI_RESET);
            return;
        }
        person.setName(newName);
        person.setPhoneNumber(newNumber);

        System.out.println(ANSI_YELLOW + "------------------------");
        System.out.println("Pridany: " + person.getName() + " (" + person.getPhoneNumber() + ")");
        System.out.println("------------------------" + ANSI_RESET);
    }
    
    //TODO: Implement the method findInRegister
    private void findInRegister() {
        System.out.println("Enter phone or number to find in register: ");
        String numberOrName = readLine();

        Person person;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(numberOrName);
        if(matcher.matches()){
            person = register.findPersonByPhoneNumber(numberOrName);
        } else {
            person = register.findPersonByName(numberOrName);
        }

        if(person != null){
            System.out.println(ANSI_YELLOW + "---------------------------------");
            System.out.println(person.getName() + "(" + person.getPhoneNumber() + ")");
            System.out.println("------------------------" + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "---------------------------------");
            System.out.println("Dany zaznam sa v zozname nenachadza!");
            System.out.println("------------------------" + ANSI_RESET);
        }

    }
    
    private void removeFromRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine());
        Person person = register.getPerson(index - 1);
        try {
            register.removePerson(person);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}
