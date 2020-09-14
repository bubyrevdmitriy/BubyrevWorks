using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab01
{
    public class MyController : Controller
    {
        // GET: Home
        /*public ActionResult Index()
        {
            return View();
        }*/
        public string Index(string hel)
        {
            int hour = DateTime.Now.Hour;
            string name = $"{hel}";
            string Greeting = ModelClass.ModelHello() + ", ";
            return Greeting+name;

        }

    }
}