
using System;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using ModèleObjet.Model;

namespace School.DataAccess
{

    public class BaseRepository<TEntity> : IRepository<TEntity> where TEntity : class
    {

        protected readonly DbContext _dbContext;
        public BaseRepository(Model1Container dbContext)
        {
            _dbContext = dbContext;
        }

        public void Insert(TEntity entity)
        {
 
            _dbContext.Set<TEntity>().Add(entity);
 
            _dbContext.SaveChanges();
        }

        public void Delete(TEntity entity)
        {
            _dbContext.Set<TEntity>().Remove(entity);
            _dbContext.SaveChanges();
        }

        public IQueryable<TEntity> SearchFor(Expression<Func<TEntity, bool>> predicate)
        {
            return _dbContext.Set<TEntity>().Where(predicate);
        }

        public IQueryable<TEntity> GetAll()
        {
            return _dbContext.Set<TEntity>();
        }

        public TEntity GetById(int id)
        {
            return _dbContext.Set<TEntity>().Find(id);
        }

        public bool Save(TEntity entity, Expression<Func<TEntity, bool>> predicate)
        {
            TEntity ent = (SearchFor(predicate)).FirstOrDefault();

            if (ent == null)
            {
                Insert(entity);
                return true;
            }

            return false;
        }
    }
}
