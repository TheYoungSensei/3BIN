using System.Collections.Generic;
using System.Linq;
using School.DataAccess;
using ModèleObjet.Model;

namespace School.Model.Repository
{
    public class CourseRepository : BaseRepository<Course>, ICourseRepository
    {

        public CourseRepository(Model1Container dbContext) : base(dbContext) { }


        public IList<Course> GetCoursesForSection(string sectionName)
        {
            IList<Course> courses = (from Course c in _dbContext.Set<Course>()
                                     where c.Professor.Section.Name.Equals(sectionName)
                                    select c).ToList();
            return courses;

        }


    }
}
