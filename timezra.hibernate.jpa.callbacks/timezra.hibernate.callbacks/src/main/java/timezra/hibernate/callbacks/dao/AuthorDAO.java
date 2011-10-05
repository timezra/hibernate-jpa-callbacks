package timezra.hibernate.callbacks.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import timezra.hibernate.callbacks.domain.Author;

@Repository
public class AuthorDAO {

    @Resource
    private SessionFactory sessionFactory;

    public void create(final Author a) {
        getSession().save(a);
    }

    public Author findByName(final String name) {
        return (Author) getSession().createCriteria(Author.class) //
                .add(Restrictions.eq(Author.NAME_ATTRIBUTE, name)) //
                .uniqueResult();
    }

    public void update(final Author a) {
        getSession().update(a);
    }

    public void delete(final Author a) {
        getSession().delete(a);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
