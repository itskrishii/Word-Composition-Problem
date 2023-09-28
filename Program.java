import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        FileRead("Input_01.txt");
        FileRead("Input_02.txt");
    }

    private static void FileRead(String name) {
        // Read words from the file and store them in a HashSet
        Set<String> words = new HashSet<>();
        long startTime = System.currentTimeMillis();
        StoreWords(name, words);
        long endTime = System.currentTimeMillis();

        String longestCompoundWord = LongestCompoundWord(words);
        String secondLongestCompoundWord = SecondLongestCompoundWord(words);
        
        // Print the results
        System.out.println("Longest Compound Word: " + longestCompoundWord);
        System.out.println("Second Longest Compound Word: " + secondLongestCompoundWord);
        System.out.println("Time taken to process the file: " + (endTime - startTime) + " ms");
        System.out.println();
    }

    // Procedure to read and store words in a HashSet
    private static void StoreWords(String file_Name, Set<String> words) {
        try (BufferedReader br = new BufferedReader(new FileReader(file_Name))) {
            String sentence;
            while ((sentence = br.readLine()) != null) {
                words.add(sentence);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file " + e.getMessage());
            System.exit(1);
        }
    }

    // Procedure to find the longest compound word
    private static String LongestCompoundWord(Set<String> words) {
        String longestCompoundWord = "";
        for (String word : words) {
            if (isCompound(word, words) && word.length() > longestCompoundWord.length()) {
                longestCompoundWord = word;
            }
        }
        return longestCompoundWord;
    }

    // Procedure to find the second longest compound word
    private static String SecondLongestCompoundWord(Set<String> words) {
        String longestCompoundWord = "";
        String secondLongestCompoundWord = "";
        for (String word : words) {
            if (isCompound(word, words)) {
                if (word.length() > longestCompoundWord.length()) {
                    secondLongestCompoundWord = longestCompoundWord;
                    longestCompoundWord = word;
                } else if (word.length() > secondLongestCompoundWord.length()) {
                    secondLongestCompoundWord = word;
                }
            }
        }
        return secondLongestCompoundWord;
    }

    // Procedure to check weather a word is compound
    private static boolean isCompound(String word, Set<String> words) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (words.contains(prefix) && (words.contains(suffix) || isCompound(suffix, words))) {
                return true;
            }
        }
        return false;
    }
}
