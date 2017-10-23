using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LINQDataContext
{
    public class DataContext
    {
        public readonly List<Student> Students;
        public readonly List<Section> Sections;
        public readonly List<Professor> Professors;
        public readonly List<Course> Courses;
        public readonly List<Grade> Grades;

        public DataContext()
        {
            this.Students = new List<Student>();
            this.Students.AddRange(new Student[] {
                new Student(){ Student_ID = 1, First_Name = "Georges", Last_Name = "Lucas", BirthDate = new DateTime(1944,5,17), Login = "glucas", Section_ID = 1320, Year_Result = 10, Course_ID = "EG2210"},
                new Student(){ Student_ID = 2, First_Name = "Clint", Last_Name = "Eastwood", BirthDate = new DateTime(1930,5,31), Login = "ceastwoo", Section_ID = 1010, Year_Result = 4, Course_ID = "EG2210"},
                new Student(){ Student_ID = 3, First_Name = "Sean", Last_Name = "Connery", BirthDate = new DateTime(1930,8,25), Login = "sconnery", Section_ID = 1020, Year_Result = 12, Course_ID = "EG2110"},
                new Student(){ Student_ID = 4, First_Name = "Robert", Last_Name = "De Niro", BirthDate = new DateTime(1943,8,17), Login = "rde niro", Section_ID = 1110, Year_Result = 3, Course_ID = "EG2210"},
                new Student(){ Student_ID = 5, First_Name = "Kevin", Last_Name = "Bacon", BirthDate = new DateTime(1958,7,8), Login = "kbacon", Section_ID = 1120, Year_Result = 16, Course_ID = "0"},
                new Student(){ Student_ID = 6, First_Name = "Kim", Last_Name = "Basinger", BirthDate = new DateTime(1953,12,8), Login = "kbasinge", Section_ID = 1310, Year_Result = 19, Course_ID = "0"},
                new Student(){ Student_ID = 7, First_Name = "Johnny", Last_Name = "Depp", BirthDate = new DateTime(1963,6,9), Login = "jdepp", Section_ID = 1110, Year_Result = 11, Course_ID = "EG2210"},
                new Student(){ Student_ID = 8, First_Name = "Julia", Last_Name = "Roberts", BirthDate = new DateTime(1967,10,28), Login = "jroberts", Section_ID = 1120, Year_Result = 17, Course_ID = "0"},
                new Student(){ Student_ID = 9, First_Name = "Natalie", Last_Name = "Portman", BirthDate = new DateTime(1981,6,9), Login = "nportman", Section_ID = 1010, Year_Result = 4, Course_ID = "EG2210"},
                new Student(){ Student_ID = 10, First_Name = "Georges", Last_Name = "Clooney", BirthDate = new DateTime(1961,5,6), Login = "gclooney", Section_ID = 1020, Year_Result = 4, Course_ID = "EG2110"},
                new Student(){ Student_ID = 11, First_Name = "Andy", Last_Name = "Garcia", BirthDate = new DateTime(1956,4,12), Login = "agarcia", Section_ID = 1110, Year_Result = 19, Course_ID = "0"},
                new Student(){ Student_ID = 12, First_Name = "Bruce", Last_Name = "Willis", BirthDate = new DateTime(1955,3,19), Login = "bwillis", Section_ID = 1010, Year_Result = 6, Course_ID = "EG2210"},
                new Student(){ Student_ID = 13, First_Name = "Tom", Last_Name = "Cruise", BirthDate = new DateTime(1962,7,3), Login = "tcruise", Section_ID = 1020, Year_Result = 4, Course_ID = "EG2110"},
                new Student(){ Student_ID = 14, First_Name = "Reese", Last_Name = "Witherspoon", BirthDate = new DateTime(1976,3,22), Login = "rwithers", Section_ID = 1020, Year_Result = 7, Course_ID = "EG1020"},
                new Student(){ Student_ID = 15, First_Name = "Sophie", Last_Name = "Marceau", BirthDate = new DateTime(1966,11,17), Login = "smarceau", Section_ID = 1110, Year_Result = 6, Course_ID = "0"},
                new Student(){ Student_ID = 16, First_Name = "Sarah", Last_Name = "Michelle Gellar", BirthDate = new DateTime(1977,4,14), Login = "smichell", Section_ID = 1020, Year_Result = 7, Course_ID = "EG2110"},
                new Student(){ Student_ID = 17, First_Name = "Alyssa", Last_Name = "Milano", BirthDate = new DateTime(1972,12,19), Login = "amilano", Section_ID = 1110, Year_Result = 7, Course_ID = "0"},
                new Student(){ Student_ID = 18, First_Name = "Jennifer", Last_Name = "Garner", BirthDate = new DateTime(1972,4,17), Login = "jgarner", Section_ID = 1120, Year_Result = 18, Course_ID = "0"},
                new Student(){ Student_ID = 19, First_Name = "Michael J.", Last_Name = "Fox", BirthDate = new DateTime(1969,6,20), Login = "mfox", Section_ID = 1310, Year_Result = 3, Course_ID = "0"},
                new Student(){ Student_ID = 20, First_Name = "Tom", Last_Name = "Hanks", BirthDate = new DateTime(1956,7,9), Login = "thanks", Section_ID = 1020, Year_Result = 8, Course_ID = "EG2110"},
                new Student(){ Student_ID = 21, First_Name = "David", Last_Name = "Morse", BirthDate = new DateTime(1953,10,11), Login = "dmorse", Section_ID = 1110, Year_Result = 2, Course_ID = "0"},
                new Student(){ Student_ID = 22, First_Name = "Sandra", Last_Name = "Bullock", BirthDate = new DateTime(1964,7,26), Login = "sbullock", Section_ID = 1010, Year_Result = 2, Course_ID = "EG1020"},
                new Student(){ Student_ID = 23, First_Name = "Keanu", Last_Name = "Reeves", BirthDate = new DateTime(1964,9,2), Login = "kreeves", Section_ID = 1020, Year_Result = 10, Course_ID = "EG2210"},
                new Student(){ Student_ID = 24, First_Name = "Shannen", Last_Name = "Doherty", BirthDate = new DateTime(1971,4,12), Login = "sdoherty", Section_ID = 1320, Year_Result = 2, Course_ID = "EG2120"},
                new Student(){ Student_ID = 25, First_Name = "Halle", Last_Name = "Berry", BirthDate = new DateTime(1966,8,14), Login = "hberry", Section_ID = 1320, Year_Result = 18, Course_ID = "EG2210"},
            });
            this.Sections = new List<Section>();
            this.Sections.AddRange(new Section[] {
                new Section(){ Section_ID = 1010, Section_Name = "BSc Management", Delegate_ID = 12},
                new Section(){ Section_ID = 1020, Section_Name = "MSc Management", Delegate_ID = 9},
                new Section(){ Section_ID = 1110, Section_Name = "BSc Economics", Delegate_ID = 15},
                new Section(){ Section_ID = 1120, Section_Name = "MSc Economics", Delegate_ID = 6},
                new Section(){ Section_ID = 1310, Section_Name = "BA Sociology", Delegate_ID = 23},
                new Section(){ Section_ID = 1320, Section_Name = "MA Sociology", Delegate_ID = 6},
            });
            this.Professors = new List<Professor>();
            this.Professors.AddRange(new Professor[] {
                new Professor(){ Professor_ID = 1, Professor_Name = "zidda", Professor_Surname = "pietro", Section_ID = 1020, Professor_Office = 402, Professor_Email = "pzidda", Professor_HireDate = new DateTime(2004,12,11), Professor_Wage = 1900,},
                new Professor(){ Professor_ID = 2, Professor_Name = "decrop", Professor_Surname = "alain", Section_ID = 1120, Professor_Office = 403, Professor_Email = "adecrop", Professor_HireDate = new DateTime(2003,5,9), Professor_Wage = 1950,},
                new Professor(){ Professor_ID = 3, Professor_Name = "giot", Professor_Surname = "pierre", Section_ID = 1310, Professor_Office = 404, Professor_Email = "pgiot", Professor_HireDate = new DateTime(2002,12,21), Professor_Wage = 2100,},
                new Professor(){ Professor_ID = 4, Professor_Name = "lecourt", Professor_Surname = "christelle", Section_ID = 1310, Professor_Office = 406, Professor_Email = "clecourt", Professor_HireDate = new DateTime(2003,5,7), Professor_Wage = 1750,},
                new Professor(){ Professor_ID = 5, Professor_Name = "scheppens", Professor_Surname = "georges", Section_ID = 1020, Professor_Office = 410, Professor_Email = "gscheppens", Professor_HireDate = new DateTime(1986,10,9), Professor_Wage = 2450,},
                new Professor(){ Professor_ID = 6, Professor_Name = "louveaux", Professor_Surname = "francois", Section_ID = 1110, Professor_Office = 407, Professor_Email = "flouveaux", Professor_HireDate = new DateTime(1990,5,7), Professor_Wage = 2200,},
            });
            this.Courses = new List<Course>();
            this.Courses.AddRange(new Course[] {
                new Course(){ Course_ID = "ECGE2183", Course_Name = "Financial Management", Course_Ects = 4.0F, Professor_ID = 3},
                new Course(){ Course_ID = "ECGE2184", Course_Name = "Marketing management", Course_Ects = 3.5F, Professor_ID = 2},
                new Course(){ Course_ID = "EING2234", Course_Name = "Derivatives", Course_Ects = 3.0F, Professor_ID = 3},
                new Course(){ Course_ID = "EING2283", Course_Name = "Marketing engineering", Course_Ects = 4.0F, Professor_ID = 1},
                new Course(){ Course_ID = "EING2383", Course_Name = "Supply chain management et e-business", Course_Ects = 2.5F, Professor_ID = 5}
            });
            this.Grades = new List<Grade>();
            this.Grades.AddRange(new Grade[] {
                new Grade(){ GradeName = "B ", Lower_Bound = 14, Upper_Bound = 15},
                new Grade(){ GradeName = "E ", Lower_Bound = 18, Upper_Bound = 20},
                new Grade(){ GradeName = "F ", Lower_Bound = 10, Upper_Bound = 11},
                new Grade(){ GradeName = "I ", Lower_Bound = 8, Upper_Bound = 9},
                new Grade(){ GradeName = "IG", Lower_Bound = 0, Upper_Bound = 7},
                new Grade(){ GradeName = "S ", Lower_Bound = 12, Upper_Bound = 13},
                new Grade(){ GradeName = "TB", Lower_Bound = 16, Upper_Bound = 17},
            });
        }
    }
}
