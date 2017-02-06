package ua.training.library.service.basic;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.library.dao.UserDAO;
import ua.training.library.dao.connection.AbstractConnection;
import ua.training.library.dao.connection.ConnectionFactory;
import ua.training.library.dao.factory.DAOFactory;
import ua.training.library.data.test.TestUser;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;
import ua.training.library.service.UserService;
import ua.training.library.service.exception.ServiceException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ua.training.library.data.test.TestUser.*;

public class BasicUserServiceTest {

    @Mock
    private DAOFactory daoFactory;
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private AbstractConnection connection;
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService service = BasicUserService.getInstance();

    @BeforeClass
    public static void setUp() throws Exception {
        PropertyConfigurator.configure(BasicUserServiceTest.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(connectionFactory.getMySQLConnection()).thenReturn(connection);
        when(daoFactory.getUserDAO(connection)).thenReturn(userDAO);
        when(userDAO.getByLoginAndPassword(CLIENT.user.getLogin(), CLIENT.user.getPassword())).thenReturn(Optional.of(CLIENT.user));
        when(userDAO.getByLoginAndEmail(CLIENT.user.getLogin(), CLIENT.user.getEmail())).thenReturn(Optional.of(CLIENT.user));
        when(userDAO.getByLoginAndPassword("dou", "0000")).thenReturn(Optional.empty());
        when(userDAO.getByLoginAndEmail("dou", "jd@fantom.com")).thenReturn(Optional.empty());
        when(userDAO.getById(LIBRARIAN.user.getId())).thenReturn(Optional.of(LIBRARIAN.user));
        when(userDAO.getById(3)).thenReturn(Optional.empty());
        when(userDAO.getAllByRole(Role.CLIENT)).thenReturn(userList());
    }

    private List<User> userList() {
        return Arrays.asList(TestUser.values())
                .stream()
                .map(e -> e.user)
                .filter(u -> u.getRole() == Role.CLIENT)
                .collect(Collectors.toList());
    }

    @Test
    public void testGetInstance() throws Exception {
        UserService service = BasicUserService.getInstance();
        assertNotNull(service);
    }

    @Test
    public void testLoginCorrectUser() throws Exception {
        Optional<User> user = service.login(CLIENT.user.getLogin(), CLIENT.user.getPassword());
        verify(userDAO, times(1)).getByLoginAndPassword(CLIENT.user.getLogin(), CLIENT.user.getPassword());
        assertTrue(user.isPresent());
    }

    @Test
    public void testLoginIncorrectUser() throws Exception {
        Optional<User> user = service.login("dou", "0000");
        verify(userDAO, times(1)).getByLoginAndPassword("dou", "0000");
        assertFalse(user.isPresent());
    }

    @Test
    public void testGetByIdCorrectUser() throws Exception {
        Optional<User> user = service.getById(LIBRARIAN.user.getId());
        verify(userDAO, times(1)).getById(LIBRARIAN.user.getId());
        assertTrue(user.isPresent());
    }

    @Test
    public void testGetByIdIncorrectUser() throws Exception {
        Optional<User> user = service.getById(3);
        verify(userDAO, times(1)).getById(3);
        assertFalse(user.isPresent());
    }

    @Test
    public void testGetAllClientsByLibrarian() throws Exception {
        List<User> clients = service.getAllClients(LIBRARIAN.user.getRole());
        verify(userDAO, times(1)).getAllByRole(Role.CLIENT);
        assertThat(clients, is(userList()));
    }

    @Test(expected = ServiceException.class)
    public void testGetAllClientsByClient() throws Exception {
        service.getAllClients(CLIENT.user.getRole());
    }

    @Test
    public void testCreateNewUser() throws Exception {
        User user = new User.Builder()
                .setLogin("dou")
                .setEmail("jd@fantom.com")
                .build();
        service.create(user);
        verify(connection, times(1)).beginTransaction();
        verify(userDAO, times(1)).create(user);
        verify(connection, times(1)).commit();
    }

    @Test(expected = ServiceException.class)
    public void testCreatePresentUser() throws Exception {
        service.create(CLIENT.user);
        verify(connection, times(1)).beginTransaction();
        verify(connection, times(1)).rollback();
    }
}