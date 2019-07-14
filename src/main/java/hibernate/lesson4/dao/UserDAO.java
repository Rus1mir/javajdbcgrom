package hibernate.lesson4.dao;

import hibernate.lesson4.model.User;

public class UserDAO extends GeneralDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public User save(User user) throws Exception {
        return saveEntity(user);
    }

    public User update(User user) throws Exception {
        return updateEntity(user);
    }

    public User findById(long id) throws Exception {
        return findEntityById(id);
    }

    public void delete(long id) throws Exception {
        deleteEntity(id);
    }
}
