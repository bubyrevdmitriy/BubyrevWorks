using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ITMO.WinFormCourse2020.Labs.Lab01.Exercise05_02
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void exitToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            this.Close();
        }

        private void form1ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (DataBank.countForm1==0)//public int countForm1 = 0;
            {
                Form1 newform = new Form1();
                DataBank.countForm1 = 1;
                newform.Show();
            }
        }

        private void form1CloseToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void Form2_Load(object sender, EventArgs e)
        {
             
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            //this.WindowState = FormWindowState.Maximized;

        }
    }
}
