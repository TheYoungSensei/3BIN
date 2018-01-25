 using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
             NorthwindEntities context = new NorthwindEntities();

            // Question B1
            Console.WriteLine("B1 - Recherche clients par ville");
            Console.WriteLine("Entrez le nom d'une ville :");
            string city = Console.ReadLine();

            var customers = from Customer c in context.Customers
                            where (c.City == city) 
                            select new { c.CustomerID, c.ContactName };

            foreach (var custo in customers)
            {
                Console.WriteLine("{0} : {1}",custo.CustomerID, custo.ContactName);
            }
             
            


            // Question B2 LAZY LOADING
            Console.WriteLine("B2 LAZY LOADING - Produits disponibles par pays");
            Console.WriteLine("Entrez le nom d'un pays :");
            string country = Console.ReadLine();

            IQueryable<Supplier> suppliers = from s in context.Suppliers
                                              where s.Country == country
                                              orderby country
                                              select s;

            foreach (Supplier s in suppliers.Distinct())
            {
                //Console.WriteLine("++{0}++", s.Country);
                foreach (Product p in s.Products)
                    Console.WriteLine("{0}",p.ProductName);
            }

            

            // Question B2 EAGER LOADING 
            Console.WriteLine("B2 EAGER LOADING - Produits disponibles par pays");
            Console.WriteLine("Entrez le nom d'un pays");
            string countrylu = Console.ReadLine();

            suppliers = from s in context.Suppliers
                                              .Include("Products")
                                              where s.Country == countrylu
                                              orderby s.Country
                                              select s;

            foreach (Supplier s in suppliers)
            {
                //Console.WriteLine("++{0}++", s.Country);
                foreach (Product p in s.Products)
                    Console.WriteLine("{0}",p.ProductName);
            }

            // Question B4
            Console.WriteLine("Entrez l'ID d'un client");
            string _customerID = Console.ReadLine(); // ALFKI par ex.

            var queryOrders = from Order o in context.Orders
                        where (o.CustomerID == _customerID && o.ShippedDate != null)
                        orderby o.OrderDate descending
                        select new {CustomerID = o.CustomerID, OrderDate = o.OrderDate, ShippedDate = o.ShippedDate};


            foreach (var od in queryOrders)
            {
                Console.WriteLine("CustomerID : "+od.CustomerID+" OrderDate : "+od.OrderDate + " ShippedDate :"+od.ShippedDate);
            }

            // QUESTION B5
            var query = from Order_Detail o in context.Order_Details
                         group o by o.ProductID;


            foreach (IGrouping<int, Order_Detail> orderDetails in query)
            {
                 Console.WriteLine(orderDetails.Key+"", orderDetails.Sum(o => o.UnitPrice*o.Quantity));
                
            }
            

            // Question C
            Console.WriteLine("Tous les clients en majuscule");
                 

            IQueryable<Customer> custom = (from c in context.Customers
                                          select c);


            foreach (Customer cust in custom) {
                        cust.ContactName = cust.ContactName.ToUpper();
            }

            try
            {
                context.SaveChanges();
            }
            catch (Exception e)
            {
                Console.WriteLine("Erreur {0}", e.Message);
            }
              
            Console.WriteLine("Done");
            
            

            // Question D1 
            // suppression d'une catégorie
            Console.WriteLine("Entrez une catégorie à effacer");
            string categorieLuClavier = Console.ReadLine();

            try
            {
                Category catSupp = (from c in context.Categories
                                    where c.CategoryName == categorieLuClavier
                                      select c).First< Category>();
                
                
                    
                // OLD version 
                // context.DeleteObject(catSupp);
                
                context.Categories.Remove(catSupp);

              
             
                context.SaveChanges();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                Console.WriteLine("Pas de contact trouvé!");
            }
               

            /* Question D3 */
            /* Exemple 
             * employeeID = 1 -> DAVOLIO 123 orders
             * employeeID = 2 -> FULLER 96 orders
             * transfert DAVOLIO vers Fuller -> donc 219 orders au total
             * Attention les int.parse(...) doivent être en dehors de querys LINQ
             */
            Console.WriteLine("Entrez l'ID de l'employé à supprimer");
            string emp1 = Console.ReadLine();

            Console.WriteLine("Entrez l'ID de l'employé qui reprend les Orders de celui à supprimer");
            string emp2 = Console.ReadLine();

            int e1 = int.Parse(emp1);
            int e2 = int.Parse(emp2);

            Employee employee1 = (from e in context.Employees
                                   where e.EmployeeID == e1
                                   select e).Single<Employee>();


            Employee employee2 = (from e in context.Employees
                                   where e.EmployeeID == e2
                                   select e).Single<Employee>();

            IQueryable<Order> employee1Orders = (from o in context.Orders
                                            where o.EmployeeID == e1
                                            select o);

            foreach (Order o in employee1Orders)
            {
                employee2.Orders.Add(o);
                employee1.Orders.Remove(o);

            }

            employee1.Orders.Clear();
            employee1.Territories.Clear();
            employee1.Employees1.Clear();
            employee1 = null;

            //old version
            //context.DeleteObject(employee1);
            
            context.Employees.Remove(employee1);
            context.SaveChanges();



            Console.ReadLine();
        
        }
    }
}
