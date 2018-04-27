import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {
    Register register;

    @Before
    public void setUp() throws Exception {
        register = new Register(20);
        register.addPerson(new Person("Janko", "123456789"));
        register.addPerson(new Person("Peter", "4542345645"));
        register.addPerson(new Person("Katka", "897542664"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPerson() {
        assertEquals(3, register.getCount());
        assertEquals("Janko", register.getPerson(0).getName());
        assertEquals("123456789", register.getPerson(0).getPhoneNumber());
    }

    @Test
    public void findPersonByName()throws Exception{
        assertNull(register.findPersonByName("asdasdasdasd"));
        assertNotNull(register.findPersonByName("Janko"));
    }

    @Test
    public void findPersonByPhoneNumber() {
        assertNotNull(register.findPersonByPhoneNumber("123456789"));
        assertNotNull(register.findPersonByPhoneNumber("897542664"));
        assertNotNull(register.findPersonByPhoneNumber("897542664"));
    }



}