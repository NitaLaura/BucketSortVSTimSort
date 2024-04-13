import java.io.*;
import java.util.*;

public class Comparacao {
    public static List<Integer> readDataFromFile(String filename) throws IOException {
        File file = new File(filename);
        List<Integer> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String st = br.readLine(); // Lê a única linha
            st = st.substring(1, st.length() - 1); // Remove colchetes
            String[] numbers = st.split(", ");
            for (String number : numbers) {
                data.add(Integer.parseInt(number.trim()));
            }
        }
        return data;
    }

    public static void saveDataToFile(int[] data, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < data.length; i++) {
                bw.write(data[i] + (i < data.length - 1 ? ", " : ""));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> data = readDataFromFile("dados500_mil.txt");
        int[] original = data.stream().mapToInt(i -> i).toArray();

        // BucketSort
        int[] bucketArray = original.clone();
        long startTime = System.currentTimeMillis();
        BucketSort.sort(bucketArray);
        long bucketTime = System.currentTimeMillis() - startTime;
        saveDataToFile(bucketArray, "sorted_bucketSort.txt");

        // TimSort
        int[] timArray = original.clone();
        startTime = System.currentTimeMillis();
        TimSort.sort(timArray);
        long timTime = System.currentTimeMillis() - startTime;
        saveDataToFile(timArray, "sorted_timSort.txt");

        System.out.println("BucketSort Tempo de Execução: " + bucketTime + " ms");
        System.out.println("BucketSort Quantidade de comparações: " + BucketSort.comparacoes);
        System.out.println("BucketSort Quantidade de movimentações: " + BucketSort.movimentacoes);

        System.out.println("TimSort Tempo de Execução: " + timTime + " ms");
        System.out.println("TimSort Quantidade de comparações: " + TimSort.comparacoes);
        System.out.println("TimSort Quantidade de movimentações: " + TimSort.movimentacoes);
    }
}
