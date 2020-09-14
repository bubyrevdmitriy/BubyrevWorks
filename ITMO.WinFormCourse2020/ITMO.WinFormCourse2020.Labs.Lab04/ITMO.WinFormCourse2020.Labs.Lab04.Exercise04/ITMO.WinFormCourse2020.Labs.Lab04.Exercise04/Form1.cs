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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        
public string SolData
        {
            get
            { return textBoxF1.Text; }
            set
            { textBoxF1.Text = value; }
        }

        private void button1_Click(object sender, EventArgs e)
        {
                 
            {
            Form2 frm2 = new Form2();
            frm2.Show(this);
             }

        }


    }
}
