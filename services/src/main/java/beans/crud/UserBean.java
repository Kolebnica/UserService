package beans.crud;

import entities.User;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Inject
    @Metric(name="userDbCall")
    private Counter counter;

    @Transactional
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    /**
     * Retrieves user by username
     * @param username
     * @return User object if exists, or null
     */
    //@Counted(name = "gerUsersFromDb", monotonic = true)
    public User getUserByUsername(String username) {
        counter.inc();
        TypedQuery<User> q = em.createNamedQuery("User.getByUsername", User.class);
        q.setParameter("username", username);

        User u = null;
        try {
            u = q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ignored) { }

        return u;
    }

    //@Counted(name = "gerUsersFromDb", monotonic = true)
    public List<User> getAllUsers(){
        counter.inc();
        TypedQuery<User> q = em.createNamedQuery("User.getUsers", User.class);
        return q.getResultList();
    }

    public boolean existsUser(int id) {
        return getUser(id) != null;
    }

    //@Counted(name = "insertUsersInDb", monotonic = true)
    @Transactional
    public User insertUser(User u) {
        counter.inc();
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
