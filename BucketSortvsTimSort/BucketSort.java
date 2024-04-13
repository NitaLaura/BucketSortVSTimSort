import java.util.*;

public class BucketSort {
    public static int comparacoes = 0;
    public static int movimentacoes = 0;

    public static void sort(int[] array) {
        int maxValue = array[0];
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            comparacoes += 2; // Duas comparações por iteração
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }

        int bucketCount = (maxValue - minValue) / array.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int value : array) {
            int bucketIndex = (value - minValue) / array.length;
            buckets.get(bucketIndex).add(value);
            movimentacoes++; // Adiciona movimentação por cada adição a um bucket
        }

        int currentIndex = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int value : bucket) {
                array[currentIndex++] = value;
                movimentacoes++; // Adiciona movimentação por cada elemento colocado de volta no array
            }
        }
    }
}
