using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab05.Models
{
    public class Bid
    { 
        // ID заявки 
        public virtual int BidId { get; set; }
        // Имя заявителя 
        [DisplayName("Имя заявителя")] 
        public virtual string Name { get; set; }
        // Название кредита 
        [DisplayName("Название кредита")] 
        public virtual string CreditHead { get; set; }
        // Дата подачи заявки 
        [DisplayName("Дата подачи заявки")] 
        [DataType(DataType.DateTime)] 
        [DisplayFormat(DataFormatString = "{0:dd/MM/yy}")]
        public virtual DateTime bidDate { get; set; }
    }
}