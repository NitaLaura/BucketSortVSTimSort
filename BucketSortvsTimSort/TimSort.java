public class TimSort {
    private static int MIN_MERGE = 32;
    public static int comparacoes = 0;
    public static int movimentacoes = 0;

    public static void sort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return;
        }

        // Caso o array seja pequeno, usa insertion sort diretamente
        if (n <= MIN_MERGE) {
            insertionSort(array, 0, n);
            return;
        }

        // Divide o array em blocos de tamanho MIN_MERGE e ordena-os usando insertion sort
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(array, i, Math.min(i + MIN_MERGE, n));
        }

        // Agora combina os blocos usando merge sort
        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }
    }

    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= left && array[j] > temp) {
                comparacoes++;
                array[j + 1] = array[j];
                movimentacoes++;
                j--;
            }
            comparacoes++; // para o último caso de falha no while
            array[j + 1] = temp;
            movimentacoes++;
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            comparacoes++;
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
            movimentacoes++;
        }
        while (i <= mid) {
            temp[k++] = array[i++];
            movimentacoes++;
        }
        while (j <= right) {
            temp[k++] = array[j++];
            movimentacoes++;
        }
        for (i = left, k = 0; i <= right; i++, k++) {
            array[i] = temp[k];
            movimentacoes++;
        }
    }
}
