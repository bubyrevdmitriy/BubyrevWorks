package SortIt;

public class SortSettings {
    //класс, хранящий исходные данные для сортировки, режимы сортировки, название файлов

    private boolean sortAscending = true;//сортировка по возрастанию
    private boolean sortDescending = false;//сортировка по убыванию
    private boolean sortStrings = false;//сортируем строки
    private boolean sortIntegers = false;//сортируем числа
    private String outPutFileName;//имя исходящего файла-результата
    private String[] inputFileNames;//массив входяхий файлов

    public SortSettings() {
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {

        this.sortAscending = sortAscending;
        this.sortDescending = !sortAscending;
    }

    public boolean isSortDescending() {
        return sortDescending;
    }

    public void setSortDescending(boolean sortDescending) {

        this.sortDescending = sortDescending;
        this.sortAscending = !sortDescending;
    }

    public boolean isSortStrings() {
        return sortStrings;
    }

    public void setSortStrings(boolean sortStrings) {

        this.sortStrings = sortStrings;
        this.sortIntegers = !sortStrings;
    }

    public boolean isSortIntegers() {
        return sortIntegers;
    }

    public void setSortIntegers(boolean sortIntegers) {

        this.sortIntegers = sortIntegers;
        this.sortStrings = !sortIntegers;
    }

    public String getOutPutFileName() {
        return outPutFileName;
    }

    public void setOutPutFileName(String outPutFileName) {
        this.outPutFileName = outPutFileName;
    }

    public String[] getInputFileNames() {
        return inputFileNames;
    }

    public void setInputFileNames(String[] inputFileNames) {
        this.inputFileNames = inputFileNames;
    }
}
