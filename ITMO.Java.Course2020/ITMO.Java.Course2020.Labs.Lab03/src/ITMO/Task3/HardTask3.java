package ITMO.Task3;


public class HardTask3 {

    public static void main(String[] args) {
        System.out.println("Сложные задания. Задание №3");

        System.out.println("Найти корень уравнения cos(x^5) + x^4 - 345.3 * x - 23 = 0 на отрезке [0; 10]\n с точностью по x не хуже чем 0.001.\n " +
                        "Используйте для этого метод деления отрезка пополам (и рекурсию).");
    double xMin = 0;
    double xMax = 10;
    double xPass = 0.001;
    double xPassReverse=1/xPass;
    int xLength = (((int)xMax-(int)xMin))*(int)xPassReverse;
        System.out.println(xLength);

        double[] myArray = new double[xLength];
        for (int i = 0; i < xLength; i++) {
            myArray[i] = xMin+i*xPass;
        }

        /*System.out.println("наш массив чисел:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i]);
            System.out.print(" ");
        }*/
        System.out.println();

        //Task3Search(double myArray[], int firstElement, int lastElement)

        System.out.println("корень уравнения:");

        int result=CodeResourses.Task3Search(myArray, 0, xLength-1);
        //double midValue= 7.037;//7.037
        //double result = Math.cos(Math.toRadians(Math.pow(midValue, 5))) + Math.pow(midValue, 4) - 345.3 * midValue - 23;
        System.out.println(myArray[result]);

        /*for (double i = 0; i <= 10; i=i+0.1) {
            double midValue= i;//7.037
            double result = Math.cos(Math.toRadians(Math.pow(midValue, 5))) + Math.pow(midValue, 4) - 345.3 * midValue - 23;
            System.out.println(i +" "+result);
        }*/

        // write your code here
    }
}