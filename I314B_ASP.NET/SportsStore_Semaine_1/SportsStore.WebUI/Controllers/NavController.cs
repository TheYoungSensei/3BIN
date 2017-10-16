using System.Collections.Generic;
using System.Web.Mvc;
using SportsStore.Domain.Abstract;
using System.Linq;

namespace SportsStore.WebUI.Controllers
{
    public class NavController : Controller
    {
        private IProductRepository repository;

        public NavController(IProductRepository repo)
        {
            repository = repo;
        }

       public PartialViewResult Menu()
        {
            IEnumerable<string> categories = repository.Products
                                                .Select(x => x.Category)
                                                .Distinct()
                                                .OrderBy(x => x);
            return PartialView(categories);
        }
    }
}