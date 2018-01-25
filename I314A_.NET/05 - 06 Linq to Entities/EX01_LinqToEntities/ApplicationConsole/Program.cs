using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApplicationConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            NorthwindEntities northwind = new NorthwindEntities();
            // Exercice 1
            var customers = northwind.Customers.Where(c => c.City.Equals("LA")).Select(c => c);
            /*var yolo = northwind.Suppliers.Select(s => s);
            foreach(var stud in yolo)
            {
                Console.WriteLine(stud.Country);
            }*/
            // Exercice 2
            IQueryable<Suppliers> suppliers = northwind.Suppliers.Where(s => s.Country.Equals("UK")).Select(s => s).OrderBy(s => s.Country);
            foreach(Suppliers s in suppliers.Distinct())
            {
                foreach(Products p in s.Products)
                {
                    Console.WriteLine(p.ProductName);
                }
            }
            // Exercice 3
            suppliers = northwind.Suppliers.Include("Products").Where(s => s.Country == "UK").OrderBy(s => s.Country).Select(s => s);
            foreach(Suppliers s in suppliers)
            {
                foreach(Products p in s.Products)
                {
                    Console.WriteLine(p.ProductName);
                }
            }
            var queryOrders = northwind.Orders.Where(o => o.CustomerID == "ALFKI" && o.ShippedDate != null).OrderByDescending(o => o.OrderDate).Select(o => new
            {
                o.CustomerID,
                o.OrderDate,
                o.ShippedDate
            });
            foreach(var od in queryOrders)
            {
                Console.WriteLine("CustomerID : " + od.CustomerID + " OrderDate : " + od.OrderDate + " ShippedDate : " + od.ShippedDate);
            }
            var query = northwind.Order_Details.GroupBy(o => o.ProductID);
            foreach(var orderDetails in query)
            {
                Console.WriteLine(orderDetails.Key + " : " + orderDetails.Sum(o => o.UnitPrice * o.Quantity));
            }
            Console.WriteLine("Updates");
            IQueryable<Customers> custom = northwind.Customers.Select(c => c);
            foreach(Customers cust in custom)
            {
                cust.ContactName = cust.ContactName.ToUpper();
            }
            try
            {
                northwind.SaveChanges();
            } catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            Console.WriteLine("Done");
            custom = northwind.Customers.Select(c => c);
            foreach (Customers cust in custom)
            {
                Console.WriteLine(cust.ContactName);
            }
            IQueryable<Categories> cats = northwind.Categories.Select(c => c);
            foreach(Categories cat in cats)
            {
                Console.WriteLine(cat.CategoryName);
            }
            try
            {
                Categories cat = northwind.Categories.Where(c => c.CategoryName == "Beverages").Select(c => c).First();
                northwind.Categories.Remove(cat);
                northwind.SaveChanges();
            } catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            cats = northwind.Categories.Select(c => c);
            foreach (Categories cat in cats)
            {
                Console.WriteLine(cat.CategoryName);
            }
            IQueryable<Employees> empls = northwind.Employees.Select(e => e);
            foreach (Employees e in empls)
            {
                Console.WriteLine(e.EmployeeID);
                IQueryable<Orders> ords = northwind.Orders.Where(o => o.EmployeeID == e.EmployeeID).Select(o => o);
                foreach (Orders or in ords)
                {
                    Console.WriteLine(or.OrderID);
                }
            }
            Employees employee1 = (from e in northwind.Employees
                                  where e.EmployeeID == 4
                                  select e).Single<Employees>();
            Employees employee2 = (from e in northwind.Employees
                                  where e.EmployeeID == 5
                                  select e).Single<Employees>();
            IQueryable<Orders> employee1Orders = (from o in northwind.Orders
                                                 where o.EmployeeID == 4
                                                 select o);
            foreach (Orders o in employee1Orders)
            {
                employee2.Orders.Add(o);
                employee1.Orders.Remove(o);

            }
            employee1.Orders.Clear();
            employee1.Territories.Clear();
            employee1.Employees1.Clear();
            northwind.Employees.Remove(employee1);
            northwind.SaveChanges();
           empls = northwind.Employees.Select(e => e);
            foreach (Employees e in empls)
            {
                Console.WriteLine(e.EmployeeID);
                IQueryable<Orders> ords = northwind.Orders.Where(o => o.EmployeeID == e.EmployeeID).Select(o => o);
                foreach (Orders or in ords)
                {
                    Console.WriteLine(or.OrderID);
                }
            }
            Console.ReadLine();
        }
    }
}
