package proyect.bankproject.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proyect.bankproject.Exceptions.DuplicateUserException;
import proyect.bankproject.Exceptions.UserNotFoundException;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.User;

import java.time.LocalDate;

public class RegistrationServiceTest {

    private RegistrationService registrationService;
    private BankService bankService;

    private final String NUEVA_CEDULA = "1099999999";
    private final String NUEVO_USUARIO = "clientenuevo";

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationService();
        bankService = BankService.getInstance();

        bankService.initializeData();
    }

    @AfterEach
    void tearDown() {

        try {
            User testUser = bankService.findUserByUsername(NUEVO_USUARIO);

            bankService.deleteUser(testUser);
            if (testUser instanceof Client) {
                bankService.deleteAccountsForClient((Client) testUser);
            }

            bankService.saveAllData();
            System.out.println("LIMPIEZA: Usuario " + NUEVO_USUARIO + " eliminado.");

        } catch (UserNotFoundException e) {

            System.out.println("LIMPIEZA: Usuario " + NUEVO_USUARIO + " no fue creado. No se necesita limpieza.");
        }
    }

    @Test
    void testRegistroClienteDuplicadoFalla() {

        Exception exception = assertThrows(DuplicateUserException.class, () -> {
            registrationService.registerNewClient(
                    "111222",
                    "Otro", "Usuario",
                    LocalDate.of(2000, 1, 1),
                    "usuariounico",
                    "password123"
            );
        });

        assertTrue(exception.getMessage().contains("La cédula 111222 ya está registrada"));
    }

    @Test
    void testRegistroClienteExitoso() throws DuplicateUserException, UserNotFoundException {

        assertThrows(UserNotFoundException.class, () -> {
            bankService.findUserByUsername(NUEVO_USUARIO);
        });

        registrationService.registerNewClient(
                NUEVA_CEDULA,
                "Pedro", "Perez",
                LocalDate.of(1995, 5, 5),
                NUEVO_USUARIO,
                "superpass"
        );

        User usuarioCreado = bankService.findUserByUsername(NUEVO_USUARIO);

        assertNotNull(usuarioCreado);
        assertEquals("Pedro", usuarioCreado.getName());

    }
}