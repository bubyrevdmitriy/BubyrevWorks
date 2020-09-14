using CodeFirst;
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
using System.Data.Linq;
using System.Data.Entity;
using System.Data.Linq.Mapping;

namespace ITMO.ADONETCourse2020.TestWork.Task3.EntityFrameworkWork
{
    public partial class DepartmentAndCourseViewer : Form
    {
        SampleContext context = new SampleContext();
        string connectionString = @"Data Source=DESKTOP-VMCV9GK\SQLEXPRESS;Initial Catalog=MySchool;Integrated Security=True";

        public DepartmentAndCourseViewer()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void DepartmentAndCourseViewer_Load(object sender, EventArgs e)
        {
            
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            //имя курса
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            //кредиты
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            //комбо бокс департамента
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //кнопка добавить
            try 
            {               
                    //
                    //считываем Department
                int selectedIndex = comboBox2.SelectedIndex + 1;
                Object selectedItem = comboBox2.SelectedItem;
                //
                String SelectedItemText = selectedItem.ToString();
                int selectedIndexTable = 0;

                if (SelectedItemText.Equals("Engineering")) { selectedIndexTable = 1; }
                if (SelectedItemText.Equals("English")) { selectedIndexTable = 2; }
                if (SelectedItemText.Equals("Economics")) { selectedIndexTable = 4; }
                if (SelectedItemText.Equals("Mathematics")) { selectedIndexTable = 7; }

                int creditValue = int.Parse(textBox2.Text);
                string courseTitle = textBox1.Text;

                //-----------------------------------------

                int maxnum = 0;

                using (SqlConnection con = new SqlConnection(connectionString))
                //<add name="MySchool" connectionString="data source=(LocalDB)\MSSQLLocalDB; Initial Catalog=MyShop;Integrated Security=True;"

                {

                    SqlCommand cmd = new SqlCommand("SELECT MAX(CourseID) AS MaxOf FROM Courses", con);

                    con.Open();

                    maxnum = Convert.ToInt32(cmd.ExecuteScalar());
                    //MessageBox.Show(maxnum.ToString());

                    con.Close();

                }

                //----------------------------------------
                //
                Course course = new Course
                {
                    CourseId = maxnum,
                    CourseName = courseTitle,
                    Credits = creditValue,
                DepartmentId = selectedIndexTable

            };
                context.Courses.Add(course);
                context.SaveChanges();
                textBox1.Text = String.Empty;
                textBox2.Text = String.Empty;

                dataGridView2.DataSource = context.Courses.ToList();
            } 
            catch (Exception ex) 
            { 
                MessageBox.Show("Ошибка: " + ex.ToString()); 
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            dataGridView1.DataSource = context.Departments.ToList();
            dataGridView2.DataSource = context.Courses.ToList();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            //<add name="MySchool" connectionString="data source=(LocalDB)\MSSQLLocalDB; Initial Catalog=MyShop;Integrated Security=True;"
            //DESKTOP-VMCV9GK\SQLEXPRESS
            {

                SqlCommand cmd = new SqlCommand("SELECT MAX(DepartmentId) AS MaxOf FROM Departments", con);

                con.Open();

                var result = (cmd.ExecuteScalar());
                                //MessageBox.Show(maxnum.ToString());

                con.Close();

                //MessageBox.Show("Ошибка:" + result+ "Ошибка:");
                
                //!result.Any()
                if (Convert.ToString(result) == "")
                {
                    context.Departments.Add(new Department { DepartmentId = 1, DepartmentName = "Engineering", Budget = 350000, StartTime = DateTime.Parse("01.09.2016") });//1
                    context.Departments.Add(new Department { DepartmentId = 2, DepartmentName = "English", Budget = 120000, StartTime = DateTime.Parse("01.09.2016") });//2
                    context.Departments.Add(new Department { DepartmentId = 3, DepartmentName = "Economics", Budget = 200000, StartTime = DateTime.Parse("01.09.2016") });//3
                    context.Departments.Add(new Department { DepartmentId = 4, DepartmentName = "Mathematics", Budget = 250000, StartTime = DateTime.Parse("01.09.2016") });//4
                    context.SaveChanges();
                }
                //comboBox1.DataSource = context.Departments.ToList();

                //Courses
                if (Convert.ToString(result) == "")
                {
                    context.Courses.Add(new Course { CourseId = 1045, CourseName = "Calculus", Credits = 4, DepartmentId = 4 });//1
                    context.Courses.Add(new Course { CourseId = 1050, CourseName = "Chemistry", Credits = 5, DepartmentId = 1 });//2
                    context.Courses.Add(new Course { CourseId = 1061, CourseName = "Physics", Credits = 4, DepartmentId = 1 });//3
                    context.Courses.Add(new Course { CourseId = 2021, CourseName = "Composition", Credits = 3, DepartmentId = 2 });//4
                    context.Courses.Add(new Course { CourseId = 2030, CourseName = "Poetry", Credits = 2, DepartmentId = 2 });//5
                    context.Courses.Add(new Course { CourseId = 2042, CourseName = "Literature", Credits = 4, DepartmentId = 2 });//6
                    context.Courses.Add(new Course { CourseId = 3141, CourseName = "Trigonometry", Credits = 4, DepartmentId = 4 });//7
                    context.Courses.Add(new Course { CourseId = 4022, CourseName = "Microeconomics", Credits = 1, DepartmentId = 3 });//8
                    context.Courses.Add(new Course { CourseId = 4041, CourseName = "Macroeconomics", Credits = 3, DepartmentId = 3 });//9
                    context.Courses.Add(new Course { CourseId = 4061, CourseName = "Quantitative", Credits = 2, DepartmentId = 3 });//10
                }

                context.SaveChanges();
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                //редактировать
                if (textBox3.Text == String.Empty) return;
            var id = Convert.ToInt32(textBox3.Text);
            var course = context.Courses.Find(id);
            if (course == null) return;

            course.CourseName = textBox1.Text;
            course.Credits = Convert.ToInt32(textBox2.Text);

            //---------------------------------------------------------------
            int selectedIndex = comboBox2.SelectedIndex + 1;
            Object selectedItem = comboBox2.SelectedItem;
            //
            String SelectedItemText = selectedItem.ToString();
            int selectedIndexTable = 0;

            if (SelectedItemText.Equals("Engineering")) { selectedIndexTable = 1; }
            if (SelectedItemText.Equals("English")) { selectedIndexTable = 2; }
            if (SelectedItemText.Equals("Economics")) { selectedIndexTable = 4; }
            if (SelectedItemText.Equals("Mathematics")) { selectedIndexTable = 7; }

            int creditValue = int.Parse(textBox2.Text);
            string courseTitle = textBox1.Text;

            course.DepartmentId = selectedIndexTable;
            
            //---------------------------------------------------------------
            /*customer.FirstName = this.textBoxname.Text;
            customer.LastName = this.textBoxlastname.Text;
            customer.Email = this.textBoxmail.Text;
            customer.Age = Int32.Parse(this.textBoxage.Text);*/

            context.Entry(course).State = EntityState.Modified;
            context.SaveChanges();
            dataGridView2.DataSource = context.Courses.ToList();
                //Output();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Ошибка: " + ex.ToString());
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            //удалить
            /*if (labelid.Text == String.Empty) return;
            var id = Convert.ToInt32(labelid.Text); var customer = context.Customers.Find(id);
            context.Entry(customer).State = EntityState.Deleted; context.SaveChanges();*/

            if (textBox3.Text == String.Empty) return;
            var id = Convert.ToInt32(textBox3.Text);
            var course = context.Courses.Find(id);
            context.Entry(course).State = EntityState.Deleted;
            context.SaveChanges();
            dataGridView2.DataSource = context.Courses.ToList();
            //Output();

        }

        private void dataGridView2_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dataGridView2.CurrentRow == null) return;
            var course = dataGridView2.CurrentRow.DataBoundItem as Course;
            if (course == null) return;
            textBox3.Text = Convert.ToString(course.CourseId);

            textBox4.Text = course.ToString();

            textBox1.Text = course.CourseName;
            textBox2.Text = Convert.ToString(course.Credits);
            comboBox2.SelectedIndex = ((course.DepartmentId)-1);
            //--------------------------------------------------------------------
            /*DataContext db = new DataContext(connectionString);

            var query = from dep in db.GetTable<Department>()
                        where dep.DepartmentId == course.DepartmentId
                        select dep;*/

            //comboBox1.DataSource = context.Departments.ToList();
        }

        private void textBox5_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }
    }
}

