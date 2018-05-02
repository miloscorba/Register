import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListRegister implements Register{
    private List<Person> personList;
    private int count;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public ListRegister() {
        personList = new ArrayList<>();
        count = 0;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getSize() {
        return personList.size();
    }

    @Override
    public Person getPerson(int index) {
        return personList.get(index);
    }

    @Override
    public void addPerson(Person person) {
        personList.add(person);
        count++;
    }

    @Override
    public Person findPersonByName(String name){
        for(Person p : personList){
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber){
        for(Person p : personList){
            if (p.getPhoneNumber().equals(phoneNumber))
                return p;
        }
        return  null;
    }

    @Override
    public void removePerson(Person person) throws NullPointerException{
        try {
            personList.remove(person);
            count--;
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}