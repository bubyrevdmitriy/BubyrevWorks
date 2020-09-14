using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodeFirst
{
    public class Model
    {
    }

    public class Course
    {
        public object selectedItem;

        public int CourseId { get; set; }
        public string CourseName { get; set; }
        public int Credits { get; set; }
        public int DepartmentId { get; set; }
        public override string ToString()
        {
            string s = CourseName + ", Credits: " + Credits + ", DepartmentId: " + DepartmentId;
            return s;
        }

// Ссылка на заказы
public virtual List<Department> Departments { get; set; }
        public Course()
        {
            Departments = new List<Department>();
        }
    }
    public class Department
    {
        public int DepartmentId { get; set; }
        public string DepartmentName { get; set; }
        public int Budget { get; set; }
        
        public DateTime StartTime { get; set; }
        // Ссылка на покупателя
        public Course Course { get; set; }
        public override string ToString()
        {
            string s = DepartmentName + " " + Budget + "y. e., дата: " + StartTime;
            return s;
        }
    }














}
