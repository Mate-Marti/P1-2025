package proyect.bankproject.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proyect.bankproject.Exceptions.InvalidPasswordException;
import proyect.bankproject.Exceptions.UserNotFoundException;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.Administrator;
import proyect.bankproject.Model.Users.User;

public class LoginServiceTest {

    private static LoginService loginService;

    @BeforeAll
    static void setUp() {
        BankService bankService = BankService.getInstance();
        bankService.initializeData();
        loginService = new LoginService();
    }

    @Test
    void testLoginClienteExitoso() throws UserNotFoundException, InvalidPasswordException {

        String username = "client";
        String password = "contrasena";

        User user = loginService.validateUser(username, password);

        assertNotNull(user);
        assertTrue(user instanceof Client);
        assertEquals("client", user.getUsername());
    }

    @Test
    void testLoginAdminExitoso() throws UserNotFoundException, InvalidPasswordException {

        String username = "administrator";
        String password = "Cr7juanjo8";

        User user = loginService.validateUser(username, password);

        assertNotNull(user);
        assertTrue(user instanceof Administrator);

        assertEquals("administrator", user.getUsername());
    }

    @Test
    void testLoginContrasenaIncorrectaFalla() {
        String username = "client";
        String passwordIncorrecta = "estacontrasenaesincorrecta";

        Exception exception = assertThrows(InvalidPasswordException.class, () -> {
            loginService.validateUser(username, passwordIncorrecta);
        });

        assertEquals("Contraseña incorrecta.", exception.getMessage());
    }

    @Test
    void testLoginUsuarioNoExisteFalla() {
        String usernameNoExistente = "usuariofantasma";
        String password = "123";

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            loginService.validateUser(usernameNoExistente, password);
        });

        assertTrue(exception.getMessage().contains("no fue encontrado"));
    }
}