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
        private CourseContext db = new CourseContext();

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
            var allCourses = db.Courses.ToList<Course>();
            ViewBag.Courses = allCourses;
        }

        [HttpGet]
        public ActionResult CreateStudent()
        {
            GiveCourses(); 
            var allStudents = db.Students.ToList<Student>();
            ViewBag.Students = allStudents;
            return View();
        }

        [HttpPost]
        public ActionResult CreateStudent(Student newStudent)
        {
            //newStudent.StudentDate = DateTime.Now; 
            // Добавляем нового студента в БД 
            db.Students.Add(newStudent); 
            // Сохраняем в БД все изменения 
            db.SaveChanges(); 
            
            return View();
        }


        [HttpGet]
        public ActionResult GetTop5Students()
        {
            GiveCourses();            
            var allStudents = db.Students.SqlQuery("SELECT TOP 5 * FROM Students ORDER BY CourseCSResult+CourseJavsResult+CourseCPPResult+CoursePythonResult DESC").ToList<Student>();
            ViewBag.Students = allStudents;
            return View();            
        }


        [HttpGet]
        public ActionResult GetLow5Students()
        {
            GiveCourses();
            var allStudents = db.Students.SqlQuery("SELECT TOP 5 * FROM Students ORDER BY CourseCSResult+CourseJavsResult+CourseCPPResult+CoursePythonResult").ToList<Student>();
            ViewBag.Students = allStudents;
            return View();
        }



    }

}