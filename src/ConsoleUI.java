import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** register.ArrayRegister of persons. */
    //private ArrayRegister register;
    private ListRegister register;

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
    
    //public ConsoleUI(ArrayRegister register) {
    //    this.register = register;
    //}

    public ConsoleUI(ListRegister register) {
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
                    try {
                        updateRegister();
                    } catch (IllegalArgumentException e) {
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                    }
                    break;
                case REMOVE:
                    try {
                        removeFromRegister();
                    } catch (IllegalArgumentException e) {
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                    }
                    break;
                case FIND:
                    try {
                        findInRegister();
                    } catch (IllegalArgumentException e) {
                        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
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
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);
        
        return Option.values()[selection - 1];
    }
    
    private void printRegister() {
        int personIterator = 0;
        System.out.println(ANSI_YELLOW + "------------------------");
        System.out.println("Telefonny zoznam:");
        if(register.getSize() == 0){
            System.out.println("Prazdny zoznam! Vlozte polozky");
        } else {
            while (personIterator != register.getSize()) {
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

    private void updateRegister() throws IllegalArgumentException {
        System.out.println("Enter Name for Update: ");
        String name = readLine();
        Person person = register.findPersonByName(name);
        if(person == null){
            System.out.println(ANSI_RED + "----------------");
            throw  new IllegalArgumentException("Meno neexistuje!"+ ANSI_RESET);
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
    private void findInRegister() throws  IllegalArgumentException {
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
            throw new IllegalArgumentException("Meno alebo cislo sa v zozname nenachadza!");
        }

    }
    
    private void removeFromRegister() throws IllegalArgumentException {
        System.out.println("Enter index: ");

        int index = Integer.parseInt(readLine());
        if(index < register.getCount()+1 && index >= 1) {
            Person person = register.getPerson(index - 1);
            try {
                register.removePerson(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            throw  new IllegalArgumentException("Neplatny vstup!");
        }
    }


}
