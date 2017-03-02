package fr.maugern.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.maugern.skeleton.api.User;

import java.util.List;

public interface UserDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), alias VARCHAR(30) UNIQUE, email VARCHAR(256) UNIQUE, passwdHash VARCHAR(512))")
	void createUserTable();

	@SqlUpdate("INSERT INTO users (name,alias,email, passwdHash) VALUES (:name, :alias, :email, :passwdHash)")
	@GetGeneratedKeys
	int insert(@BindBean() User user);

	@SqlUpdate("UPDATE users SET name = :name, alias = :alias, email = :email, passwdHash = :passwdHash WHERE id = :id")
	int update(@BindBean() User user);

	@SqlQuery("SELECT * FROM users WHERE id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);

	@SqlQuery("SELECT * FROM users WHERE alias = :alias")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findByAlias(@Bind("alias") String alias);

	@SqlQuery("SELECT * FROM users WHERE email = :email")
	@RegisterMapperFactory(BeanMapperFactory.class)
	User findByEmail(@Bind("email") String email);

	@SqlQuery("SELECT * FROM users ORDER BY id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<User> all();

	@SqlUpdate("DELETE FROM users WHERE id = :id")
	void delete(@BindBean() User user);
	
	@SqlUpdate("DROP TABLE IF EXISTS users")
	void dropUserTable();

	void close();
}
