using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Configuration;

namespace ITMO.ADONETCourse2020.TestWork.Task2.DataSetWorking
{
    public partial class Form1 : Form
    {
        static SqlConnection SchoolConnection = new SqlConnection(@"Data Source=DESKTOP-VMCV9GK\SQLEXPRESS;Initial Catalog=School;Integrated Security=True");
        static string query = "SELECT * FROM Course";
        static SqlDataAdapter SqlDataAdapter1 = new SqlDataAdapter(query, SchoolConnection);
        DataSet SchoolDataset = new DataSet("School");
        SqlCommandBuilder commands = new SqlCommandBuilder(SqlDataAdapter1);

        public Form1()
        {
            InitializeComponent();
        }

        private void courseBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.courseBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.schoolDataSet);

        }

        private void courseBindingNavigatorSaveItem_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.courseBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.schoolDataSet);

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Department". При необходимости она может быть перемещена или удалена.
            this.departmentTableAdapter.Fill(this.schoolDataSet.Department);
            // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Course". При необходимости она может быть перемещена или удалена.
            this.courseTableAdapter.Fill(this.schoolDataSet.Course);
            //для редактирования курсов
            SqlDataAdapter1.Fill(SchoolDataset, "Course");
            dataGridView1.DataSource = SchoolDataset.Tables["Course"];
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void groupBox2_Enter(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            //обновить
            SchoolDataset.EndInit();
            SqlDataAdapter1.Update(SchoolDataset.Tables["Course"]);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            try
            { 
                //считываем Department
                int selectedIndex = comboBox1.SelectedIndex + 1;
                Object selectedItem = comboBox1.SelectedItem;
                //
                String SelectedItemText = selectedItem.ToString();
                int selectedIndexTable = 0;

                if (SelectedItemText.Equals("Engineering")) { selectedIndexTable = 1; }
                if (SelectedItemText.Equals("English")) { selectedIndexTable = 2; }
                if (SelectedItemText.Equals("Economics")) { selectedIndexTable = 4; }
                if (SelectedItemText.Equals("Mathematics")) { selectedIndexTable = 7; }

                int creditValue = int.Parse(textBox1.Text);
                string courseTitle= textBox2.Text;

                //-----------------------------------------
                
                int maxnum = 0;

                using (SqlConnection con = new SqlConnection(@"Data Source=DESKTOP-VMCV9GK\SQLEXPRESS;Initial Catalog=School;Integrated Security=True"))

                {

                    SqlCommand cmd = new SqlCommand("SELECT MAX(CourseID) AS MaxOf FROM Course", con);

                    con.Open();

                    maxnum = Convert.ToInt32(cmd.ExecuteScalar());
                    //MessageBox.Show(maxnum.ToString());

                    con.Close();

                }

                //----------------------------------------

                
                //добавить строку
                DataRow CustRow = SchoolDataset.Tables["Course"].NewRow();
                Object[] CustRecord = { (Convert.ToInt32(maxnum.ToString()) + 1), courseTitle, creditValue, selectedIndexTable };//{ "555", "TitleExsmple", "69", "69" };
                CustRow.ItemArray = CustRecord;
                SchoolDataset.Tables["Course"].Rows.Add(CustRow);
                SqlDataAdapter1.Update(SchoolDataset.Tables["Course"]);
                
                //обновляем верхние формы
                // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Department". При необходимости она может быть перемещена или удалена.
                this.departmentTableAdapter.Fill(this.schoolDataSet.Department);
                // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Course". При необходимости она может быть перемещена или удалена.
                this.courseTableAdapter.Fill(this.schoolDataSet.Course);
            } 
            catch //(Exception ex)
            {
                MessageBox.Show("перепроверьте данные");
            }
            

        }

        private void button4_Click(object sender, EventArgs e)
        {
            //удалить строку
            SchoolDataset.EndInit(); var index = dataGridView1.CurrentRow.Index; SchoolDataset.Tables["Course"].Rows[index].Delete();
            SqlDataAdapter1.Update(SchoolDataset.Tables["Course"]);
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        private void button5_Click(object sender, EventArgs e)
        {
            //обновить
            // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Department". При необходимости она может быть перемещена или удалена.
            this.departmentTableAdapter.Fill(this.schoolDataSet.Department);
            // TODO: данная строка кода позволяет загрузить данные в таблицу "schoolDataSet.Course". При необходимости она может быть перемещена или удалена.
            this.courseTableAdapter.Fill(this.schoolDataSet.Course);
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            string CurrentCellInfo;

            CurrentCellInfo = dataGridView1.CurrentCell.Value.ToString() + Environment.NewLine;

            
             CurrentCellInfo += "Column: " +
            dataGridView1.CurrentCell.OwningColumn.DataPropertyName + Environment.NewLine;
            CurrentCellInfo += "Column Index: " +
            dataGridView1.CurrentCell.ColumnIndex.ToString() + Environment.NewLine;
            CurrentCellInfo += "Row Index: " +
            dataGridView1.CurrentCell.RowIndex.ToString() + Environment.NewLine;
            

            label4.Text = CurrentCellInfo;

        }
    }
}
