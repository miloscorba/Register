public interface Register {
    int getCount();

    int getSize();

    Person getPerson(int index);

    void addPerson(Person person) throws IllegalArgumentException;

    Person findPersonByName(String name);

    Person findPersonByPhoneNumber(String phoneNumber);

    void removePerson(Person person) throws IllegalArgumentException;
}
