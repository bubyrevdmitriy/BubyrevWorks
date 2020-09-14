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
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private static double[] measurmentsArrayRomanovsky = Measurments.measurmentsArrayClassMeasurments;


        static double averageArrayRomanovsky;//среднее значение измерений(до корректировки)

        static double[] measurmentsArrayRomanovskyDeviations;

        static double[] measurmentsArrayRomanovskyDeviationsSquared;

        static double sumArrayRomanovskyDeviationsSquared;//сумма квадратов отклонений

        static double standardDeviationRomanovsky;//среднеквадратичное отклонение

        static double[] measurmentsArrayRomanovskyBeta;

        static double[] measurmentsArrayRomanovskyResult;

        static double RomanovskyResult;//среднее значение измерений(после корректировки)!!!!!!!!!!!!!!!!!

        
        private void button1_Click(object sender, EventArgs e)
        {
            Data.form2Show = 0;
            this.Close();
        }

        private void Form2_Load(object sender, EventArgs e)
        {


        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (Data.levelValueRadioButtonClick == 1) {
                Data.levelValueButtonClick++;
                Data.criterionRomanovsky = Utils.criterionRomanovsky(Data.countInputNumeric, Data.levelValue);
                String label3Text = Data.criterionRomanovsky.ToString();
                label3.Text = label3Text;
            }
        }

        private void label3_Click(object sender, EventArgs e)
        {
                        
        }

        private void radioButton3_CheckedChanged(object sender, EventArgs e)
        {
            Data.levelValue = 0.01;
            Data.levelValueRadioButtonClick = 1;
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            Data.levelValue = 0.02;
            Data.levelValueRadioButtonClick = 1;
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            Data.levelValue = 0.05;
            Data.levelValueRadioButtonClick = 1;
        }

        private void radioButton4_CheckedChanged(object sender, EventArgs e)
        {
            Data.levelValue = 0.1;
            Data.levelValueRadioButtonClick = 1;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            //кнопка решить задачу
            if (Data.levelValueButtonClick>0)
            {
                measurmentsArrayRomanovsky = Measurments.measurmentsArrayClassMeasurments;

                averageArrayRomanovsky = Utils.averageArray(measurmentsArrayRomanovsky);//среднее значение измерений(до корректировки)

                measurmentsArrayRomanovskyDeviations = Utils.ArrayDeviations(measurmentsArrayRomanovsky, averageArrayRomanovsky);

                measurmentsArrayRomanovskyDeviationsSquared = Utils.ArrayDeviationsSquared(measurmentsArrayRomanovskyDeviations);

                sumArrayRomanovskyDeviationsSquared = Utils.averageArrayDeviationsSquared(measurmentsArrayRomanovskyDeviationsSquared);//сумма квадратов отклонений

                standardDeviationRomanovsky = Math.Sqrt(sumArrayRomanovskyDeviationsSquared / (Data.countInputNumeric - 1));//среднеквадратичное отклонение

                measurmentsArrayRomanovskyBeta = Utils.ArrayRomanovskyBeta(measurmentsArrayRomanovskyDeviations, standardDeviationRomanovsky);

                measurmentsArrayRomanovskyResult = Utils.ArrayRomanovskyResult(measurmentsArrayRomanovsky, measurmentsArrayRomanovskyBeta, Data.criterionRomanovsky);

                RomanovskyResult = Utils.Result(measurmentsArrayRomanovskyResult);//среднее значение измерений(после корректировки)!!!!!!!!!!!!!!!!!





        String label6Text = averageArrayRomanovsky.ToString("0.00000");//поле среднее значение до корректировки ошибок
                label6.Text = label6Text;

                String label5Text = RomanovskyResult.ToString("0.00000");//поле среднее значение после корректировки ошибок
                label5.Text = label5Text;

                String label11Text = standardDeviationRomanovsky.ToString("0.00000");//поле среднее значение после корректировки ошибок
                label11.Text = label11Text;

                //--------------------------------------------------------------------------
                dataGridView1.RowCount = Data.countInputNumeric +2;//строки
                dataGridView1.ColumnCount = 4;//столбцы
                for (int i = 1; i < dataGridView1.RowCount-1; i++)
                {
                    dataGridView1.Rows[i].Cells[0].Value = i.ToString();                    
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i+1].Cells[1].Value = measurmentsArrayRomanovsky[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i + 1].Cells[2].Value = measurmentsArrayRomanovskyDeviations[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView1.RowCount - 2; i++)
                {
                    dataGridView1.Rows[i + 1].Cells[3].Value = measurmentsArrayRomanovskyDeviationsSquared[i].ToString("0.00000");
                }
                     dataGridView1.Rows[0].Cells[0].Value = "№";
                      dataGridView1.Rows[0].Cells[1].Value = "Результат измерения";
                      dataGridView1.Rows[0].Cells[2].Value = "Отклонения";
                       dataGridView1.Rows[0].Cells[3].Value = "Квадрат отклонений";

                dataGridView1.Rows[Data.countInputNumeric +1].Cells[1].Value = averageArrayRomanovsky.ToString("0.00000");
                dataGridView1.Rows[Data.countInputNumeric +1].Cells[3].Value = sumArrayRomanovskyDeviationsSquared.ToString("0.00000");

                //----------------------------------------------------------------------------
                dataGridView2.RowCount = Data.countInputNumeric + 2;//строки
                dataGridView2.ColumnCount = 4;//столбцы
                for (int i = 1; i < dataGridView2.RowCount - 1; i++)
                {
                    dataGridView2.Rows[i].Cells[0].Value = i.ToString();
                }
                for (int i = 0; i < dataGridView2.RowCount - 2; i++)
                {
                    dataGridView2.Rows[i + 1].Cells[1].Value = measurmentsArrayRomanovsky[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView2.RowCount - 2; i++)
                {
                    dataGridView2.Rows[i + 1].Cells[2].Value = measurmentsArrayRomanovskyBeta[i].ToString("0.00000");
                }
                for (int i = 0; i < dataGridView2.RowCount - 2; i++)
                {
                    dataGridView2.Rows[i + 1].Cells[3].Value = measurmentsArrayRomanovskyResult[i].ToString("0.00000");
                }
                dataGridView2.Rows[0].Cells[0].Value = "№";
                dataGridView2.Rows[0].Cells[1].Value = "Результат измерения";
                dataGridView2.Rows[0].Cells[2].Value = "Коэффициент бета";
                dataGridView2.Rows[0].Cells[3].Value = "Истинные результаты измерения";

                dataGridView2.Rows[Data.countInputNumeric + 1].Cells[1].Value = averageArrayRomanovsky.ToString("0.00000");
                dataGridView2.Rows[Data.countInputNumeric + 1].Cells[3].Value = RomanovskyResult.ToString("0.00000");


                //----------------------------------------------------------------------------
                if (RomanovskyResult > 0) {
                    label12.Text = " "; 
                    label13.Text = " "; 
                } else {
                    label12.Text = "'не число' соответствует полностью провальной серии";
                    label13.Text = "измерений.  Точность измерений должна быть увеличена.";
                }




            }


        }

        private void label6_Click(object sender, EventArgs e)
        {
            //поле среднее значение до корректировки ошибок
        }

        private void label5_Click(object sender, EventArgs e)
        {
            //поле среднее значение после корректировки ошибок
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
            ///////////111/////"не число" соответствует полностью провальной серии  
        }

        private void label13_Click(object sender, EventArgs e)
        {
            ///////222/////измерений.  Точность измерений должна быть увеличена.
        }
    }

}

