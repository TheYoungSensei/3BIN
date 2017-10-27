using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinqEntities
{
    class Program
    {
        
        private static NorthwindEntities context;
        static void Main(string[] args)
        {
            context = new NorthwindEntities();
            //PrintCustomerOfCity();
            //ProductsOfCountryLazy();
            //ProductsOfCountryEager();
            //CommandsForClient();
            //setNameInMajuscule();
            //deleteCategory();
            reassignment();
            Console.ReadLine();
        }

        private static void PrintCustomerOfCity()
        {
            Console.WriteLine("Veuilliez entrer le nom de la ville : ");
            String city = Console.ReadLine();
            IQueryable<Customer> custs = context.Customers.Where(c => c.City.Equals(city)).Select(c => c);
            Console.WriteLine("Voici les customers y habitant : ");
            foreach (Customer c in custs)
            {
                Console.WriteLine("Customer : {0}", c.ContactName);
            }
        }

        private static void ProductsOfCountryLazy()
        {
            Console.WriteLine("Veuilliez entrer le nom du pays : ");
            String country = Console.ReadLine();
            IQueryable<Supplier> suppliers = from s in context.Suppliers
                                            where s.Country == country
                                            orderby s.ContactName
                                            select s;
            foreach (Supplier s in suppliers)
            {
                Console.WriteLine("Supplier : {0}", s.ContactName);
                foreach(Product p in s.Products)
                {
                    Console.WriteLine("    Product : {0}", p.ProductName);
                }
            }
        }

        private static void ProductsOfCountryEager()
        {
            Console.WriteLine("Veuilliez entrer le nom du pays : ");
            String country = Console.ReadLine();
            IQueryable<Supplier> suppliers = from s in context.Suppliers
                                                .Include("Products")
                                             where s.Country == country
                                             orderby s.ContactName
                                             select s;
            foreach (Supplier s in suppliers)
            {
                Console.WriteLine("Supplier : {0}", s.ContactName);
                foreach (Product p in s.Products)
                {
                    Console.WriteLine("    Product : {0}", p.ProductName);
                }
            }
        }

        private static void CommandsForClient()
        {
            Console.WriteLine("Veuillez entrer le nom du client dont vous souhaitez récupérer les commandes : ");
            String nomClient = Console.ReadLine();
            var commandes = context.Customers
                .Where(c => c.ContactName.Equals(nomClient))
                .Join(context.Invoices,
                c => c.CustomerID,
                inv => inv.CustomerID,
                (c, inv) =>
                new
                {
                    CustomerID = c.CustomerID,
                    OrderDate = inv.OrderDate,
                    ShippedDate = inv.ShippedDate
                }).Where(s => s.ShippedDate != null).OrderByDescending(s => s.ShippedDate);
            int i = 0;
            foreach (var elem in commandes)
            {
                Console.WriteLine("Commande N°" + i);
                i++;
                Console.WriteLine("CustomerID : "  + elem.CustomerID);
                Console.WriteLine("OrderDate : " + elem.OrderDate);
                Console.WriteLine("ShippedDate : " + elem.ShippedDate);
            }
        }

        private static void TotalDesVentesParProduits()
        {
            IQueryable<Product> products = from p in context.Products
                                           select p;
            foreach(Product p in products)
            {
                Console.WriteLine("{0} : {1}", p.ProductID, "YOLO");
            }
        }

        private static void setNameInMajuscule()
        {
            IEnumerable<Customer> clients = from c in context.Customers
                                            select c;
            foreach(Customer c in clients)
            {
                c.ContactName = c.ContactName.ToUpper();
            }

            context.SaveChanges();

            IEnumerable<Customer> customers = from c in context.Customers
                                            select c;
            foreach (Customer c in customers)
            {
                Console.WriteLine(c.ContactName = c.ContactName.ToUpper());
            }
        }

        private static void deleteCategory()
        {
            IEnumerable<Category> yolo = from c in context.Categories
                                               select c;
            foreach (Category cat in yolo)
            {
                Console.WriteLine("{0}", cat.CategoryName);
            }

            Console.WriteLine("Veuilliez entrer le nom de la catégorie à supprimer : ");
            String categoryName = Console.ReadLine();
            Category category = (from c in context.Categories
                                where c.CategoryName == categoryName
                                select c).Single<Category>();
            context.Categories.Remove(category);

            IEnumerable<Category> categories = from c in context.Categories
                                               select c;
            context.SaveChanges();

            foreach (Category cat in categories)
            {
                Console.WriteLine("{0}", cat.CategoryName);   
            }
        }

        private static void reassignment()
        {
            foreach(Employee empl in from e in context.Employees select e)
            {
                Console.WriteLine(empl.LastName);
            }

            Console.WriteLine("Veuilliez entrer le nom du premier employé : ");
            String nomEmploye1 = Console.ReadLine();
            Console.WriteLine("Veuilliez entrer le nom du deuxième employé : ");
            String nomEmploye2 = Console.ReadLine();

            Employee employe1 = (from e in context.Employees
                               where e.LastName == nomEmploye1
                               select e).Single<Employee>();
            Employee employe2 = (from e in context.Employees
                                 where e.LastName == nomEmploye2
                                 select e).Single<Employee>();
            foreach(Order order in employe1.Orders)
            {
                employe2.Orders.Add(order);
                employe1.Orders.Remove(order);
            }
            context.SaveChanges();
            context.Employees.Remove(employe1);
            context.SaveChanges();
            foreach (Employee empl in from e in context.Employees select e)
            {
                Console.WriteLine(empl.LastName);
            }
        }
    }
}
