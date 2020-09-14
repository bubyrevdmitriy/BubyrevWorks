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
    public partial class Form1 : Form
    {
        

        public Form1()
        {
            InitializeComponent();
        }

        private void ButtonExitForm2_Click(object sender, EventArgs e)
        {
            DataBank.countForm1 = 0;
            this.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            int halfWidth = this.Width / 2;
            int halfHeight = this.Height / 2;

            System.Drawing.Drawing2D.GraphicsPath myPath =
                new System.Drawing.Drawing2D.GraphicsPath();
            myPath.AddPolygon(new Point[] { new Point(x: halfWidth, y:halfWidth),
                                            new Point (x: this.Width, y: halfHeight),
                                            new Point (x: halfWidth, y: 0),
                                            new Point(x: 0, y: halfHeight)}) ;

            
            Region myRegion = new Region(myPath);
            this.Region = myRegion;
        }
    }
}
