// Sorts.java
public class Sorts {
    public static void insertionSort(ScoreEntry[] arr) {
        for (int i = 1; i < arr.length; i++) {
            ScoreEntry key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].score < key.score) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void quickSort(ScoreEntry[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(ScoreEntry[] arr, int low, int high) {
        int pivot = arr[high].score;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].score > pivot) {
                i++;
                ScoreEntry tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        ScoreEntry tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    public static int binarySearchByName(ScoreEntry[] arr, String name) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            int cmp = arr[m].player.compareToIgnoreCase(name);
            if (cmp == 0) return m;
            if (cmp < 0) l = m + 1; else r = m - 1;
        }
        return -1;
    }
}
