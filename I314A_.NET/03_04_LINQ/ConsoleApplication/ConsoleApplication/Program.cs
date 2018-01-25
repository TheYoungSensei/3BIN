using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication
{
    public static class StringExtension
    {
        public static String Reverse(this string s)
        {
            string reversed = "";
            foreach(char c in s)
            {
                reversed = c + reversed;
            }
            return reversed;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Bonjour".Reverse());
            Console.ReadLine();
        }
    }
}
