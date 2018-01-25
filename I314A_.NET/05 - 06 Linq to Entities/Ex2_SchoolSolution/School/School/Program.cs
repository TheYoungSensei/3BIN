using System;
using System.Collections.Generic;
using System.Linq;
using ModèleObjet.Model;
using School.Model.Repository;

namespace School
{
    class Program
    {
        static void Main()
        {
            Model1Container model = new Model1Container();

            // création repositories

            SectionRepository repoSect = new SectionRepository(model);
            StudentRepository repoStud = new StudentRepository(model);
            CourseRepository repoCourse = new CourseRepository(model);
            ProfessorRepository repoProfessor = new ProfessorRepository(model);

            // ajout de 2 sections
            Section sectInfo = new Section {Name = "Info"};
            repoSect.Save(sectInfo, s => s.Name.Equals(sectInfo.Name));
            Section sectDiet = new Section { Name = "Diet" };
            repoSect.Save(sectDiet, s => s.Name.Equals(sectDiet.Name)); 

            // renvoyer toutes les sections

            IList<Section> sections = repoSect.GetAll().ToList();

            Console.WriteLine("----------- SECTIONS --------------------");
            foreach (Section s in sections)
            {
                Console.WriteLine(s.Name);
            }
            Console.WriteLine("-----------------------------------------");


            // ajout de 3 étudiants
            Student studinfo = new Student
            {
                Firstname = "studinfo",
                Name = "studinfo",
                Section = sectInfo,
                YearResult = 100
            };
           
            Student studdiet = new Student
            {
                Firstname = "studdiet",
                Name = "studdiet",
                Section = sectDiet,
                YearResult = 150
            };
           

            Student studinfo2 = new Student
            {
                Firstname = "studinfo2",
                Name = "studinfo2",
                Section = sectInfo,
                YearResult = 110
            };

            repoStud.Save(studinfo, s => s.Name.Equals(studinfo.Name) && s.Firstname.Equals(studinfo.Firstname));
            repoStud.Save(studinfo2, s => s.Name.Equals(studinfo2.Name) && s.Firstname.Equals(studinfo2.Firstname));
            repoStud.Save(studdiet, s => s.Name.Equals(studdiet.Name) && s.Firstname.Equals(studdiet.Firstname));

            IList<Student> studs = repoStud.GetStudentBySectionOrderByYearResult();

            foreach (Student s in studs)
            {
                Console.WriteLine("SECTION : "+ s.Section.Name +" STUD : "+s.Name+ " YEAR_RESULT : "+s.YearResult);
            }

        

            // créer 2 professeurs
            Professor profDiet = new Professor {Firstname = "profDiet", Name = "profDiet", Section = sectDiet};
            repoProfessor.Save(profDiet, p => p.Name.Equals(profDiet.Name));
            Professor profInfo = new Professor { Firstname = "profInfo", Name = "profInfo", Section = sectInfo };
            repoProfessor.Save(profInfo, p => p.Name.Equals(profInfo.Name));

            // créer 3 cours
            Course techCulinaire = new Course {Name = "Technique Culinaire", Professor = profDiet};
            repoCourse.Save(techCulinaire, c => c.Name.Equals(techCulinaire.Name));
            Course mathDiet = new Course { Name = "Math", Professor = profDiet };
            repoCourse.Save(mathDiet, c => c.Name.Equals(mathDiet.Name));
            Course mathInfo = new Course { Name = "Math", Professor = profInfo };
            repoCourse.Save(mathInfo, c => c.Name.Equals(mathInfo.Name));

            // liste des cours de la section diet
            IList<Course> courses = repoCourse.GetCoursesForSection("Diet");
            Console.WriteLine("Cours de la section Diet : ");
            foreach (Course c in courses)
            {
                Console.WriteLine("COURS : " + c.Name + "PROFESSOR " + c.Professor.Firstname);
            }

            Console.ReadLine();
        }
    }
}
