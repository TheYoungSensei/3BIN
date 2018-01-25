using System;
using System.Linq;
using System.Linq.Expressions;

namespace School.DataAccess
{
    // interface générique pour les repositories

    public interface IRepository
    {
    }
        

    public interface IRepository<T> : IRepository
    {
        void Insert(T entity);
        void Delete(T entity);
        IQueryable<T> SearchFor(Expression<Func<T, bool>> predicate);
        // sauve l'entité si l'élément n'existe pas déjà -> l'existence se base sur le prédicat
        bool Save(T entity,Expression<Func<T, bool>> predicate);
        IQueryable<T> GetAll();
        T GetById(int id);
    }
}
