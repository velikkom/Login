package LoginPage;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static LoginPage.LoginPageApp.users;

public class User {

    private String name;
    private String surname;
    private String email;
    private String password;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname + " Email: " + email;
    }
    static void addFakeUsers() {
        String[] names = {"John", "Emma", "Michael", "Sophia", "William"};
        String[] surnames = {"Doe", "Smith", "Johnson", "Brown", "Taylor"};
        String[] emails = {"john.doe@example.com", "emma.smith@example.com", "michael.johnson@example.com", "sophia.brown@example.com", "william.taylor@example.com"};
        String[] passwords = {"password123", "abc123", "qwerty123", "passpass", "123456"};

        for (int i = 0; i < names.length; i++) {
            User user = new User(names[i], surnames[i], emails[i], passwords[i]);
            users.put(emails[i], user);
        }
    }

    static boolean isValidEmail(String email) {
        // Boşluk içermemeli
        if (email.contains(" ")) {
            return false;
        }

        // @ işaretini içermeli
        if (!email.contains("@")) {
            return false;
        }

        // gmail.com, hotmail.com veya yahoo.com ile bitmeli
        if (!email.endsWith("gmail.com") && !email.endsWith("hotmail.com") && !email.endsWith("yahoo.com")) {
            return false;
        }

        // @ işaretinden önceki kısımda sadece büyük-küçük harf, rakam veya -._ sembolleri olabilir
        String usernamePart = email.substring(0, email.indexOf('@'));
        String usernameRegex = "^[a-zA-Z0-9_.-]+$";
        if (!usernamePart.matches(usernameRegex)) {
            return false;
        }

        return true;
    }

    static boolean isValidPassword(String password) {
        // Boşluk içermemeli
        if (password.contains(" ")) {
            return false;
        }

        // En az 6 karakter olmalı
        if (password.length() < 6) {
            return false;
        }

        // En az bir tane küçük harf içermeli
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // En az bir tane büyük harf içermeli
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // En az bir tane rakam içermeli
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // En az bir tane sembol içermeli
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            return false;
        }

        return true;
    }

}
