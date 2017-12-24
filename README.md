**Simple application for Address parsing**

The goal of the application is to read one line addresses from a file and write the output in simple `{"street name", "house number"}` format.

From project root run `mvn package` to build the jar.

Run 
`java -cp target\address-parser-<version>.jar my.company.Main test.txt output.txt`
to execute.

If second parameter is not provided it will create an `output.txt` file within user's home directory