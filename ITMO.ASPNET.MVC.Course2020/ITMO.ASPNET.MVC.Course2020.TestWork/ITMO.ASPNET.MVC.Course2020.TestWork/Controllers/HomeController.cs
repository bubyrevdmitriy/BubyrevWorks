using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Web;
using System.Web.Mvc;
using ITMO.ASPNET.MVC.Course2020.TestWork.Models;
using Sitecore.FakeDb;

namespace ITMO.ASPNET.MVC.Course2020.TestWork.Controllers
{
    public class HomeController : Controller
    {
        
        public ActionResult Index()
        {
            GiveCourses();
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }


        public ActionResult Information()
        {
            ViewBag.Message = "Your application information page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        private void GiveCourses()
        {
            using (CourseContext db1 = new CourseContext()) {
                var allCourses = db1.Courses.ToList<Course>();
                ViewBag.Courses = allCourses;
            }
        }

        [HttpGet]
        public ActionResult CreateStudent()
        {
            return View();
        }

        [HttpPost]
        public ActionResult CreateStudent(Student newStudent)
        {
            using (CourseContext db1 = new CourseContext())
            {                
                // Добавляем нового студента в БД 
                db1.Students.Add(newStudent);
                // Сохраняем в БД все изменения 
                db1.SaveChanges();
                return View();
            }
        }


        [HttpGet]
        public ActionResult GetTop5Students()
        {
            using (CourseContext db1 = new CourseContext())
            {
                     
            var allStudents = db1.Students.SqlQuery("SELECT TOP 5 * FROM Students ORDER BY CourseCSResult+CourseJavsResult+CourseCPPResult+CoursePythonResult DESC").ToList<Student>();
            ViewBag.Students = allStudents;
            return View();
            }
        }


        [HttpGet]
        public ActionResult GetLow5Students()
        {
            using (CourseContext db1 = new CourseContext())
            {
                
                var allStudents = db1.Students.SqlQuery("SELECT TOP 5 * FROM Students ORDER BY CourseCSResult+CourseJavsResult+CourseCPPResult+CoursePythonResult").ToList<Student>();
                ViewBag.Students = allStudents;
                return View();
            }
        }



    }

}