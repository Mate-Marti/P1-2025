package proyect.bankproject.Service;

import proyect.bankproject.Exceptions.InvalidPasswordException;
import proyect.bankproject.Exceptions.UserNotFoundException;
import proyect.bankproject.Model.Users.User;

public class LoginService {

    private final BankService bankService;

    public LoginService() {
        this.bankService = BankService.getInstance();
    }

    public User validateUser(String username, String plainTextPassword)
            throws UserNotFoundException, InvalidPasswordException {

        User user;

        user = bankService.findUserByUsername(username);

        if (user.checkPassword(plainTextPassword)) {
            return user;
        }

        throw new InvalidPasswordException("Contraseña incorrecta.");
    }
}