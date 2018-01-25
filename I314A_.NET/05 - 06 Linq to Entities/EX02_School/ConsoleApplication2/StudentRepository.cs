using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication2
{
    class StudentRepository : BaseRepository<Student>, IStudentRepository
    {


        public StudentRepository(Model1Container dbcontext) : base(dbcontext) { }
    

        public IList<Student> GetStudentBySectionOrderByYearResult()
        {
            IList<Student> students = _dbContext.Set<Student>().OrderBy(s => s.Section.Name).ThenBy(s => s.YearResult).Select(s => s).ToList();
            return students;
        }

        public bool SaveStudent(Student student)
        {
            Student stud = (from Student s in _dbContext.Set<Student>()
                            where s.Name.Equals(student.Name) && s.Firstname.Equals(student.Firstname)
                            select s).FirstOrDefault();
            if (stud == null)
            {
                _dbContext.Set<Student>().Add(student);
                _dbContext.SaveChanges();
                return true;
            }
            return false;
        }
    }
}
