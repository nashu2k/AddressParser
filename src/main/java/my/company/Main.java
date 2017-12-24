package my.company;

import my.company.parser.AddressParser;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AddressParser parser = new AddressParser();
        if (args.length < 1) {
            System.out.println("Bad usage: first argument must be provided");
        } else if (args.length == 1) {
            BufferedReader file = null;
            try {
                file = new BufferedReader(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line;
            while ((line = file.readLine()) != null) {
                System.out.println(line);
                parser.parseAddress(line);
            }
        } else if (args.length == 2)
            System.out.println(parser.parseAddressWithLocalization(args[0], args[1]).toString());
    }
}
