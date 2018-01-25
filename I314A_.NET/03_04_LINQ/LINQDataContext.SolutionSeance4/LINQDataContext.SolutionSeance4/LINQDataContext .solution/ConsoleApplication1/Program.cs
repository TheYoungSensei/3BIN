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
            var studentView = from Student s in dc.Students
                              select new { s.Last_Name, s.BirthDate, s.Login, s.Year_Result }
                              ;


            // 2.1 version appels méthode

            studentView = dc.Students.Select(s => new { s.Last_Name, s.BirthDate, s.Login, s.Year_Result });
            foreach (var stud in studentView)
            {
                Console.WriteLine("{0} est né le {1} et a obtenu {2}", stud.Last_Name, stud.BirthDate, stud.Year_Result);
            }

            // Exercice 2.2
            var studentV = from Student s in dc.Students
                              select new { FullName = s.Last_Name +" "+s.First_Name, s.Student_ID, s.BirthDate }
                              ;

            foreach (var stud in studentV)
            {
                Console.WriteLine("{0} {1} {2}", stud.FullName, stud.Student_ID, stud.BirthDate);
            }
           // Console.ReadLine();

            // 3.1
            var stud31 = from Student s in dc.Students
                         where s.BirthDate.Year < 1955
                         select new { s.Last_Name, s.BirthDate, s.Year_Result, status=(s.Year_Result>12)?"OK":"KO" };

            Console.WriteLine("Ex 3.1");
            foreach (var stud in stud31)
            {
                Console.WriteLine(stud);
            }
                    
            // 3.2
            var stud32 = from Student s in dc.Students
                         where( s.BirthDate.Year >= 1955 && s.BirthDate.Year <= 1965)
                         select new { s.Last_Name, s.BirthDate, s.Year_Result
                                    , catégorie = (s.Year_Result < 10) ? "inférieure" : (s.Year_Result == 10)?"neutre": "supérieure" };

            Console.WriteLine("Ex 3.2");
            foreach (var stud in stud32)
            {
                Console.WriteLine(stud);
            }

            // 3.3
            var stud33 = from Student s in dc.Students
                         where (s.Last_Name.Last() == 'r')
                         select new
                         {
                             s.Last_Name,
                             s.BirthDate,
                             s.Year_Result,
                             catégorie = (s.Year_Result < 10) ? "inférieure" : (s.Year_Result == 10) ? "neutre" : "supérieure"
                         };

            // Version appel de méthodes
            stud33 = dc.Students.Where(s => s.Last_Name.Last() == 'r')
                        .Select( s => new
                         {
                             s.Last_Name,
                             s.BirthDate,
                             s.Year_Result,
                             catégorie = (s.Year_Result < 10) ? "inférieure" : (s.Year_Result == 10) ? "neutre" : "supérieure"
                         });

            Console.WriteLine("Ex 3.3");
            foreach (var stud in stud33)
            {
                Console.WriteLine(stud);
            }


            // 3.4
            var stud34 = from Student s in dc.Students
                         where (s.Year_Result<=3)
                         orderby s.Year_Result descending
                         select new
                         {
                             s.Last_Name,
                             s.BirthDate,
                             s.Year_Result,
                             catégorie = (s.Year_Result < 10) ? "inférieure" : (s.Year_Result == 10) ? "neutre" : "supérieure"
                         };

            Console.WriteLine("Ex 3.4");
            foreach (var stud in stud34)
            {
                Console.WriteLine(stud);
            }

            // 3.5
            var stud35 = from Student s in dc.Students
                         where (s.Section_ID == 1110)
                         orderby s.Last_Name
                         select new
                         {
                             s.Last_Name,
                             s.BirthDate,
                             s.Year_Result,
                             catégorie = (s.Year_Result < 10) ? "inférieure" : (s.Year_Result == 10) ? "neutre" : "supérieure"
                         };

            Console.WriteLine("Ex 3.5");
            foreach (var stud in stud35)
            {
                Console.WriteLine(stud);
            }


            // 4.1
            double moyenne = dc.Students.Select(s => s.Year_Result).Average();
            Console.WriteLine("Ex 4.1");
            Console.WriteLine("Moyenne = " + moyenne );

        


            // 4.2
            int max = dc.Students.Select(s => s.Year_Result).Max();
            Console.WriteLine("Ex 4.2");
            Console.WriteLine("Max = " + max);
            

            // 4.5
            int nbLignes = dc.Students.Select(s => s.Year_Result).Count();
            Console.WriteLine("Ex 4.3");
            Console.WriteLine("count = " + nbLignes);
           

            // 5.1 

            // groupement par section
            var sectionsResult = from Student s in dc.Students
                                 group s by s.Section_ID;

            
            
            Console.WriteLine("Ex 5.1");
            foreach (IGrouping<Int32, Student> section in sectionsResult)
            {
               Console.WriteLine("Le max de la section {0} est {1}", section.Key, section.Select(s =>  s.Year_Result ).Max());
               
            }
    

            // 5.3 

            var resultMoy = from Student s in dc.Students
                            where (s.BirthDate.Year >= 1970
                            && s.BirthDate.Year <= 1985)
                            group s by s.BirthDate.Month;

            Console.WriteLine("Ex 5.3");

            foreach (IGrouping<Int32,Student> res in resultMoy)
            {
                Console.WriteLine("Mois : " + res.Key);
                Console.WriteLine("result moyen : "+res.Select(s => s.Year_Result).Average());
            }


        


            // 5.5

           var query = from Cours in dc.Courses join prof in dc.Professors on Cours.Professor_ID equals prof.Professor_ID 
                       join sect in dc.Sections on prof.Section_ID equals sect.Section_ID
                        select new { Cours.Course_Name, prof.Professor_Name, sect.Section_Name};

           Console.WriteLine("5.5");
           foreach (var res in query)
           {
               Console.WriteLine(res.Course_Name + " " + res.Section_Name + " " + res.Professor_Name);
           }

            // 5.7 

           // groupjoin
           Console.WriteLine("5.7");
           var query5_7 = from s in dc.Sections
                          join p in dc.Professors on s.Section_ID equals p.Section_ID into sectProfs
                          select new { nomsection = s.Section_Name, nomsprofs = sectProfs };

            foreach (var jointure in query5_7)
            {
                Console.WriteLine("Section : " + jointure.nomsection);
                foreach (Professor prof in jointure.nomsprofs)
                {
                    Console.WriteLine("Prof : " + prof.Professor_Name);
                }
            }

            // 5.8 

            // groupjoin + test count
            Console.WriteLine("5.8");
            var query5_8 = from s in dc.Sections
                           join p in dc.Professors on s.Section_ID equals p.Section_ID into sectProfs
                           select new { nomsection = s.Section_Name, nomsprofs = sectProfs };

            foreach (var jointure in query5_7)
            {
                 if (jointure.nomsprofs.Count() > 0)
                 {
                    Console.WriteLine("Section : " + jointure.nomsection);
               
                    foreach (Professor prof in jointure.nomsprofs)
                    {
                        Console.WriteLine("Prof : " + prof.Professor_Name);
                    }
                }
            }

       
           

         
                 
           
            



            Console.ReadLine();
          
        }
    }
}
