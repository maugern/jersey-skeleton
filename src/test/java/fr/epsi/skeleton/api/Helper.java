package fr.epsi.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private static final Logger logger = LoggerFactory.getLogger(Helper.class);
    
    private static final UserDao dao = BDDFactory.getDbi().open(UserDao.class);

    public void initDb() {
        dao.dropUserTable();
        dao.createUserTable();
    }

    User createUserWithAlias(String alias) {
        User user = new User(alias);
        return createUser(user);
    }

    User createUserWithEmail(String alias, String email) {
        User user = new User(alias);
        user.setEmail(email);
        return createUser(user);
    }

    public User createUserWithPassword(String alias, String passwd, String salt) {
        User user = new User(alias);
        user.setPassword(passwd);
        logger.trace("createUserWithPassword Hash : " + user.getPasswdHash());
        return createUser(user);
    }

    public User createUserWithEmailAndPassword(String alias, String email, String passwd, String salt) {
        User user = new User(alias);
        user.setEmail(email);
        user.setPassword(passwd);
        logger.trace("createUserWithPassword Hash : " + user.getPasswdHash());
        return createUser(user);
    }

    private static User createUser(User user) {
        int id = dao.insert(user);
        return user;
    }

}
