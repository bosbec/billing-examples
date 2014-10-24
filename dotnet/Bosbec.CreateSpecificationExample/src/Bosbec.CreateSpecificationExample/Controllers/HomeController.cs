namespace Bosbec.CreateSpecificationExample.Controllers
{
    using System;
    using System.Web.Mvc;

    using Bosbec.CreateSpecificationExample.Helpers;

    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Index(DateTime periodStart, DateTime periodEnd)
        {
            var notifyUrl = Url.RouteUrl("FetchSpecification", null, Request.Url.Scheme) + "?specificationId={specificationId}";

            SpecificationsHelper.Create(periodStart, periodEnd, notifyUrl);

            return RedirectToRoute("ListSpecifications");
        }
    }
}