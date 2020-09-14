using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace ITMO.WinFormCourse2020.Labs.Lab06.Exercise03
{
    public partial class Form1 : Form
    {
        static int k = 10;
        dat d1 = new dat(k);

        //chartPlot(d,leg);

        private void chartPlot(int d, string leg)
        {
            chart1.Series["Series1"].Points.Clear();
            for (int i = 1; i <= k; i++)
            {
                chart1.Series["Series1"].Points.AddXY(i, d1.random(d));
            }
            //chart1.Series["Series1"].ChartType = SeriesChartType.Spline;

            if (checkBox1.Checked == true)
            { chart1.Series["Series1"].ChartType = SeriesChartType.Column; }
            else
            { chart1.Series["Series1"].ChartType = SeriesChartType.Spline; }

            chart1.Series["Series1"].IsValueShownAsLabel = true;
            chart1.Series["Series1"].LegendText = leg;
        }



       public Form1()
        {
            InitializeComponent();

            chartPlot(10, "Ветер м/с");

            /*for (int i = 1; i <= k; i++)
            {
                chart1.Series["Series1"].Points.AddXY(i, d1.random(10));
            }
                
            chart1.Series["Series1"].ChartType = SeriesChartType.Spline;
           
            chart1.Series["Series1"].IsValueShownAsLabel = true;
            chart1.Series["Series1"].LegendText = "Ветер м/с";*/

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged_1(object sender, EventArgs e)
        {
            if (radioButton1.Checked == true)
            {
                chartPlot(100, "Влажность");
                //d1 = 100;
                //String leg = "Влажность";
            }
        }

        private void radioButton2_CheckedChanged_1(object sender, EventArgs e)
        {
            if (radioButton2.Checked == true)
            {
                chartPlot(30, "Температура");
                //d1 = 30;
                //String leg = "Температура";
            }
        }

        private void chart1_Click(object sender, EventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}
