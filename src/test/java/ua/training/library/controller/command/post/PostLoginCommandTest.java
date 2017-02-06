package ua.training.library.controller.command.post;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;
import ua.training.library.messages.ServiceMessages;
import ua.training.library.messages.LoggingMessages;
import ua.training.library.controller.configuration.Attributes;
import ua.training.library.controller.configuration.Pages;
import ua.training.library.controller.configuration.Parameters;
import ua.training.library.controller.configuration.Paths;
import ua.training.library.dao.exception.DAOException;
import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;
import ua.training.library.model.util.PasswordHelper;
import ua.training.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ua.training.library.data.test.TestUser.*;

public class PostLoginCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    UserService service;

    @Captor
    ArgumentCaptor<User> userInSessionCaptor;

    @InjectMocks
    PostLoginCommand command = new PostLoginCommand();

    @BeforeClass
    public static void setUp() throws Exception {
        PropertyConfigurator.configure(PostLoginCommandTest.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Parameters.LOGIN)).thenReturn(LIBRARIAN.user.getLogin());
        when(request.getParameter(Parameters.PASSWORD)).thenReturn("111");
    }

    @Test
    public void testExecuteCorrectLogin() throws Exception {
        when(service.login(LIBRARIAN.user.getLogin(), PasswordHelper.getSecurePassword("111"))).thenReturn(Optional.of(LIBRARIAN.user));
        doNothing().when(session).setAttribute(eq(Attributes.LOGINED_USER), userInSessionCaptor.capture());
        when(session.getAttribute(Attributes.LOGINED_USER)).thenReturn(LIBRARIAN.user);
        String link = command.execute(request, response);
        assertEquals(userInSessionCaptor.getValue(), LIBRARIAN.user);
        verify(response).sendRedirect(Paths.BASE + "/librarian" + Paths.PROFILE);
        assertEquals(link, Pages.REDIRECT);
    }

    @Test
    public void testExecuteIncorrectLogin() throws Exception {
        User uncheckedUser = new User.Builder()
                .setId(0)
                .setLogin(LIBRARIAN.user.getLogin())
                .setRole(Role.ANONYMOUS)
                .build();
        when(service.login(LIBRARIAN.user.getLogin(), PasswordHelper.getSecurePassword("111"))).thenReturn(Optional.empty());
        doNothing().when(session).setAttribute(eq(Attributes.LOGINED_USER), userInSessionCaptor.capture());
        when(session.getAttribute(Attributes.LOGINED_USER)).thenReturn(LIBRARIAN.user);
        String link = command.execute(request, response);
        assertEquals(userInSessionCaptor.getValue(), uncheckedUser);
        verify(session).setAttribute(Attributes.ERROR_MESSAGE, ServiceMessages.ERROR_IN_LOGIN);
        assertEquals(link, Pages.LOGIN_PAGE);
    }

    @Test
    public void testExecuteCatchRuntimeException() throws Exception {
        when(service.login(LIBRARIAN.user.getLogin(), PasswordHelper.getSecurePassword("111")))
                .thenThrow(new DAOException(LoggingMessages.DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_PASSWORD));
        String link = command.execute(request, response);
        verify(session).setAttribute(Attributes.ERROR_MESSAGE, LoggingMessages.DAO_USER_EXCEPTION_GET_BY_LOGIN_AND_PASSWORD);
        verify(response).sendError(500);
        assertEquals(link, Pages.REDIRECT);
    }
}