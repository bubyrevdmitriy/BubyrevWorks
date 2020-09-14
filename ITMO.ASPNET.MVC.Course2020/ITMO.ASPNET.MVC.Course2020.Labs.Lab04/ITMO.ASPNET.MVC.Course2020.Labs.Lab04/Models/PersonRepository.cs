using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab03.Models
{
    public class PersonRepository
    {
        private List<Person> persons = new List<Person>();

        public int NumberOfPerson
        { 
            get 
            { 
                return persons.Count(); 
            } 
        }
        public IEnumerable<Person> GetAllResponses 
        { 
            get 
            { 
                return persons; 
            } 
        }

        public void AddResponse(Person pers) 
        { 
            persons.Add(pers); 
        }

    }
}