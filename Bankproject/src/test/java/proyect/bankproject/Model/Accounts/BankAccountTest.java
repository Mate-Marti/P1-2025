package proyect.bankproject.Model.Accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Transactions.Transaction;
import proyect.bankproject.Exceptions.InsufficientFundsException;
import java.time.LocalDate;
import java.util.List;


public class BankAccountTest {

    private Client testClient;
    private BankAccount savingsAccount;
    private BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        testClient = new Client("123", "Test", "User", "testuser", LocalDate.now(), "password");

        savingsAccount = new SavingsAccount("SA-001", 1000.0, testClient, 0.02);
        checkingAccount = new CheckingAccount("CH-002", 500.0, testClient, 200.0);
    }

    @Test
    void testDepositoEnAhorros() {
        savingsAccount.deposit(500.0);

        assertEquals(1500.0, savingsAccount.getBalance());
    }

    @Test
    void testRetiroExitosoAhorros() throws InsufficientFundsException {
        savingsAccount.withdraw(300.0);

        assertEquals(700.0, savingsAccount.getBalance());
    }

    @Test
    void testRetiroSinFondosAhorros() {

        assertThrows(InsufficientFundsException.class, () -> {
            savingsAccount.withdraw(2000.0);
        });

        assertEquals(1000.0, savingsAccount.getBalance());
    }

    @Test
    void testRetiroConSobregiroExitoso() throws InsufficientFundsException {

        checkingAccount.withdraw(600.0);

        assertEquals(-100.0, checkingAccount.getBalance());
    }

    @Test
    void testRetiroExcedeSobregiro() {
        assertThrows(InsufficientFundsException.class, () -> {
            checkingAccount.withdraw(800.0);
        });

        assertEquals(500.0, checkingAccount.getBalance());
    }

    @Test
    void testTransferenciaLogsCorrectos() throws InsufficientFundsException {
        savingsAccount.transfer(checkingAccount, 300.0);

        assertEquals(700.0, savingsAccount.getBalance());
        assertEquals(800.0, checkingAccount.getBalance());

        List<Transaction> txAhorros = savingsAccount.getTransactions();
        List<Transaction> txCorriente = checkingAccount.getTransactions();

        Transaction txEnviada = txAhorros.get(1);
        Transaction txRecibida = txCorriente.get(1);

        assertEquals("TRANSFERENCIA_ENVIADA", txEnviada.getType());
        assertEquals("TRANSFERENCIA_RECIBIDA", txRecibida.getType());
    }
}
