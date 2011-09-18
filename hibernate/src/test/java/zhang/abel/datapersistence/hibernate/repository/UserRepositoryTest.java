package zhang.abel.datapersistence.hibernate.repository;

import com.sun.corba.se.spi.activation.Repository;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import zhang.abel.datapersistence.hibernate.domain.User;
import zhang.abel.datapersistence.hibernate.utils.HibernateManager;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest{

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepository();
    }

    @Test
    public void should_load_user_by_id() {
        User user = userRepository.load(1);

        assertThat(user, is(notNullValue()));
    }

    @Test
    public void should_persist_user() {
        User user = new User("abel", "password");
        userRepository.persist(user);

        User loadUser = userRepository.load(user.getId());
        assertThat(loadUser.getUsername(), is(user.getUsername()));
        assertThat(loadUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void should_not_update_if_user_has_been_updated_by_others() {
        Session session = HibernateManager.getSession();
        User user = new User("abel", "password");
        userRepository.persist(user);

        user.setEmail("zyzhang@thoughtworks.com");
        userRepository.persist(user);

    }
}
