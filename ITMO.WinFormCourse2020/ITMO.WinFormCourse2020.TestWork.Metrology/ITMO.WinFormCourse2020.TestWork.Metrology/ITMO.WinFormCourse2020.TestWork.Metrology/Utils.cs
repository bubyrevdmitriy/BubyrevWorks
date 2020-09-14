using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ITMO.WinFormCourse2020.TestWork.Metrology
{
    public class Utils
    {
        
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double averageArray(double[] measurmentsArray)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double averageArray;
            double arraySum = 0;
            for (int i = 0; i < measurmentsArray.Length; i++)
            {
                arraySum = arraySum + measurmentsArray[i];
            }

            averageArray = arraySum / measurmentsArray.Length;

            return averageArray;
        }

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double[] ArrayDeviations(double[] measurmentsArray, double averageArrayNumber)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double[] arrayDeviations = new double[measurmentsArray.Length];

            for (int i = 0; i < measurmentsArray.Length; i++)
            {
                arrayDeviations[i] = measurmentsArray[i] - averageArrayNumber;
            }


            return arrayDeviations;
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

        public static double[] ArrayDeviationsSquared(double[] arrayDeviations)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double[] arrayDeviationsSquared = new double[arrayDeviations.Length];

            for (int i = 0; i < arrayDeviations.Length; i++)
            {
                arrayDeviationsSquared[i] = arrayDeviations[i] * arrayDeviations[i];
            }


            return arrayDeviationsSquared;
        }

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double averageArrayDeviationsSquared(double[] arrayDeviationsSquared)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double averageArrayDeviationsSquared;
            double arraySum = 0;
            for (int i = 0; i < arrayDeviationsSquared.Length; i++)
            {
                arraySum = arraySum + arrayDeviationsSquared[i];
            }

            averageArrayDeviationsSquared = arraySum / 1;

            return averageArrayDeviationsSquared;
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double criterionRomanovsky(int measurementsNumber, double radioButtonValue)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double criterionRomanovskyValue = 0;

            
            int userChoice=1 ;//конвертируем строку с консоли в число
            if (radioButtonValue==0.01) { userChoice=1; }
            if (radioButtonValue == 0.02) { userChoice = 2; }
            if (radioButtonValue == 0.05) { userChoice = 3; }
            if (radioButtonValue == 0.1) { userChoice = 4; }

            int caseSwitch = userChoice;//вводим переменную для switch

            switch (caseSwitch)//интерполяция
            {
                case 1://0.01

                    if (measurementsNumber == 4) { criterionRomanovskyValue = 1.73; }//1
                    if (4 < measurementsNumber && measurementsNumber < 6) { criterionRomanovskyValue = (measurementsNumber - 4) * (2.16 - 1.73) / (6 - 4); }//2
                    if (measurementsNumber == 6) { criterionRomanovskyValue = 2.16; }//3
                    if (6 < measurementsNumber && measurementsNumber < 8) { criterionRomanovskyValue = (measurementsNumber - 6) * (2.43 - 2.16) / (8 - 6); }//4
                    if (measurementsNumber == 8) { criterionRomanovskyValue = 2.43; }//5
                    if (8 < measurementsNumber && measurementsNumber < 10) { criterionRomanovskyValue = (measurementsNumber - 8) * (2.62 - 2.43) / (10 - 8); }//6
                    if (measurementsNumber == 10) { criterionRomanovskyValue = 2.62; }//7
                    if (10 < measurementsNumber && measurementsNumber < 12) { criterionRomanovskyValue = (measurementsNumber - 10) * (2.75 - 2.62) / (12 - 10); }//8
                    if (measurementsNumber == 12) { criterionRomanovskyValue = 2.75; }//9
                    if (12 < measurementsNumber && measurementsNumber < 15) { criterionRomanovskyValue = (measurementsNumber - 12) * (2.90 - 2.75) / (15 - 12); }//10
                    if (measurementsNumber == 15) { criterionRomanovskyValue = 2.90; }//11
                    if (15 < measurementsNumber && measurementsNumber < 20) { criterionRomanovskyValue = (measurementsNumber - 15) * (3.08 - 2.90) / (20 - 15); }//12
                    if (measurementsNumber == 20) { criterionRomanovskyValue = 3.08; }//13
                    break;

                case 2://0.02

                    if (measurementsNumber == 4) { criterionRomanovskyValue = 1.72; }//1
                    if (4 < measurementsNumber && measurementsNumber < 6) { criterionRomanovskyValue = (measurementsNumber - 4) * (2.13 - 1.72) / (6 - 4); }//2
                    if (measurementsNumber == 6) { criterionRomanovskyValue = 2.13; }//3
                    if (6 < measurementsNumber && measurementsNumber < 8) { criterionRomanovskyValue = (measurementsNumber - 6) * (2.37 - 2.13) / (8 - 6); }//4
                    if (measurementsNumber == 8) { criterionRomanovskyValue = 2.37; }//5
                    if (8 < measurementsNumber && measurementsNumber < 10) { criterionRomanovskyValue = (measurementsNumber - 8) * (2.54 - 2.37) / (10 - 8); }//6
                    if (measurementsNumber == 10) { criterionRomanovskyValue = 2.54; }//7
                    if (10 < measurementsNumber && measurementsNumber < 12) { criterionRomanovskyValue = (measurementsNumber - 10) * (2.66 - 2.54) / (12 - 10); }//8
                    if (measurementsNumber == 12) { criterionRomanovskyValue = 2.66; }//9
                    if (12 < measurementsNumber && measurementsNumber < 15) { criterionRomanovskyValue = (measurementsNumber - 12) * (2.80 - 2.66) / (15 - 12); }//10
                    if (measurementsNumber == 15) { criterionRomanovskyValue = 2.80; }//11
                    if (15 < measurementsNumber && measurementsNumber < 20) { criterionRomanovskyValue = (measurementsNumber - 15) * (2.96 - 2.80) / (20 - 15); }//12
                    if (measurementsNumber == 20) { criterionRomanovskyValue = 2.96; }//13
                    break;

                case 3://0.05

                    if (measurementsNumber == 4) { criterionRomanovskyValue = 1.71; }//1
                    if (4 < measurementsNumber && measurementsNumber < 6) { criterionRomanovskyValue = (measurementsNumber - 4) * (2.10 - 1.71) / (6 - 4); }//2
                    if (measurementsNumber == 6) { criterionRomanovskyValue = 2.10; }//3
                    if (6 < measurementsNumber && measurementsNumber < 8) { criterionRomanovskyValue = (measurementsNumber - 6) * (2.27 - 2.10) / (8 - 6); }//4
                    if (measurementsNumber == 8) { criterionRomanovskyValue = 2.27; }//5
                    if (8 < measurementsNumber && measurementsNumber < 10) { criterionRomanovskyValue = (measurementsNumber - 8) * (2.41 - 2.27) / (10 - 8); }//6
                    if (measurementsNumber == 10) { criterionRomanovskyValue = 2.41; }//7
                    if (10 < measurementsNumber && measurementsNumber < 12) { criterionRomanovskyValue = (measurementsNumber - 10) * (2.52 - 2.41) / (12 - 10); }//8
                    if (measurementsNumber == 12) { criterionRomanovskyValue = 2.52; };//9
                    if (12 < measurementsNumber && measurementsNumber < 15) { criterionRomanovskyValue = (measurementsNumber - 12) * (2.64 - 2.52) / (15 - 12); }//10
                    if (measurementsNumber == 15) { criterionRomanovskyValue = 2.64; }//11
                    if (15 < measurementsNumber && measurementsNumber < 20) { criterionRomanovskyValue = (measurementsNumber - 15) * (2.78 - 2.64) / (20 - 15); }//12
                    if (measurementsNumber == 20) { criterionRomanovskyValue = 2.78; }//13
                    break;

                case 4://0.10

                    if (measurementsNumber == 4) { criterionRomanovskyValue = 1.69; }//1
                    if (4 < measurementsNumber && measurementsNumber < 6) { criterionRomanovskyValue = (measurementsNumber - 4) * (2.00 - 1.69) / (6 - 4); }//2
                    if (measurementsNumber == 6) { criterionRomanovskyValue = 2.00; }//3
                    if (6 < measurementsNumber && measurementsNumber < 8) { criterionRomanovskyValue = (measurementsNumber - 6) * (2.17 - 2.00) / (8 - 6); }//4
                    if (measurementsNumber == 8) { criterionRomanovskyValue = 2.17; }//5
                    if (8 < measurementsNumber && measurementsNumber < 10) { criterionRomanovskyValue = (measurementsNumber - 8) * (2.29 - 2.17) / (10 - 8); }//6
                    if (measurementsNumber == 10) { criterionRomanovskyValue = 2.29; }//7
                    if (10 < measurementsNumber && measurementsNumber < 12) { criterionRomanovskyValue = (measurementsNumber - 10) * (2.39 - 2.29) / (12 - 10); }//8
                    if (measurementsNumber == 12) { criterionRomanovskyValue = 2.39; }//9
                    if (12 < measurementsNumber && measurementsNumber < 15) { criterionRomanovskyValue = (measurementsNumber - 12) * (2.49 - 2.39) / (15 - 12); }//10
                    if (measurementsNumber == 15) { criterionRomanovskyValue = 2.49; }//11
                    if (15 < measurementsNumber && measurementsNumber < 20) { criterionRomanovskyValue = (measurementsNumber - 15) * (2.62 - 2.49) / (20 - 15); }//12
                    if (measurementsNumber == 20) { criterionRomanovskyValue = 2.62; }//13
                    break;

                default:

                    Console.WriteLine("Default case");
                    break;
            }

            return criterionRomanovskyValue;
        }
        //----------------------------------------------------------------------------------------------------------------------------------------------

        public static double[] ArrayRomanovskyBeta(double[] arrayDeviations, double standardDeviation)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double[] ArrayRomanovskyBeta = new double[arrayDeviations.Length];

            for (int i = 0; i < arrayDeviations.Length; i++)
            {
                ArrayRomanovskyBeta[i] = Math.Abs(arrayDeviations[i]) / standardDeviation;
            }

            return ArrayRomanovskyBeta;
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------
        public static double[] ArrayRomanovskyResult(double[] measurmentsArrayRomanovsky, double[] measurmentsArrayRomanovskyBeta, double criterionRomanovsky)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double[] ArrayRomanovskyResult = new double[measurmentsArrayRomanovsky.Length];

            for (int i = 0; i < measurmentsArrayRomanovsky.Length; i++)
            {
                if (measurmentsArrayRomanovskyBeta[i] > criterionRomanovsky)
                { ArrayRomanovskyResult[i] = 0; }
                else
                { ArrayRomanovskyResult[i] = measurmentsArrayRomanovsky[i]; }
            }

            return ArrayRomanovskyResult;
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double criterionCharlier(int measurementsNumber)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double criterionCharlierValue = 0;

            if (measurementsNumber <= 5) { criterionCharlierValue = 1.3; }//1
            if (5 < measurementsNumber && measurementsNumber < 10) { criterionCharlierValue = (measurementsNumber - 5) * (1.65 - 1.3) / (10 - 5); }//2
            if (measurementsNumber == 10) { criterionCharlierValue = 1.65; }//3
            if (10 < measurementsNumber && measurementsNumber < 20) { criterionCharlierValue = (measurementsNumber - 10) * (1.96 - 1.65) / (20 - 10); }//4
            if (measurementsNumber == 20) { criterionCharlierValue = 1.96; }//5
            if (20 < measurementsNumber && measurementsNumber < 30) { criterionCharlierValue = (measurementsNumber - 20) * (2.13 - 1.96) / (30 - 20); }//6
            if (measurementsNumber == 30) { criterionCharlierValue = 2.13; }//7
            if (30 < measurementsNumber && measurementsNumber < 40) { criterionCharlierValue = (measurementsNumber - 30) * (2.24 - 2.13) / (40 - 30); }//8
            if (measurementsNumber == 40) { criterionCharlierValue = 2.24; }//9
            if (40 < measurementsNumber && measurementsNumber < 50) { criterionCharlierValue = (measurementsNumber - 40) * (2.32 - 2.24) / (50 - 40); }//10
            if (measurementsNumber == 50) { criterionCharlierValue = 2.32; }//11
            if (50 < measurementsNumber && measurementsNumber < 100) { criterionCharlierValue = (measurementsNumber - 50) * (2.58 - 2.32) / (100 - 50); }//12
            if (measurementsNumber >= 100) { criterionCharlierValue = 2.58; }//13


            return criterionCharlierValue;
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double[] ArrayCharlierResult(double[] measurmentsArrayCharlier, double[] measurmentsArrayCharlierDeviations, double KS)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double[] ArrayCharlierResult = new double[measurmentsArrayCharlier.Length];

            for (int i = 0; i < measurmentsArrayCharlier.Length; i++)
            {
                if (Math.Abs(measurmentsArrayCharlierDeviations[i]) > KS)
                { ArrayCharlierResult[i] = 0; }
                else
                { ArrayCharlierResult[i] = measurmentsArrayCharlier[i]; }
            }

            return ArrayCharlierResult;
        }
        
        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------
        public static double Result(double[] measurmentsArray)//название метода, а также тип, количество и название передаваемых в метод аргументов
        {
            double averageArray;
            double arraySum = 0;
            int arrayLength = 0;
            for (int i = 0; i < measurmentsArray.Length; i++)
            {
                if (measurmentsArray[i] != 0)
                {
                    arrayLength++;

                    arraySum = arraySum + measurmentsArray[i];
                }

            }

            averageArray = arraySum / arrayLength;

            return averageArray;
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------


    }
}
