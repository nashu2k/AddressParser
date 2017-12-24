package my.company.parser;


public class AddressParser {

    public String[] parseAddress(String address) {
        if (address == null) {
            throw new NullPointerException("String address must not be null");
        }
        if (!containsBasicDelimiter(address) || !containsNumber(address) || containsOnlyNumbers(address) || address.length() < 3)
            throw new IllegalArgumentException("Invalid given address");
        return parseAddressWithLocalization(address, "de");
    }

    public String[] parseAddressWithLocalization(String address, String geoloc) {
        if (address == null) {
            throw new NullPointerException("String address must not be null");
        }
        if (geoloc == null) {
            throw new NullPointerException("Localization option must not be null");
        }
        switch (geoloc) {
            // TODO: find a better implementation of various inputs / country standards
            case ("de"):
                return splitAddress(address);
            case ("fr"):
                return splitAddress(address);
            case ("us"):
                return splitAddress(address);
            case ("es"):
                return splitAddress(address);
            default:
                return new String[]{address};
        }
    }

    /**
     * @param address - one line string for address
     * @return "Normalized" address without punctuation
     */
    private String normalizeAddress(String address) {
        String normedAddress = address;
        if ((address.contains(",") || address.contains(".")) && !address.contains(" "))
            normedAddress = address.replaceAll(",", " ");
        else if ((address.contains(",") || address.contains(".")) && address.contains(" "))
            normedAddress = address.replaceAll(",", "");
        return normedAddress;
    }

    /**
     * @param address - one line string for address
     * @return if address contains number
     */
    private boolean containsNumber(String address) {
        return address.matches(".*\\d+.*");
    }

    private boolean containsBasicDelimiter(String address) {
        // TODO check if other delimiters are used
        return address.contains(" ") || address.contains(",");
    }

    private boolean containsStringDelimiter(String address, String delim) {
        return !address.startsWith(delim) && address.matches("(?i:.*" + delim + ".*)");
    }

    private boolean containsOnlyNumbers(String address) {
        return address.replaceAll("\\s+", "").matches("^[0-9]*$");
    }


    /**
     * @param address - one line string for address
     * @return the address split in an array {"street name", "house number"}
     */
    private String[] splitAddress(String address) {
        if (containsStringDelimiter(address, " no "))
            return splitAfterString(address, "no");
        else if (containsStringDelimiter(address, " nr "))
            return splitAfterString(address, " nr ");
        else if (Character.isDigit(address.charAt(0)))
            return splitWithNumberFirst(address);
        else
            return splitWithNumberLast(address);
    }

    private String[] splitAfterString(String address, String exp) {
        return normalizeAddress(address).split("\\s+(?=" + exp + ")");
    }

    private String[] splitWithNumberFirst(String address) {
        String[] splitted = normalizeAddress(address).split(" ", 2);
        String houseNo = splitted[0];
        String streetName = splitted[1];
        return new String[]{streetName, houseNo};
    }

    private String[] splitWithNumberLast(String address){
        return normalizeAddress(address).split("\\s+(?=\\d)");
    }
}
