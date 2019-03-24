package edu.bsuirDev.database.dao;

import edu.bsuirDev.database.models.HibernateSessionFactory;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import edu.bsuirDev.database.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    /**
     *      ВСЁ ИДЁТ ПО ПЛАНУ
     */
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();

        User user = new User("NotDimon", "MyMail", "helloworld");
        Plan plan = new Plan(13, "PPPLan");
        Step step = new Step("String", new Date(100000), 45);
        user.addPlan(plan);
        plan.addStep(step);

        userDAO.save(user);
        User user1 = userDAO.findById(user.getId());
        System.out.println(user1);
        userDAO.deleteById(user.getId());
    }

    public User findById(long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
    }

    public User deleteById(long id) {
        User user = this.findById(id);
        if(user == null) {
            return null;
        } else {
        this.delete(user);
        return user;
        }
    }

    public void save(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (Plan plan : user.getPlans()) {
            for(Step step : plan.getSteps()) {
                session.delete(step);
            }
            plan.setSteps(null);
            session.delete(plan);
        }
        user.setPlans(null);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Plan findPlanById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Plan.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
