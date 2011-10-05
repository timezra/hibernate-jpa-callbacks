package timezra.hibernate.jpa.event_listener_callbacks.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import timezra.hibernate.jpa.event_listener_callbacks.dao.AuthorDAO;
import timezra.hibernate.jpa.event_listener_callbacks.domain.Author;

@Service
public class AuthorService {

    @Resource
    private AuthorDAO authorDAO;

    @Transactional
    public void create(final Author a) {
        authorDAO.create(a);
    }

    @Transactional
    public void update(final Author a) {
        authorDAO.update(a);
    }

    @Transactional
    public void delete(final Author a) {
        authorDAO.delete(a);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Author findByName(final String name) {
        return authorDAO.findByName(name);
    }
}
