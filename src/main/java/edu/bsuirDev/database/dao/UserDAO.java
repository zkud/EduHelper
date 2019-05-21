package edu.bsuirDev.database.dao;

import edu.bsuirDev.database.models.Plan;
import edu.bsuirDev.database.models.Step;
import edu.bsuirDev.database.models.User;

import java.util.List;

public interface UserDAO {
    public User findById(long id);

    public void save(User user);

    public void update(User user);

    public void deleteUser(User user);

    public void deletePlan(Plan plan);

    public void deleteStep(Step step);

    public List<User> findAll();
}
