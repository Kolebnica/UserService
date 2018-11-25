package beans.crud;

import entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Transactional
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    /**
     * Retrieves user by username
     * @param username
     * @return User object if exists, or null
     */
    public User getUserByUsername(String username) {
        TypedQuery<User> q = em.createNamedQuery("User.getByUsername", User.class);
        q.setParameter("username", username);

        User u = null;
        try {
            u = q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ignored) { }

        return u;
    }


    public List<User> getAllUsers(){
        TypedQuery<User> q = em.createNamedQuery("User.getUsers", User.class);
        return q.getResultList();
    }

    public boolean existsUser(int id) {
        return getUser(id) != null;
    }

    @Transactional
    public User insertUser(User u) {
        em.persist(u);
        em.flush();
        return u;
    }

    @Transactional
    public User updateUser(User u) {
        em.merge(u);
        em.flush();
        return u;
    }

    public void deleteUser(int id) {
        // TODO
    }

}
