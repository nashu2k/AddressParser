package my.company;


import my.company.parser.AddressParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class LocalizedAddressTest {

    AddressParser parser = new AddressParser();

    @Before
    public void setUp() throws Exception {
        System.out.println("Starting localization test\n" +
                "-------------------------");
    }

    @Test
    public void testNullArguments() {

        try {
            parser.parseAddressWithLocalization(null, null);
            fail("Should return NPE for null address and geoloc");
        } catch (NullPointerException e) {}

        try {
            parser.parseAddressWithLocalization("some addres", null);
            fail("Should return NPE for address and null geoloc");
        } catch (NullPointerException e) {}
    }

    @Test
    public void testUSAddress() {
        assertArrayEquals(parser.parseAddressWithLocalization("200 Brooklyn Avenue", "us"),
                new String[]{"Brooklyn Avenue", "200"});
    }

    @Test
    public void testFRAddress() {
        assertArrayEquals(parser.parseAddressWithLocalization("4, rue de la revolution", "fr"),
                new String[]{"rue de la revolution", "4"});
    }

    @Test
    public void testESAddress() {
        assertArrayEquals(parser.parseAddressWithLocalization("Calle Aduana, 29", "es"),
                new String[]{"Calle Aduana", "29"});
    }

    @Test
    public void testESAddressWithNo() {
        assertArrayEquals(parser.parseAddressWithLocalization("Calle 39 No 1540", "es"),
                new String[]{"Calle 39", "No 1540"});
    }

    @Test
    public void testNoDelimiterWithDigits() {
        assertArrayEquals(parser.parseAddressWithLocalization("No New Addresses 12B", "es"),
                new String[]{"No New Addresses", "12B"});
    }

    @After

    public void tearDown() throws Exception {
        System.out.println("Test Ended");
    }
}