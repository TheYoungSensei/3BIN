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
            ProductsOfCountry();
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
    }
}
