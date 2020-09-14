using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ITMO.ASPNET.Course2020.Labs.Lab01
{
    public partial class Start : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            /*if (IsPostBack)
            {
                GuestResponse rsvp = new GuestResponse(name.Text, email.Text, phone.Text, CheckBoxYN.Checked);
                ResponseRepository.GetRepository().AddResponse(rsvp);
                if (rsvp.WillAttend.HasValue && rsvp.WillAttend.Value)
                { 
                Response.Redirect("seeyouthere.html"); 
                } 
                else 
                { 
                Response.Redirect("sorryyoucantcome.html"); 
                }
            }*/

        }
    }
}