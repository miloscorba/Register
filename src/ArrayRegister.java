import com.sun.deploy.util.ArrayUtil;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
    /** register.Person array. */
    private Person[] persons;
    
    /** Number of persons in this register. */
    private int count;
    
    /**
     * Constructor creates an empty register with maximum size specified.
     * @param size maximum size of the register
     */
    public ArrayRegister(int size) {
        persons = new Person[size];
        count = 0;
    }
    
    /**
     * Returns the number of persons in this register.
     * @return the number of persons in this register
     */
    @Override
    public int getCount() {
        return count;
    }
    
    /**
     * Returns the maximum number of persons in this register.
     * @return the maximum number of persons in this register.
     */
    @Override
    public int getSize() {
        return persons.length;
    }
    
    /**
     * Returns the person at the specified position in this register.
     * @param index index of the person to return 
     * @return person the person at the specified position in this register 
     */
    @Override
    public Person getPerson(int index){
        return persons[index];
    }

    /**
     * Appends the specified person to the end of this register. 
     * @param person person to append to this register
     */
    @Override
    public void addPerson(Person person) throws IllegalArgumentException {
        if(count == getSize()){
            System.out.println("----------------------------------------");
            throw  new IllegalArgumentException("Zoznam je plny!!");

        }
        if(findPersonByName(person.getName()) == null && findPersonByPhoneNumber(person.getPhoneNumber()) == null){
            persons[count] = person;
            count++;
        } else {
            throw new IllegalArgumentException("Cislo alebo meno sa uz v zozname nachadza!");
        }
    }       
    
    //TODO: Implement the method findPersonByName
    /**
     * Returns the person with specified name in this register or <code>null</code>,
     * if match can not be found.
     * @param name name of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByName(String name) {
        for(Person person : persons){
            if(person == null){
                return null;
            }
            if(person.getName().equals(name)){
                return person;
            }
        }
        return null;
    }
    
    //TODO: Implement the method findPersonByPhoneNumber
    /**
     * Returns the person with specified phone number in this register or <code>null</code>,
     * if match can not be found.
     * @param phoneNumber phone number of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        for(Person person : persons){
            if(person == null){
                return null;
            }
            if(person.getPhoneNumber().equals(phoneNumber)){
                return person;
            }
        }
        return null;
    }
    
    //TODO: Implement the method removePerson
    /**
     * Removes the specified person from the register.
     * @param person person to remove
     */
    @Override
    public void removePerson(Person person) throws IllegalArgumentException {
        boolean remove = false;
        if(count == 0){
            throw  new IllegalArgumentException("Prazdny zoznam!");
        }
        for(int numberOfDeleted = 0; numberOfDeleted < persons.length -1; numberOfDeleted++){
            if(person == persons[numberOfDeleted]){
                System.arraycopy(persons, numberOfDeleted + 1, persons, numberOfDeleted,
                        persons.length - 1 - numberOfDeleted);
                count--;
                remove = true;
                return;
            }
        }
        if(!remove){
            throw new IllegalArgumentException("Nenachadza sa v zozname");
        }
    }
}
