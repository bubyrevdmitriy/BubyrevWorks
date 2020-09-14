using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.TestWork.Models
{
    public class CoursesDbInitializer : DropCreateDatabaseIfModelChanges<CourseContext>
    {

        protected override void Seed(CourseContext context)
        {
            context.Courses.Add(new Course { Head = "C#", Period = 65, Sum = 5 });
            context.Courses.Add(new Course { Head = "Java", Period = 65, Sum = 5 });
            context.Courses.Add(new Course { Head = "C++", Period = 65, Sum = 5 });
            context.Courses.Add(new Course { Head = "Python", Period = 65, Sum = 5 });

            base.Seed(context);
        }


    }
}