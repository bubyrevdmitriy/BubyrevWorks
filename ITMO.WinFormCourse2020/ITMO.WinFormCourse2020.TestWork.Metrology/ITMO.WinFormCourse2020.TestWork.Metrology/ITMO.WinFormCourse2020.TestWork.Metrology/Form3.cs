using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ITMO.WinFormCourse2020.TestWork.Metrology
{
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
        }
        
        private static double[] measurmentsArrayCharlier;
        
        static double averageArrayCharlier;//среднее значение измерений(до корректировки)!!!!!!!!!!!!!!!!!

        static double[] measurmentsArrayCharlierDeviations;

        static double[] measurmentsArrayCharlierDeviationsSquared;

        static double sumArrayCharlierDeviationsSquared;//сумма квадратов отклонений!!!!!!

        static double standardDeviationCharlier;//среднеквадратичное отклонение!!!!!!!!!!!!!!
                
        static double KS;//KS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        static double[] measurmentsArrayCharlierResult;

        static double сharlierResult;//среднее значение измерений(после корректировки)!!!!!!!!!!!!!!!!       

        private void button1_Click(object sender, EventArgs e)
        {
            Data.form3Show = 0; 
            this.Close();
        }

        private void Form3_Load(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Data.criterionCharlierButtonClick++;
            Data.criterionCharlier = Utils.criterionCharlier(Data.countInputNumeric); ;
            String label2Text = Data.criterionCharlier.ToString();
            label2.Text = label2Text;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            //кнопка решить задачу
            if(Data.criterionCharlierButtonClick>0)
            {
                measurmentsArrayCharlier = Measurments.measurmentsArrayClassMeasurments;

                averageArrayCharlier = Utils.averageArray(Form3.measurmentsArrayCharlier);//среднее значение измерений(до корректировки)!!!!!!!!!!!!!!!!!

                measurmentsArrayCharlierDeviations = Utils.ArrayDeviations(measurmentsArrayCharlier, Form3.averageArrayCharlier);

                measurmentsArrayCharlierDeviationsSquared = Utils.ArrayDeviationsSquared(Form3.measurmentsArrayCharlierDeviations);

                sumArrayCharlierDeviationsSquared = Utils.averageArrayDeviationsSquared(Form3.measurmentsArrayCharlierDeviationsSquared);//сумма квадратов отклонений!!!!!!

                standardDeviationCharlier = Math.Sqrt(sumArrayCharlierDeviationsSquared / (Data.countInputNumeric - 1));//среднеквадратичное отклонение!!!!!!!!!!!!!!

                KS = Data.criterionCharlier * standardDeviationCharlier;//KS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                measurmentsArrayCharlierResult = Utils.ArrayCharlierResult(Form3.measurmentsArrayCharlier, Form3.measurmentsArrayCharlierDeviations, Form3.KS);

                сharlierResult = Utils.Result(Form3.measurmentsArrayCharlierResult);//среднее значение измерений(после корректировки)!!!!!!!!!!!!!!!!!


                String label7Text = averageArrayCharlier.ToString("0.00000");//среднее значение до исправления ошибок
                label7.Text = label7Text;

                String label5Text = сharlierResult.ToString("0.00000");//среднее значение после исправления ошибок
                label5.Text = label5Text;

                String label11Text = standardDeviationCharlier.ToString("0.00000");//среднеквадратичное отклонение
                label11.Text = label11Text;

                String label12Text = KS.ToString("0.00000");//K*S
                label12.Text = label12Text;

                //--------------------------------------------------------------------------
                dataGridView1.RowCount = Data.countInputNumeric + 2;//строки
                dataGridView1.ColumnCount = 4;//столбцы
                for (int i = 1; i < dataGridView1.RowCount - 1; i++)
                {
                    dataGridView1.Rows[i].Cells[0].Value = i.ToString();
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i + 1].Cells[1].Value = measurmentsArrayCharlier[i].ToString();
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i + 1].Cells[2].Value = measurmentsArrayCharlierDeviations[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i + 1].Cells[3].Value = measurmentsArrayCharlierDeviationsSquared[i].ToString("0.00000");
                }
                dataGridView1.Rows[0].Cells[0].Value = "№";
                dataGridView1.Rows[0].Cells[1].Value = "Результат измерения";
                dataGridView1.Rows[0].Cells[2].Value = "Отклонения";
                dataGridView1.Rows[0].Cells[3].Value = "Квадрат отклонений";

                dataGridView1.Rows[Data.countInputNumeric + 1].Cells[1].Value = averageArrayCharlier.ToString("0.00000");
                dataGridView1.Rows[Data.countInputNumeric + 1].Cells[3].Value = sumArrayCharlierDeviationsSquared.ToString("0.00000");

                //----------------------------------------------------------------------------
                dataGridView2.RowCount = Data.countInputNumeric + 2;//строки
                dataGridView2.ColumnCount = 3;//столбцы
                for (int i = 1; i < dataGridView2.RowCount - 1; i++)
                {
                    dataGridView2.Rows[i].Cells[0].Value = i.ToString();
                }
                for (int i = 0; i < dataGridView2.RowCount - 2; i++)
                {
                    dataGridView2.Rows[i + 1].Cells[1].Value = measurmentsArrayCharlier[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView2.RowCount - 2; i++)
                {
                    dataGridView2.Rows[i + 1].Cells[2].Value = measurmentsArrayCharlierResult[i].ToString("0.00000");
                }
                dataGridView2.Rows[0].Cells[0].Value = "№";
                dataGridView2.Rows[0].Cells[1].Value = "Результат измерения";
                dataGridView2.Rows[0].Cells[2].Value = "Истинные результаты измерения";

                dataGridView2.Rows[Data.countInputNumeric + 1].Cells[1].Value = averageArrayCharlier.ToString("0.00000");
                dataGridView2.Rows[Data.countInputNumeric + 1].Cells[2].Value = сharlierResult.ToString("0.00000");


                //----------------------------------------------------------------------------
                if (сharlierResult > 0)
                {
                    label13.Text = " ";
                    label14.Text = " ";
                }
                else
                {
                    label13.Text = "'не число' соответствует полностью провальной серии";
                    label14.Text = "измерений.  Точность измерений должна быть увеличена.";
                }

            }

        }

        private void label7_Click(object sender, EventArgs e)
        {
            //среднее значение до исправления ошибок
        }

        private void label5_Click(object sender, EventArgs e)
        {
            //среднее значение после исправления ошибок
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            //таблица до корректировки
        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            //таблица после корректировки
        }

        private void label11_Click(object sender, EventArgs e)
        {
            //среднеквадратичное отклонение
        }

        private void label12_Click(object sender, EventArgs e)
        {
            //K*S
        }

        private void label15_Click(object sender, EventArgs e)
        {

        }

        private void label14_Click(object sender, EventArgs e)
        {
            ///222///измерений.  Точность измерени должна быть увеличена.
        }

        private void label13_Click(object sender, EventArgs e)
        {
            ///111///"не число" соответствует полностью провальной серии 
        }
    }
}
