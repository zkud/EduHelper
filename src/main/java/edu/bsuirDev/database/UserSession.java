package edu.bsuirDev.database;


import edu.bsuirDev.database.dao.UserDAOImpl;
import edu.bsuirDev.database.models.*;

import java.util.List;

public class UserSession {

    User user;
    UserDAOImpl userDAO;
    boolean isExist;

    public static void main(String[] argc) {
        User user = new User("NotDimon", "MyMail", "helloworld");
        UserSession userSession = new UserSession(1);

        List<Step> steps = userSession.getSteps(1);
    }

    public UserSession(User user) {
        this.user = user;
        this.userDAO = new UserDAOImpl();
        User temp = this.userDAO.find(user);
        if(temp != null) {
            this.isExist = true;
            this.user = temp;
            this.user.setPlans(userDAO.findAllPlans(user.getId()));
            for(Plan plan : user.getPlans()) {
                plan.setSteps(userDAO.findAllSteps(plan.getId()));
            }
        } else {
            this.isExist = false;
        }
    }

    public UserSession(long id) {
        this.userDAO = new UserDAOImpl();
        this.user = userDAO.findById(id);
        this.user.setPlans(userDAO.findAllPlans(id));
        for(Plan plan : user.getPlans()) {
            plan.setSteps(userDAO.findAllSteps(plan.getId()));
        }
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
        if(isExist) {
            userDAO.update(user);
        } else {
            userDAO.save(user);
            isExist = true;
        }
    }

    public void deleteUser() {
        userDAO.delete(user);
        isExist = false;
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
        user.addPlan(plan);
    }

    public void addStep(long id, Step step) {
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                plan.addStep(step);
                break;
            }
        }
    }

    public void removePlan(long id) {
        for(Plan plan : user.getPlans()) {
            if(plan.getId() == id) {
                user.removePlan(plan);
                break;
            }
        }
    }

    public void removeStep(long id) {
        for(Plan plan : user.getPlans()) {
            for(Step step : plan.getSteps()) {
                if(step.getId() == id)
                {
                    plan.removeStep(step);
                    return;
                }
            }
        }
    }
}
