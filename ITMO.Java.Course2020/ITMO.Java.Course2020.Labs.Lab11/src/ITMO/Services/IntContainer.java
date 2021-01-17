package ITMO.Services;

public class IntContainer implements Container {

    private int i;//строки

    private int j;//столбцы

    private int[][] array;


    //печать массива
    public void printArray() {
        for (int i1 = 0; i1 < array.length; i1++) {

            for (int j1 = 0; j1 < array[i1].length; j1++) {
                System.out.print(array[i1][j1] + "\t");
            }
            System.out.println();
        }
    }

    public IntContainer(int i, int j) {
        this.i = i;
        this.j = j;
        this.array = new int[i][j];
        //заполнение массива
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                //array[i1][j1] =i1*j1;
                array[i1][j1] =(int) (10*Math.random());
            }
        }
    }

    public IntContainer(int j) {
        this.i = 1;
        this.j = j;
        this.array = new int[i][j];
        //заполнение массива
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                //array[i1][j1] =i1*j1;
                array[i1][j1] =(int) (10*Math.random());
            }
        }
    }

    @Override
    public Iterator getIterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator {

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
        public Object next() {

            if (currentColumn == array[currentRow].length) {
                currentColumn = 0;
                currentRow++;
            }
            return array[currentRow][currentColumn++];

        }
    }

}
