using System.Collections.Generic;
using ModèleObjet.Model;

namespace School.Model.Repository
{
    public interface ICourseRepository
    {
     
        IList<Course> GetCoursesForSection(string sectionName);
    }
}
