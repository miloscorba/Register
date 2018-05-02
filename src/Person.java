import java.io.Serializable;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * register.Person.
 */
public class Person implements Serializable {
    /** Name of this person. */
    private String name;

    /** Phone number of this person. */
    private String phoneNumber;

    /**
     * Construct a person.
     * @param name name of the person
     * @param phoneNumber phone number of the person
     */
    public Person(String name, String phoneNumber) {
        this.name = name;
        try {
            this.setPhoneNumber(phoneNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns name of this person.
     * @return name of this person
     */
    public String getName(){
        return name;
    }

    /**
     * Sets name of this person.
     * @param nameNew name of this person
     */
    public void setName(String nameNew) {
        name = nameNew;
    }

    /**
     * Returns phone number of this person.
     * @return phone number of this person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number of this person.
     * @param phoneNumberNew phone number of this person
     */
    public void setPhoneNumber(String phoneNumberNew) {
        if(!isValidPhoneNumber(phoneNumberNew)) {
            throw new RuntimeException("Phone number is not valid");
        }
        phoneNumber = phoneNumberNew;
    }


    /**
     * Validates the phone number. Valid phone numbers contains only digits.
     * @param phoneNumber phone number to validate
     * @return <code>true</code> if phone number is valid, <code>false</code> otherwise
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("[\\d]+");
        int phoneToInt = Integer.parseInt(phoneNumber);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Returns a string representation of the person.
     * @return string representation of the person.
     */
    public String toString() {
        return  name + " (" + phoneNumber + ")";
    }

    public static Comparator<Person> StuNameComparator = new Comparator<Person>() {

        public int compare(Person p1, Person p2) {
            String personName1 = p1.getName().toUpperCase();
            String personName2 = p2.getName().toUpperCase();
            return personName1.compareTo(personName2);
        }};
}