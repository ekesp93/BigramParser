import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BigramParser {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // Get input file
        System.out.println("Input file to be read:");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        scanner.close();

        // Read input file and convert to string
        StringBuilder fileStringBuilder = new StringBuilder();
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            fileStringBuilder.append(fileScanner.nextLine());
        }
        fileScanner.close();

        Map<String,Integer> bigramMap = findBigrams(fileStringBuilder.toString());

        // Output results
        System.out.println("Bigrams found:");
        bigramMap.forEach((key, value) -> System.out.println("\"" + key + "\" " + value));
    }

    /**
     * Finds out how many of each bigram are in a string
     * @param input
     * @return Map of bigrams and number of times they appear
     */
    private static Map<String,Integer> findBigrams(String input) {
        Map<String,Integer> bigramCountMap = new LinkedHashMap<>();

        // Split based on any non alphanumeric character
        String[] wordArray = input.split("\\P{Alpha}+");

        // Populate the map while checking if the bigram is already in it, if so, increment the value
        for (int i = 1; i < wordArray.length; i++) {
            String bigram = wordArray[i - 1].toLowerCase() + " " + wordArray[i].toLowerCase();
            if (bigramCountMap.containsKey(bigram)) {
                bigramCountMap.put(bigram, bigramCountMap.get(bigram) + 1);
            } else {
                bigramCountMap.put(bigram, 1);
            }
        }

        return bigramCountMap;
    }
}
