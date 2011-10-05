package timezra.jpa.callbacks.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import timezra.jpa.callbacks.domain.Author;

@Repository
public class AuthorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(final Author a) {
        entityManager.persist(a);
    }

    public Author findByName(final String name) {
        final CriteriaQuery<Author> query = entityManager.getCriteriaBuilder().createQuery(Author.class);
        final Root<Author> author = query.from(Author.class);
        query.where(author.get(Author.NAME_ATTRIBUTE).in(name));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Author update(final Author a) {
        return entityManager.merge(a); // need to return the managed instance
    }

    public void delete(final Author a) {
        entityManager.remove(entityManager.contains(a) ? a : entityManager.find(Author.class, a.getId()));
    }
}
