using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab01
{
    public class ModelClass
    {
        public static string ModelHello()
        {
            int hour = DateTime.Now.Hour;
            string Greeting = hour < 12 ? "Доброе утро" : "Добрый день"; return Greeting;
        }
    }







}
