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
    public partial class Form1 : Form
    {
        

        private double[] measurmentsArray = new double[Data.countInputNumeric];

        public Form1()
        {
            InitializeComponent();           
             
    }

        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
                MessageBox.Show("Поле должно содержать цифры");
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

            if (Data.countInputTextBox < Data.countInputNumeric)
            {
                double temp = Convert.ToDouble(textBox1.Text);
                measurmentsArray[Data.countInputTextBox] = temp;
                Data.countInputTextBox++;
                if (Data.countInputTextBox < Data.countInputNumeric)
                { 
                    textBox1.Clear();
                    String label1Text = "Введите значение №" + (Data.countInputTextBox + 1);
                    label1.Text = label1Text;
                }
                
            }
            if (Data.countInputTextBox == Data.countInputNumeric)
            {
                label1.Text = "результаты измерений введены!";
                textBox1.Clear();
                if (Data.countInputTextBox <= 20)
                {
                    if (Data.form2Show == 0)
                    {
                        Measurments Measurments = new Measurments(measurmentsArray);
                        Form2 myForm2 = new Form2();//Measurments.measurmentsArrayClassMeasurments
                        Data.form2Show++;

                        myForm2.TopLevel = false;
                        myForm2.AutoScroll = true;
                        panel1.Controls.Add(myForm2);

                        myForm2.Show();
                    }

                }
                else
                {
                    if (Data.form3Show == 0)
                    {
                        Measurments Measurments = new Measurments(measurmentsArray);
                        Form3 myForm3 = new Form3();
                        Data.form3Show++;

                        myForm3.TopLevel = false;
                        myForm3.AutoScroll = true;
                        panel1.Controls.Add(myForm3);

                        myForm3.Show();
                    }

                }
            }
            
        }

        
        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
            
        }

        private void button2_Click(object sender, EventArgs e)
        {


        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            Data.form1Show = 0;
            this.Close();
        }

        private void richTextBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
