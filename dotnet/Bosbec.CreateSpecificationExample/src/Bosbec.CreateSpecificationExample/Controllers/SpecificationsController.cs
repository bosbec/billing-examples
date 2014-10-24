namespace Bosbec.CreateSpecificationExample.Controllers
{
    using System;
    using System.Web.Mvc;

    using Bosbec.CreateSpecificationExample.Helpers;

    public class SpecificationsController : Controller
    {
        public ActionResult Index()
        {
            var specifications = SpecificationsHelper.List();

            ViewBag.Specifications = specifications;

            return View();
        }

        public ActionResult Fetch(Guid specificationId)
        {
            // TODO: Figure out what to do here!
            return new EmptyResult();
        }
    }
}