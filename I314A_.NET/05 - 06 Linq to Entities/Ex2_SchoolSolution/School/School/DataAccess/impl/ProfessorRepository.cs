using School.DataAccess;
using ModèleObjet.Model;

namespace School.Model.Repository
{
    public class ProfessorRepository : BaseRepository<Professor>, IProfessorRepository
    {

        public ProfessorRepository(Model1Container dbcontext) : base(dbcontext)
        {
        }


     
    }
}
