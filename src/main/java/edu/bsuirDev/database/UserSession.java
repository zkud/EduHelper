package edu.bsuirDev.database;


import edu.bsuirDev.database.dao.UserDAO;
import edu.bsuirDev.database.dao.UserDAOImpl;
import edu.bsuirDev.database.models.*;

import java.util.List;

public class UserSession {

    User user;
    UserDAOImpl userDAO;

    public static void main(String[] argc) {
        Step step = new Step();
        UserSession userSession = new UserSession(1);
        userSession.addStep(14, step);
    }

    public static UserSession createNewUser(String name, String mail, String password) {
        User user = new User(name, mail, password);
        UserSession userSession = new UserSession(user);
        if(userSession.user == null) {
            UserDAO userDAO = new UserDAOImpl();
            userSession.user = user;
            userDAO.saveUser(user);
        }
        else
        {
            return null;
        }
        return userSession;
    }

    public UserSession(String name, String mail, String password) {
        this.user = new User(name, mail, password);
        this.userDAO = new UserDAOImpl();
        this.user = this.userDAO.find(user);
    }

    public UserSession(User user) {
        this.user = user;
        this.userDAO = new UserDAOImpl();
        this.user = this.userDAO.find(user);
    }

    public UserSession(long id) {
        this.userDAO = new UserDAOImpl();
        this.user = userDAO.findById(id);
    }

    public User getUser() {
        return user;
    }

    public Plan getPlan(long id) {
        for(Plan plan : user.getPlans()) {
            if(id == plan.getId()) {
                return plan;
            }
        }
        return null;
    }

    public void saveUser() {
        userDAO.saveUser(user);
    }

    public void deleteUser() {
        userDAO.deleteUser(user);
    }

    public List<Plan> getPlans() {
        return user.getPlans();
    }

    public void setPlans(List<Plan> plans) {
        user.setPlans(plans);
    }

    public List<Step> getSteps(long id) {
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                return plan.getSteps();
            }
        }
        return null;
    }

    public void setSteps(long id, List<Step> steps) {
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                plan.setSteps(steps);
                break;
            }
        }
    }

    public void addPlan(Plan plan) {
        if(user != null) {
            userDAO.savePlan(user, plan);
        }
    }

    public void addStep(long id, Step step) {
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                userDAO.saveStep(plan, step);
                break;
            }
        }
    }

    public void removePlan(long id) {
        if(user.getPlans() == null)
        {
            return;
        }
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                userDAO.deletePlan(plan);
                break;
            }
        }
    }

    public void removeStep(long planid, long stepid) {
        if(user.getPlans() == null) {
            return;
        }
        for(Plan plan : user.getPlans()) {
            if(plan.getSteps() == null) {
                continue;
            }
            if(plan.getId() == planid) {
                for (Step step : plan.getSteps()) {
                    if (step.getId() == stepid) {
                        //plan.removeStep(step);
                        userDAO.deleteStep(step);
                        return;
                    }
                }
            }
        }
    }
}
