package ITMO.Services;

public class KContainer implements Container {

    private int i;//строки

    private int j;//столбцы

    private K[][] array;

    public K[][] getArray() {
        return array;
    }

    //печать массива
    public void printArray() {
        for (int i1 = 0; i1 < array.length; i1++) {

            for (int j1 = 0; j1 < array[i1].length; j1++) {
                System.out.print(array[i1][j1].getValue() + "\t");
            }
            System.out.println();
        }
    }

    public KContainer(int i, int j) {
        this.i = i;
        this.j = j;
        this.array = new K[i][j];
        //заполнение массива
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                //array[i1][j1] =i1*j1;
                array[i1][j1] =new K((int) (10*Math.random()));
            }
        }
    }

    public KContainer(int j) {
        this.i = 1;
        this.j = j;
        this.array = new K[i][j];
        //заполнение массива
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                //array[i1][j1] =i1*j1;
                array[i1][j1] =new K((int) (10*Math.random()));
            }
        }
    }

    @Override
    public Iterator getIterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator {
        //int index;
        private int currentRow = 0;
        private int currentColumn = 0;

        @Override
        public boolean hasNext() {

            if (currentRow + 1 == array.length) {
                return currentColumn < array[currentRow].length;
            }
            return currentRow < array.length;
        }

        @Override
        public K next() {

            if (currentColumn == array[currentRow].length) {
                currentColumn = 0;
                currentRow++;
            }
            return array[currentRow][currentColumn++];

        }
    }

}
