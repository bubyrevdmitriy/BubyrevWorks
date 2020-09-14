using System.Web;
using System.Web.Mvc;

namespace ITMO.ASPNET.MVC.Course2020.Labs.Lab05
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
