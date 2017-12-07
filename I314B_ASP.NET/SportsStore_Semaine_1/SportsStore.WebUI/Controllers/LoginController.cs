using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SportsStore.WebUI.Controllers
{
    public class LoginController : Controller
    {
        public PartialViewResult Button()
        {
            return PartialView("LoginButton");
        }

        public ViewResult Login()
        {
            return View("Login");
        }

        public ViewResult Logged()
        {
            return View();
        }
    }
}