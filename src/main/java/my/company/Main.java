package my.company;

import my.company.parser.AddressParser;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Bad usage: first argument must be provided");
        } else if (args.length == 1) {
            readFromFileAndWriteToAnotherFile(args[0], System.getProperty("user.home") + "\\output.txt");
        } else if (args.length == 2)
            readFromFileAndWriteToAnotherFile(args[0], args[1]);
    }

    private static void readFromFileAndWriteToAnotherFile(String input, String output) throws IOException {
        AddressParser parser = new AddressParser();
        BufferedReader inputFile = null;
        BufferedWriter outputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            outputFile = new BufferedWriter(new FileWriter(output));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        while ((line = inputFile.readLine()) != null) {
            System.out.println(line);
            outputFile.write(Arrays.toString(parser.parseAddress(line)));
            outputFile.newLine();
        }
        inputFile.close();
        outputFile.close();
    }
}
