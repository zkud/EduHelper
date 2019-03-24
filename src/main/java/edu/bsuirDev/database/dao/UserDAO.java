package edu.bsuirDev.database.dao;

import edu.bsuirDev.database.models.User;

import java.util.List;

public interface UserDAO {
    public User findById(long id);

    public User deleteById(long id);

    public void save(User user);

    public void update(User user);

    public void delete(User user);

    public List<User> findAll();
}
