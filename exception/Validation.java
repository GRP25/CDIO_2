package funcionalitylayer;

import datalayer.UserDAO;
import dto.UserDTO;

import java.util.ArrayList;

import static utilities.CosoleColors.RED_UNDERLINED;
import static utilities.CosoleColors.RESET;

public class Validation {
    private static boolean hasStr(String str1, String str2) {
        return str1.matches(".*(?i)" + str2 + ".*");
    }

    private static boolean hasDigit(String str) {
        return str.matches(".*[0-9].*");
    }

    private static boolean hasUppercase(String str) {
        return str.matches(".*[A-Z].*");
    }

    private static boolean hasLowercase(String str) {
        return str.matches(".*[a-z].*");
    }

    private static boolean hasSpecial(String str) {
        return str.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*");
    }

    private static boolean lengthValidator(int min, int max, String str) {
        return str.length() <= min && str.length() >= max;
    }

    private static boolean isDateValidator(String date) {
        return date.matches("^(3[01]|[12][0-9]|0[1-9])(1[0-2]|0[1-9])[0-9]{2}$");
    }

    public static String cprValidator(String cpr, UserDAO db) throws NotACPRException {
        if (cpr.length() != 10)
            throw new NotACPRException(RED_UNDERLINED + "This is not a cpr number it does not have the right amount of digits" + RESET);
        if (!isDateValidator(cpr.substring(0, 6)))
            throw new NotACPRException(RED_UNDERLINED + "This cpr does not contain a valid date" + RESET);

        long               num   = Long.parseLong(cpr);
        ArrayList<UserDTO> users = db.selectAll();
        for (UserDTO user : users) {
            if (user.getCpr() == num) {
                throw new NotACPRException(RED_UNDERLINED + "This cpr already exists in the database" + RESET);
            }
        }
        return cpr;
    }

    public static String nameValidator(String name) throws NotANameException {
        if (hasDigit(name))
            throw new NotANameException(RED_UNDERLINED + "Names can not have numbers. Please put in a real name!" + RESET);
        if (hasSpecial(name))
            throw new NotANameException(RED_UNDERLINED + "Names can not have special characters. Please put in a real name!" + RESET);
        if (lengthValidator(1, 25, name))
            throw new NotANameException(RED_UNDERLINED + "The name can only be between 2 and 25 characters" + RESET);
        else
            return name;
    }

    public static String passwordValidator(String name, String password) throws NotAValidPasswordException {
        String[] names = name.split(" ");
        if (hasStr(password, names[0]))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Its not a good idea to put your own first name in your password" + RESET);
        if (hasStr(password, names[1]))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Its not a good idea to put your own last name in your password" + RESET);
        if (lengthValidator(8, 50, password))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Your password has to be between 8 and 50 characters" + RESET);
        if (!hasUppercase(password))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Your pass has to contain at least one uppercase character");
        if (!hasLowercase(password))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Your pass has to contain at least one lowercase character");
        if (!hasSpecial(password))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Your pass has to contain at least one special character");
        if (!hasDigit(password))
            throw new NotAValidPasswordException(RED_UNDERLINED + "Your pass has to contain at least one digit");
        else
            return password;
    }
}
