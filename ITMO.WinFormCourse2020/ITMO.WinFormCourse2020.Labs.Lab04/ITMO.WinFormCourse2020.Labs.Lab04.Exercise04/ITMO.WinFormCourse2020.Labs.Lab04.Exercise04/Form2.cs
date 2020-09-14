using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ITMO.WinFormCourse2020.Labs.Lab04.Exercise04
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
                {
                string s = textBoxF21.Text + " " + textBoxF22.Text + " " +
                textBoxF23.Text;
                Form1 frm1 = this.Owner as Form1;
                if (frm1 != null)
                {
                    frm1.SolData = s;
                }
                this.Close();
            }
        }
    }
}
