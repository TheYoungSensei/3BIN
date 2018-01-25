using School.DataAccess;
using System.Collections.Generic;
using System.Linq;
using ModèleObjet.Model;

namespace School.Model.Repository
{
    public class StudentRepository : BaseRepository<Student>,IStudentRepository
    {
       
        public StudentRepository(Model1Container dbcontext) : base(dbcontext) { }
        public bool SaveStudent(Student student)
        {
            Student stud = (from Student s in _dbContext.Set<Student>()
                                where s.Name.Equals(student.Name) && s.Firstname.Equals(student.Firstname)
                                select s).FirstOrDefault();
            if (stud == null)
            {

                //_dbcontext.StudentSet.Add(student); === voir ligne ci-dessous
                _dbContext.Set<Student>().Add(student);
                _dbContext.SaveChanges();
                return true;
            }
            return false;
        }


        public IList<Student> GetStudentBySectionOrderByYearResult()
        {
            IList<Student> students = (from Student s in _dbContext.Set<Student>()
                                            orderby s.Section.Name, s.YearResult descending 
                                            select s).ToList();

            return students;
        }
    }
}
