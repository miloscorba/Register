import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {
    public static final String FILENAME = "register.bin";

    public static void main(String[] args) throws Exception {
        //Register register = new ArrayRegister(20);
        ListRegister listRegister = new ListRegister();
        try (FileInputStream is = new FileInputStream(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            listRegister.setPersonList((List<Person>) ois.readObject());
        } catch (Exception e){
        }

        ConsoleUI ui = new ConsoleUI(listRegister);
        ui.run();

        try (FileOutputStream os = new FileOutputStream(FILENAME, false);
             ObjectOutputStream oos = new ObjectOutputStream(os);) {
            oos.writeObject(listRegister.getPersonList());
        }
    }
}
