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
            //Exo2_1(dc);
            //Exo2_2(dc);
            //Exo2_3(dc);
            //Exo3_1(dc);
            //Exo3_2(dc);
            //Exo3_3(dc);
            //Exo3_4(dc);
            //Exo3_5(dc);
            //Exo3_6(dc);
            //Exo3_7(dc);
            //Exo4_1(dc);
            //Exo4_2(dc);
            //Exo4_3(dc);
            //Exo4_4(dc);
            //Exo4_5(dc);
            //Exo5_1(dc);
            //Exo5_2(dc);
            //Exo5_3(dc);
            //Exo5_4(dc);
            //Exo5_5(dc);
            //Exo5_6(dc);
            //Exo5_7(dc);
            Exo5_8(dc);
            Console.ReadLine();
        }

        private static void Exo2_1(DataContext dc)
        {
            var toPrint = dc.Students.Select(s => new
            {
                FullName = string.Format("{0} {1}" , s.First_Name, s.Last_Name),
                BirthDate = s.BirthDate,
                Login = s.Login,
                Result = s.Year_Result
            });
            foreach(var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "\n" + elem.BirthDate + "\n" + elem.Login + "\n" + elem.Result + "\n");
            }
        }

        private static void Exo2_2(DataContext dc)
        {
            var toPrint = dc.Students.Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                BirthDate = s.BirthDate,
                Id = s.Student_ID
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "\n" + elem.BirthDate + "\n" + elem.Id + "\n");
            }
        }

        private static void Exo2_3(DataContext dc)
        {
            var toPrint = dc.Students.Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                BirthDate = s.BirthDate,
                Id = s.Student_ID
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.BirthDate + "|" + elem.Id + "\n");
            }
        }

        private static void Exo3_1(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => s.BirthDate.Year < 1955).Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                YearResult = s.Year_Result,
                Statut = Reussis(s.Year_Result),
                BirthDate = s.BirthDate
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.BirthDate + "|" + elem.YearResult + "|" + elem.Statut + "\n");
            }
        }

        private static String Reussis(int entier)
        {
            if(entier >= 12)
            {
                return "OK";
            } else
            {
                return "KO";
            }
        }

        private static void Exo3_2(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => s.BirthDate.Year > 1955 && s.BirthDate.Year < 1965).Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                YearResult = s.Year_Result,
                Statut = Categorie(s.Year_Result),
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.YearResult + "|" + elem.Statut + "\n");
            }
        }

        private static String Categorie(int entier)
        {
            if(entier < 10)
            {
                return "inférieure";
            } else if (entier > 10)
            {
                return "supérieure";
            } else
            {
                return "neutre";
            }
        }

        private static void Exo3_3(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => s.Last_Name[s.Last_Name.Length - 1] == 'r').Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                Id = s.Section_ID,
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.Id + "\n");
            }
        }

        private static void Exo3_4(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => s.Year_Result <= 3).OrderBy(s => s.Year_Result).Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                YearResult = s.Year_Result
            });
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.YearResult + "\n");
            }
        }


        private static void Exo3_5(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => s.Section_ID == 1110).Select(s => new
            {
                FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                YearResult = s.Year_Result
            }).OrderBy(s => s.FullName);
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.YearResult + "\n");
            }
        }

        private static void Exo3_6(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => (s.Section_ID == 1110 ||s.Section_ID == 1020) && (s.Year_Result > 18 || s.Year_Result < 12))
                .Select(s => new {
                    FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                    SectionId = s.Section_ID,
                    YearResult = s.Year_Result
            }).OrderByDescending(s => s.SectionId);
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.YearResult + "|" + elem.SectionId + "\n");
            }
        }

        private static void Exo3_7(DataContext dc)
        {
            var toPrint = dc.Students.Where(s => String.Concat(s.Section_ID).Substring(0, 2).Equals("13"))
                .Select(s => new {
                    FullName = string.Format("{0} {1}", s.First_Name, s.Last_Name),
                    SectionId = s.Section_ID,
                    result_100 = s.Year_Result * 100 / 20
                }).Where(s => s.result_100 <= 60).OrderBy(s => s.result_100);
            foreach (var elem in toPrint)
            {
                Console.WriteLine(elem.FullName + "|" + elem.result_100 + "|" + elem.SectionId + "\n");
            }
        }

        private static void Exo4_1(DataContext dc)
        {
            var toPrint = dc.Students.Average(s => s.Year_Result);
            Console.WriteLine(toPrint);
        }

        private static void Exo4_2(DataContext dc)
        {
            var toPrint = dc.Students.Max(s => s.Year_Result);
            Console.WriteLine(toPrint);
        }

        private static void Exo4_3(DataContext dc)
        {
            var toPrint = dc.Students.Sum(s => s.Year_Result);
            Console.WriteLine(toPrint);
        }

        private static void Exo4_4(DataContext dc)
        {
            var toPrint = dc.Students.Min(s => s.Year_Result);
            Console.WriteLine(toPrint);
        }

        private static void Exo4_5(DataContext dc)
        {
            var toPrint = dc.Students.Count();
            Console.WriteLine(toPrint);
        }

        private static void Exo5_1(DataContext dc)
        {
            IEnumerable<IGrouping<int, Student>> toPrint = dc.Students.GroupBy(s => s.Section_ID);
            foreach(IGrouping<int, Student> g in toPrint)
            {
                Console.WriteLine(g.Key + " : " + g.Max(s => s.Year_Result));
            }
        }

        private static void Exo5_2(DataContext dc)
        {
            IEnumerable<IGrouping<int, Student>> toPrint = dc.Students.Where(s => String.Concat(s.Section_ID).Substring(0, 2).Equals("10")).GroupBy(s => s.Section_ID);
            foreach (IGrouping<int, Student> g in toPrint)
            {
                Console.WriteLine(g.Key + " : " + g.Average(s => s.Year_Result));
            }
        }

        private static void Exo5_3(DataContext dc)
        {
            IEnumerable<IGrouping<int, Student>> toPrint = dc.Students.Where(s => s.BirthDate.Year >= 1970 && s.BirthDate.Year <= 1985).GroupBy(s => s.BirthDate.Month);
            foreach (IGrouping<int, Student> g in toPrint)
            {
                Console.WriteLine(g.Key + " : " + g.Average(s => s.Year_Result));
            } 
        }

        private static void Exo5_4(DataContext dc)
        {
            IEnumerable<IGrouping<int, Student>> toPrint = dc.Students.GroupBy(s => s.Section_ID);
            foreach (IGrouping<int, Student> g in toPrint)
            {
                if(g.Count() >= 3)
                {
                    Console.WriteLine(g.Key + " : " + g.Average(s => s.Year_Result));
                }
            }
        }

        private static void Exo5_5(DataContext dc)
        {
            var toPrint = dc.Courses.Join(dc.Professors,
                c => c.Professor_ID,
                p => p.Professor_ID,
                (c, p) => new
                {
                    Course = c.Course_Name,
                    Professor = p.Professor_Name,
                    SectionId = p.Section_ID
                }).Join(dc.Sections,
                c => c.SectionId,
                p => p.Section_ID,
                (c, p) => new
                {
                    Course_name = c.Course,
                    Professor_name = c.Professor,
                    Section_name = p.Section_Name
                });
            foreach(var jointure in toPrint)
            {
                Console.WriteLine(jointure.Course_name + " | " + jointure.Section_name + " | " + jointure.Professor_name);
            }
        }

        private static void Exo5_6(DataContext dc)
        {
            var toPrint = dc.Sections.Join(dc.Students,
                section => section.Delegate_ID,
                student => student.Student_ID,
                (section, student) => new
                {
                    Section_id = section.Section_ID,
                    Section_name = section.Section_Name,
                    Last_name = student.Last_Name
                });
            foreach (var jointure in toPrint)
            {
                Console.WriteLine(jointure.Section_id + " | " + jointure.Section_name + " | " + jointure.Last_name);
            }
        }

        private static void Exo5_7(DataContext dc)
        {

            var toPrint = dc.Sections.GroupJoin(dc.Professors,
                s => s.Section_ID,
                p => p.Section_ID,
                (s, p) => new
                {
                    Section_id = s.Section_ID,
                    Section_name = s.Section_Name,
                    Professors = p
                });
            foreach (var jointure in toPrint)
            {
                Console.WriteLine(jointure.Section_id + " -> " + jointure.Section_name);
                foreach(Professor prof in jointure.Professors)
                {
                    Console.WriteLine(prof.Professor_Name);
                }
            }
        }

        private static void Exo5_8(DataContext dc)
        {

            var toPrint = dc.Sections.GroupJoin(dc.Professors,
                s => s.Section_ID,
                p => p.Section_ID,
                (s, p) => new
                {
                    Section_id = s.Section_ID,
                    Section_name = s.Section_Name,
                    Professors = p
                }).Where(p => p.Professors.Count() >= 1);
            foreach (var jointure in toPrint)
            {
                Console.WriteLine(jointure.Section_id + " -> " + jointure.Section_name);
                foreach (Professor prof in jointure.Professors)
                {
                    Console.WriteLine(prof.Professor_Name);
                }
            }
        }


    }
}
