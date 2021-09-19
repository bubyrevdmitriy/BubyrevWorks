package SortIt;

public class MergeSortForStringsArray {
    // класс содержит методы для сортировки строкового массива данных, целиком помещающихся в оперативную память

    public static void mergeSortStrings(String[] a, int n, String condition) {
        // проверяем не 1 ли элемент в массиве?
        if (n < 2) {
            return;
        }

        int mid = n / 2;// находим средний элемент
        String[] l = new String[mid];// создаем массив для левой части переданного в метод массива
        String[] r = new String[n - mid];// создаем массив для правой части переданного в метод массива

        // копируем левую часть от начала до середины
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        // копируем правую часть от середины до конца массива
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSortStrings(l, mid, condition); // рекурсивно запускаем метод с левым массивом
        mergeSortStrings(r, n - mid, condition); // рекурсивно запускаем метод с правым массивом

        mergeStrings(a, l, r, mid, n - mid, condition);
    }

    public static void mergeStrings( String[] a, String[] l, String[] r, int left, int right, String condition) {
        // в массив a мы вписываем элементы из массивов l и r, left и right длины массивов l и r
        int i = 0, j = 0, k = 0;// i-индекс в массиве-источнике l, j-индекс в массиве-источнике r, k-индекс в массиве-записе a
        while (i < left && j < right) { //пока остались непрочтенные строки в массиве-источнике l и r
            if (condition.equals("ascending")) {
                if (l[i] .compareTo(r[j]) < 0 ) { // сортировка по возрастанию
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
            if (condition.equals("descending")) {
                if (l[i] .compareTo(r[j]) > 0 ) { // сортировка по убыванию
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
        }
        while (i < left) { // пока остались непрочтенные строки в массиве-источнике l
            a[k++] = l[i++];
        }
        while (j < right) { //пока остались непрочтенные строки в массиве-источнике r
            a[k++] = r[j++];
        }
    }
}