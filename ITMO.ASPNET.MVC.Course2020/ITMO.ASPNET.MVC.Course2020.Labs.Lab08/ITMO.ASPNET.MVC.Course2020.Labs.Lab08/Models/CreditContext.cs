using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab05.Models
{
    
    public class CreditContext : DbContext 
    { 
        public DbSet<Credit> Credits { get; set; } 
        public DbSet<Bid> Bids { get; set; } 
    }
    
}