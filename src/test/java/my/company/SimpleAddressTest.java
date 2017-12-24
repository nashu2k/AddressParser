package my.company;


import my.company.parser.AddressParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;


public class SimpleAddressTest {

    AddressParser parser = new AddressParser();

    @Before
    public void setUp() {
        System.out.println("Starting simple test\n" +
                "-------------------------");
    }


    @Test
    public void testNullArguments() {
        try {
            parser.parseAddress(null);
            fail("Should return NPE for null address");
        } catch (NullPointerException e) {}

    }

    @Test
    public void testInvalidAddress() {
        // TODO: should it be split into multiple negavtive scenarios?
        try {
            parser.parseAddress("Bd");
            fail("Should return exception for invalid address");
        } catch (IllegalArgumentException e) {
        }
        try {
            parser.parseAddress("this100stringdoesn'thaveproperaddressdelimiter");
            fail("Should return exception for invalid address");
        } catch (IllegalArgumentException e) {
        }
        try {
            parser.parseAddress("stringwithnonumberinit");
            fail("Should return exception for invalid address");
        } catch (IllegalArgumentException e) {
        }
        try {
            // FIXME: check the assumption that an address that contains only numbers is invalid
            parser.parseAddress("243 49 34 2145");
            fail("Should return exception for invalid address");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSimpleAddress() {
        assertArrayEquals(parser.parseAddress("Winterallee 3"), new String[]{"Winterallee", "3"});
    }

    @Test
    public void testSimpleAddressWithSpace() {
        assertArrayEquals(parser.parseAddress("Am Bächle 23"), new String[]{"Am Bächle", "23"});
    }

    @Test
    public void testSimpleAddressWithGermanCharacter() {
        assertArrayEquals(parser.parseAddress("Musterstraße 45"), new String[]{"Musterstraße", "45"});
    }

    @Test
    public void testSimpleAddressWithNumberAndChar() {
        assertArrayEquals(parser.parseAddress("Blaufeldweg 123B"), new String[]{"Blaufeldweg", "123B"});
    }

    @Test
    public void testSimpleAddressWithNumberAndCharAndSpace() {
        assertArrayEquals(parser.parseAddress("Auf der Vogelwiese 23 B"), new String[]{"Auf der Vogelwiese", "23 B"});
    }

    @After
    public void tearDown() {
        System.out.println("Test Ended");
    }
}