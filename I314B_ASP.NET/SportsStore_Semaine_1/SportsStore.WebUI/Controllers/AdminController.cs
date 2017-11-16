using System.Web.Mvc;
using SportsStore.Domain.Abstract;
namespace SportsStore.WebUI.Controllers {
    public class AdminController : Controller
    {
        private IProductRepository repository;
        public AdminController(IProductRepository repo)
        {
            repository = repo;
        }
        public ViewResult Index()
        {
            return View(repository.Products);
        }
    }
}