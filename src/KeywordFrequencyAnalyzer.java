import java.util.*;
import java.io.*;

public class KeywordFrequencyAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter input file name (e.g., input.txt): ");
        String fileName = sc.nextLine();
        System.out.print("Enter number of top frequent words to display: ");
        int N = sc.nextInt();

        Map<String, Integer> freqMap = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNext()) {
                String word = fileScanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (word.isEmpty()) continue;
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

            pq.addAll(freqMap.entrySet());

            System.out.println("\nTop " + N + " most frequent words:");
            for (int i = 0; i < N && !pq.isEmpty(); i++) {
                Map.Entry<String, Integer> entry = pq.poll();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        sc.close();
    }
}
