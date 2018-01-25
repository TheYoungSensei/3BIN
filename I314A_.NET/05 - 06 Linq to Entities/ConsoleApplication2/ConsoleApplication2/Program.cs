using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication2
{
    class Program
    {
        static void Main(string[] args)
        {
            Model1Container model = new Model1Container();
            SectionRepository sectionRepository = new SectionRepository(model);
            StudentRepository studentRepository = new StudentRepository(model);
            Section sectInfo = new Section { Name = "Info" };
            sectionRepository.Save(sectInfo, s => s.Name.Equals(sectInfo.Name));
            Section sectDiet = new Section { Name = "Diet" };
            sectionRepository.Save(sectDiet, s => s.Name.Equals(sectDiet.Name));
            IList<Section> sections = sectionRepository.GetAll().ToList();
            foreach(Section s in sections)
            {
                Console.WriteLine(s.Name);
            }
            Student studinfo1 = new Student { Name = "StudInfo", Firstname ="StudInfo", Section = sectInfo, YearResult = "100" };
            Student studdiet = new Student { Name = "StudDiet", Firstname = "StudDiet", Section = sectDiet, YearResult = "120" };
            Student studinfo2 = new Student { Name = "StudInfo2", Firstname = "StudInfo", Section = sectInfo, YearResult = "110" };
            studentRepository.Save(studinfo1, s => s.Name.Equals(studinfo1.Name) && s.Firstname.Equals(studinfo1.Firstname));
            studentRepository.Save(studinfo2, s => s.Name.Equals(studinfo2.Name) && s.Firstname.Equals(studinfo2.Firstname));
            studentRepository.Save(studdiet, s => s.Name.Equals(studdiet.Name) && s.Firstname.Equals(studdiet.Firstname));
            IList<Student> students = studentRepository.GetStudentBySectionOrderByYearResult();
            foreach(Student s in students)
            {
                Console.WriteLine(s.Name + " en " + s.Section.Name + " : " + s.YearResult);
            }
            Console.ReadLine();
          }
    }
}
