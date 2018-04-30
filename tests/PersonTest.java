import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest{

    Person person = new Person("Adam", "56465465464");
    Person personTwo = new Person("Anakonda", "455416156444");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void setName() {
        person.setName("Bulabula");
        assertEquals("Bulabula", person.getName());
    }

    @Test
    public void setPhoneNumber() {
        person.setPhoneNumber("545415154");
        personTwo.setPhoneNumber("589");
        assertEquals("545415154", person.getPhoneNumber());
        assertEquals("589", personTwo.getPhoneNumber());

    }
}