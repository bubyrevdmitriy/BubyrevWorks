using System.Web;
using System.Web.Mvc;

namespace ITMO.ASPNET.MVC.Course2020.TestWork
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
