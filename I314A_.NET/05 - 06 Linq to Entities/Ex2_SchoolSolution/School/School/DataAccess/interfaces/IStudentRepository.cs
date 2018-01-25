using ModèleObjet.Model;
using System.Collections.Generic;

namespace School.Model.Repository
{
    public interface IStudentRepository
    {
       
        IList<Student> GetStudentBySectionOrderByYearResult();
    }
}
