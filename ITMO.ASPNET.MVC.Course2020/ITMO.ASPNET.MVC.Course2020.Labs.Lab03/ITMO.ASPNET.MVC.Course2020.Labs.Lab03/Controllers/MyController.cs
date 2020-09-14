using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ITMO.ASPNET.MVC.Course2020.Labs.Lab03.Models;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab03.Controllers
{
    public class MyController : Controller
    {
        // GET: My
        /*public ActionResult Index()
        {
            return View();
        }*/
        private static PersonRepository db = new PersonRepository();

        public ViewResult Index() 
        {
            int hour = DateTime.Now.Hour; 
            ViewBag.Greeting = hour < 12 ? "Доброе утро" : "Добрый день"; 
            ViewData["Mes"] = "хорошего настроения"; 
            return View();
        }

        [HttpGet]
        public ViewResult InputData() 
        { 
            return View(); 
        }


        [HttpPost]
        public ViewResult InputData(Person p)
        {
            db.AddResponse(p);
            return View("Hello", p);
        }

        public ViewResult OutputData() 
        { 
            ViewBag.Pers = db.GetAllResponses; 
            ViewBag.Count = db.NumberOfPerson; 
            return View("ListPerson");
        }

    }
}