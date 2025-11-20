package proyect.bankproject.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proyect.bankproject.Exceptions.InsufficientFundsException;
import proyect.bankproject.Exceptions.InvalidAmountException;
import proyect.bankproject.Model.Accounts.BankAccount;
import proyect.bankproject.Model.Accounts.SavingsAccount;
import proyect.bankproject.Model.Users.Client;

import java.time.LocalDate;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private BankAccount testAccount;

    @BeforeEach
    void setUp() {

        transactionService = new TransactionService();

        Client testClient = new Client("123", "Test", "User", "testuser", LocalDate.now(), "password");
        testAccount = new SavingsAccount("SA-TEST", 1000.0, testClient, 0.02);

        BankService.getInstance().initializeData();
    }


    @Test
    void testPerformDepositExitoso() throws InvalidAmountException {
        transactionService.performDeposit(testAccount, "500.0");
        assertEquals(1500.0, testAccount.getBalance());
    }

    @Test
    void testPerformDepositMontoInvalido_Letras() {

        Exception exception = assertThrows(InvalidAmountException.class, () -> {
            transactionService.performDeposit(testAccount, "abc");
        });

        assertTrue(exception.getMessage().contains("El monto debe ser un número válido"));
        assertEquals(1000.0, testAccount.getBalance());
    }

    @Test
    void testPerformDepositMontoInvalido_Negativo() {

        Exception exception = assertThrows(InvalidAmountException.class, () -> {
            transactionService.performDeposit(testAccount, "-100");
        });

        assertTrue(exception.getMessage().contains("El monto debe ser positivo"));
        assertEquals(1000.0, testAccount.getBalance());
    }

    @Test
    void testPerformWithdrawalExitoso() throws InvalidAmountException, InsufficientFundsException {
        transactionService.performWithdrawal(testAccount, "300.0");
        assertEquals(700.0, testAccount.getBalance());
    }

    @Test
    void testPerformWithdrawalMontoInvalido_Letras() {

        Exception exception = assertThrows(InvalidAmountException.class, () -> {
            transactionService.performWithdrawal(testAccount, "xyz");
        });

        assertTrue(exception.getMessage().contains("El monto debe ser un número válido"));
        assertEquals(1000.0, testAccount.getBalance());
    }

    @Test
    void testPerformWithdrawalFondosInsuficientes() {

        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            transactionService.performWithdrawal(testAccount, "2000.0");
        });

        assertTrue(exception.getMessage().contains("Fondos insuficientes"));
        assertEquals(1000.0, testAccount.getBalance());
    }
}
