using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab05.Models
{
    public class Bid
    {
        // ID заявки 
        public virtual int BidId { get; set; } 
        // Имя заявителя 
        public virtual string Name { get; set; } 
        // Название кредита 
        public virtual string CreditHead { get; set; } 
        // Дата подачи заявки 
        public virtual DateTime bidDate { get; set; }
    }
}