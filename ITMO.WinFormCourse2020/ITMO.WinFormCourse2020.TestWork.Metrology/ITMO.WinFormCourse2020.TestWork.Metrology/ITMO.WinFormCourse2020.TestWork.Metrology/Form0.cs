using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ITMO.WinFormCourse2020.TestWork.Metrology
{
    public partial class Form0 : Form
    {
        public Form0()
        {
            InitializeComponent();
            //public static Panel panel1;
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
            Data.countInputNumeric = Convert.ToInt32(Math.Round(numericUpDown1.Value, 0));
            
        }
             

        private void button1_Click_1(object sender, EventArgs e)
        {
            

            if (Data.form1Show==0 & Data.countInputNumeric>=4) {
                Form1 myForm = new Form1();
                Data.form1Show++;

                myForm.TopLevel = false;
                myForm.AutoScroll = true;
                panel1.Controls.Add(myForm);


                myForm.Show();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void Form0_Load(object sender, EventArgs e)
        {

        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
