using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab03.Models
{
    public class ModelClass
    {
    }

    public class Person
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public override string ToString() 
        { 
            string s = FirstName + " " + LastName; 
            return s; 
        }
    }

}