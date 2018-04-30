/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Register register = new Register(5);

        register.addPerson(new Person("Janko Hrasko", "0900123456"));
        register.addPerson(new Person("Jana Petrova", "56467541564"));
        register.addPerson(new Person("Adam Kolonicky", "454421478"));
        register.addPerson(new Person("Matus Stastny", "32134654654"));

        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}
