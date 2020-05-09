package functionality;

import data.sql.UserDAO;

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
        return str.length() < min || str.length() > max;
    }

    private static boolean isDateValidator(String date) {
        return date.matches("^(3[01]|[12][0-9]|0[1-9])(1[0-2]|0[1-9])[0-9]{2}$");
    }

    public static String cprValidator(String cpr) throws NotACPRException {
        UserDAO db = new UserDAO();
        if (cpr.length() != 10)
            throw new NotACPRException("This is not a cpr number it does not have the right amount of digits" );

        if (!isDateValidator(cpr.substring(0, 6)))
            throw new NotACPRException("This cpr does not contain a valid date");

        if (db.exists(cpr)) {
            throw new NotACPRException("This cpr already exists in the database");
        }
        return cpr;
    }

    public static String nameValidator(String name) throws NotANameException {
        if (name.split(" ").length != 2)
            throw new NotANameException("You need to put in a first and a last name!");
        if (hasDigit(name))
            throw new NotANameException("Names can not have numbers. Please put in a real name!");
        if (hasSpecial(name))
            throw new NotANameException("Names can not have special characters. Please put in a real name!");
        if (lengthValidator(2, 25, name))
            throw new NotANameException("The name can only be between 2 and 25 characters");
        else
            return name;
    }

    public static String passwordValidator(String name, String password) throws NotAValidPasswordException {
        String[] names = name.split(" ");
        if (hasStr(password, names[0]))
            throw new NotAValidPasswordException("Its not a good idea to put your own first name in your password");
        if (hasStr(password, names[1]))
            throw new NotAValidPasswordException("Its not a good idea to put your own last name in your password");
        if (lengthValidator(8, 50, password))
            throw new NotAValidPasswordException("Your password has to be between 8 and 50 characters");
        if (!hasUppercase(password))
            throw new NotAValidPasswordException("Your pass has to contain at least one uppercase character");
        if (!hasLowercase(password))
            throw new NotAValidPasswordException("Your pass has to contain at least one lowercase character");
        if (!hasSpecial(password))
            throw new NotAValidPasswordException("Your pass has to contain at least one special character");
        if (!hasDigit(password))
            throw new NotAValidPasswordException("Your pass has to contain at least one digit");
        else
            return password;
    }
}
