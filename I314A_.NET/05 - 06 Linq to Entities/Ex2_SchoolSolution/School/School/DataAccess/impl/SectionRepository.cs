using School.DataAccess;
using ModèleObjet.Model;

namespace School.Model.Repository
{
    public class SectionRepository : BaseRepository<Section>, ISectionRepository
    {

        public SectionRepository(Model1Container _dbContext) : base(_dbContext) { }


    }
}
