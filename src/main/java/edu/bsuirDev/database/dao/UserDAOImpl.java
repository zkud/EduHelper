package edu.bsuirDev.database.dao;

import edu.bsuirDev.database.models.HibernateSessionFactory;
import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import edu.bsuirDev.database.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    /**
     *      ВСЁ ИДЁТ ПО ПЛАНУ
     */
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        long id = 1;
        Step step = HibernateSessionFactory.getSessionFactory().openSession().get(Step.class, id);
        System.out.println(step);
    }

    public User findById(long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
    }

    public User deleteById(long id) {
        User user = this.findById(id);
        if(user == null) {
            return null;
        } else {
        this.deleteUser(user);
        return user;
        }
    }

    public void saveUser(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void savePlan(User user, Plan plan) {
        user.addPlan(plan);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(plan);
        tx1.commit();
        session.close();
    }

    public void saveStep(Plan plan, Step step) {
        plan.addStep(step);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(step);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
          Session session = HibernateSessionFactory.getSessionFactory().openSession();
          Transaction tx1 = session.beginTransaction();
//        if(user.getPlans() != null) {
//            for (Plan plan : user.getPlans()) {
//                if(plan.getSteps() != null) {
//                    for(Step step : plan.getSteps()) {
//                        session.update(step);
//                    }
//                    session.update(plan);
//                }
//            }
//        }
        session.update(user);
        tx1.commit();
        session.close();
    }

    public User find(User user) {
        List<User> users = findAll();
        for(User i : users) {
            if(i.getMail().equals(user.getMail()) &&
                    i.getName().equals(user.getName()) &&
                    i.getPassword().equals(user.getPassword())) {
                return i;
            }
        }
        return null;
    }

    public void deleteUser(User user) {
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

    public void deletePlan(Plan plan) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        for(Step step : plan.getSteps()) {
                session.delete(step);
        }
        plan.setSteps(null);
        session.delete(plan);
        tx1.commit();
        session.close();
    }

    public void deleteStep(Step step) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(step);
        tx1.commit();
        session.close();
    }

    public Plan findPlanById(long id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Plan.class, id);
    }


    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    public List<Plan> findAllPlans(long id) {
        List<Plan> plans = (List<Plan>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Plan where owner_id = " + id).list();
        return plans;
    }

    public List<Step> findAllSteps(long id) {
        List<Step> steps = (List<Step>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Step where plan_id = " + id).list();
        return steps;
    }
}
