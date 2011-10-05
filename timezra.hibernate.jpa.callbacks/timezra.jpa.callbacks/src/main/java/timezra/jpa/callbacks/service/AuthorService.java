package timezra.jpa.callbacks.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import timezra.jpa.callbacks.dao.AuthorDAO;
import timezra.jpa.callbacks.domain.Author;

@Service
public class AuthorService {

    @Resource
    private AuthorDAO authorDAO;

    @Transactional
    public void create(final Author a) {
        authorDAO.create(a);
    }

    @Transactional
    public Author update(final Author a) {
        return authorDAO.update(a);
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
