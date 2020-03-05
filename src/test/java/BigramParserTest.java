import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class BigramParserTest {

    private final InputStream sysIn = System.in;
    private final PrintStream sysOut = System.out;

    @After
    public void resetSystemIO() {
        System.setIn(sysIn);
        System.setOut(sysOut);
    }

    @Test
    public void bigramParserTest1() throws FileNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("src/test/java/testFile1.txt".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BigramParser.main(new String[0]);

        assertEquals("Input file to be read:\nBigrams found:\n\"the quick\" 2\n\"quick brown\" 1\n\"brown fox\" 1\n\"fox and\" 1\n\"and the\" 1\n\"quick blue\" 1\n\"blue hare\" 1\n", outputStream.toString());
    }

    @Test
    public void multipleSameBigramTest() throws FileNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("src/test/java/testFile2.txt".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BigramParser.main(new String[0]);

        assertEquals("Input file to be read:\nBigrams found:\n\"sentence sentence\" 6\n", outputStream.toString());
    }
}
