package hibernate.lesson4.dao;

import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends GeneralDAO<User> {

    public static User loginedUser;

    public UserDAO() {
        super(User.class);
    }

    public static boolean isLogined() {
        return (loginedUser != null);
    }

    public static boolean isAdmin() {
        return (loginedUser != null && loginedUser.getUserType() == UserType.ADMIN);
    }

    public static User getLoginUser() {
        return loginedUser;
    }

    public void login(User user) {

        if (loginedUser != null && user.getId() == loginedUser.getId()) {
            System.out.println("User " + user + " was already login");
            return;
        }

        loginedUser = user;
        System.out.println("User " + user + " was login successfully");
    }

    public void logout() {
        loginedUser = null;
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

    public User getUserByNameAndPass(String name, String password) throws Exception {

        String sql = "SELECT * FROM USERS WHERE USER_NAME = ? AND PASSWORD = ?";
        List<Object> params = new ArrayList<>();
        params.add(name);
        params.add(password);
        List<User> users = getEntitiesByQuery(sql, params);
        return null;
    }
}
