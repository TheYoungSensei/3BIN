using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using LINQDataContext;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            DataContext dc = new DataContext();
            // Exercice 2.1
            var students = dc.Students.Select(s => new {
               s.Last_Name,
               s.BirthDate,
               s.Login,
               s.Year_Result
            });
            foreach(var stud in students) {
                Console.WriteLine("{0} est né le {1} a obtenu {2} ",
                    stud.Last_Name, stud.BirthDate, stud.Year_Result);
            }
            Console.WriteLine("--------");
            // Exercice 2.2
            var students2 = dc.Students.Select(s => new {
                FullName = s.First_Name+" "+s.Last_Name,
                s.Student_ID,
                s.BirthDate
            });
            foreach(var stud in students2)
            {
                Console.WriteLine("{0} - {1} est né le {2}",
                    stud.Student_ID,stud.FullName, stud.BirthDate);
            }    
            // Exercice 2.3 => Je suis pas venus ici pour souffrir Okay ?
            // Exercice 3.1
            var students3 = dc.Students.Where(s => s.BirthDate.Year < 1955).Select(s => new
            {
                s.First_Name,
                s.Year_Result,
                Status = (s.Year_Result> 12)?"OK":"KO"
            });
            Console.WriteLine("-----------");
            foreach (var stud in students3)
            {
                Console.WriteLine("{0} | {1} | {2} ",
                    stud.First_Name, stud.Year_Result, stud.Status);
            }
            // Exercice 3.3
            var students4 = dc.Students.Where(s => s.Last_Name.Last() == 'r').Select(s => new
            {
                s.Student_ID,
                s.Section_ID
            });
            // Exercice 3.5
            var students5 = dc.Students.Where(s => s.Section_ID == 1110).Select(s => new
            {
                FullName = s.First_Name+" "+s.Last_Name,
                s.Year_Result
            }).OrderBy(s => s.Year_Result);
            var students6 = dc.Students.Where(s => s.Section_ID.ToString().Substring(0,2) == "13")
                .Select(s => new
            {
                s.First_Name,
                s.Section_ID,
                colonne_result_100 = s.Year_Result * 5
            }).Where(s => s.colonne_result_100 <= 60).OrderByDescending(s => s.colonne_result_100);
            // Exercice 4.1
            var resultatAnnuelMoyen = dc.Students.Average(s => s.Year_Result);
            Console.WriteLine(resultatAnnuelMoyen);
            // Exercice 4.2
            var resultatMax = dc.Students.Max(s => s.Year_Result);
            Console.WriteLine(resultatMax);
            // Exercice 4.3
            var sommeResultats = dc.Students.Sum(s => s.Year_Result);
            Console.WriteLine(sommeResultats);
            // Exercice 4.4
            var resultatMin = dc.Students.Min(s => s.Year_Result);
            Console.WriteLine(resultatMin);
            // Exercice 4.5
            var nbLignes = dc.Students.Count();
            Console.WriteLine(nbLignes);
            // Exercice 5.1
            var students7 = dc.Students.GroupBy(s => s.Section_ID);
            foreach(var stud in students7)
            {
                Console.WriteLine("{0} {1}", stud.Key, stud.Max(s => s.Year_Result));
            }
            // Exercice 5.3
            var resultMoy = dc.Students.Where(s => s.BirthDate.Year >= 1970 && s.BirthDate.Year <= 1985).GroupBy(s => s.BirthDate.Month);
            foreach(var stud in resultMoy)
            {
                Console.WriteLine("Mois : " + stud.Key);
                Console.WriteLine("result moyen : " + stud.Average(s => s.Year_Result));
            }
            // Exercice 5.5
            var query = from Cours in dc.Courses
                        join Prof in dc.Professors on Cours.Professor_ID equals Prof.Professor_ID
                        join Sect in dc.Sections on Prof.Section_ID equals Sect.Section_ID
                        select new { Cours.Course_Name, Prof.Professor_Name, Sect.Section_Name };
            // Exercice 5.7
            var query2 = from s in dc.Sections
                         join p in dc.Professors on s.Section_ID equals p.Section_ID into sectProfs
                         select new { nomsection = s.Section_Name, nomsprofs = sectProfs };
            foreach(var jointure in query2)
            {
                Console.WriteLine("Section : " + jointure.nomsection);
                foreach(Professor prof in jointure.nomsprofs)
                {
                    Console.WriteLine("Prof : " + prof.Professor_Name);
                }
            }
            Console.ReadLine();
        }
    }
}
