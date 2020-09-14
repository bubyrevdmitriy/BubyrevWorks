using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.TestWork.Models
{
    public class Course
    {
        // ID образовательного курса
        public virtual int CourseId { get; set; }
        
        // Название курса
        public virtual string Head { get; set; } 

        // Продолжительность курса, часы 
        public virtual int Period { get; set; } 

        // Максимально возможное кол-во набранных баллов
        public virtual int Sum { get; set; } 
       
    }
}