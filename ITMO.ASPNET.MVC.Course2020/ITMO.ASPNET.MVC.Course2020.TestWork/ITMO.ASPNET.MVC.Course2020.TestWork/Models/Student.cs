using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.TestWork.Models
{
    public class Student
    {
        // ID студента 
        public virtual int StudentId { get; set; }

        // Имя студента 
        [DisplayName("Имя студента")]
        public virtual string Name { get; set; }

        // Оценка по курсу C# 
        [DisplayName("Оценка по курсу C#")]
        public virtual int CourseCSResult { get; set; }

        // Оценка по курсу Java 
        [DisplayName("Оценка по курсу Java")]
        public virtual int CourseJavsResult { get; set; }

        // Оценка по курсу C++ 
        [DisplayName("Оценка по курсу C++")]
        public virtual int CourseCPPResult { get; set; }

        // Оценка по курсу Python 
        [DisplayName("Оценка по курсу Python")]
        public virtual int CoursePythonResult { get; set; }


        // Дата сомтавления отчета 
        [DisplayName("Дата начала обучения")]
        [DataType(DataType.DateTime)]
        [DisplayFormat(DataFormatString = "{0:dd/MM/yy}")]
        public virtual DateTime StudentStartDate { get; set; }
    }
}