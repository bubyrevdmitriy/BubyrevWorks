using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ITMO.WinFormCourse2020.Labs.Lab06.Exercise01
{
    public partial class edit : Form
    {
        public edit()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form1 frm1 = this.Owner as Form1;

           
            try
            {
                frm1.a = double.Parse(textBox1.Text);
                frm1.b = double.Parse(textBox2.Text);

                double a = frm1.a;
                double b = frm1.b;

                frm1.DataA.Text = textBox1.Text;
                frm1.DataB.Text = textBox2.Text;
            }
            catch (Exception er)
            {
                MessageBox.Show("При выполнении ввода исходных данных возникла ошибка: \n" + er.Message,
                "Ошибка", MessageBoxButtons.OK,
                MessageBoxIcon.Error);
                return;
            }

          
            this.Close();
            frm1.Refresh();
            


        }

        private void edit_Load(object sender, EventArgs e)
        {

        }
    }
}
