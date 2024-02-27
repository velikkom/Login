package LoginPage;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import static LoginPage.Service.*;
import static LoginPage.User.*;


public class LoginPageApp {



    /*

Project: Bir siteye üye olma ve giriş yapma sayfası tasarlayınız.

         menü: kullanıcıya işlem seçimi için menü gösterilir.

         üye olma(register): kullanıcıdan ad-soyad, email ve şifre bilgileri alınız.
                             email ve şifre birer listede tutulur.
                             aynı email kabul edilmez.

         giriş(login): email ve şifre girilir.
                       email bulunamazsa kayıtlı değil, üye olun uyarısı verilir.
                       email ile aynı indekste kayıtlı şifre doğrulanırsa siteye giriş yapılır.

         email validation: boşluk içermemeli
                         : @ içermeli
                         : gmail.com,hotmail.com veya yahoo.com ile bitmeli.
                         : mailin kullanıcı adı kısmında(@ den önce) sadece büyük-küçük harf,rakam yada -._ sembolleri olabilir.

         password validation: boşluk içermemeli
                            : en az 6 karakter olmalı
                            : en az bir tane küçük harf içermeli
                            : en az bir tane büyük harf içermeli
                            : en az bir tane rakam içermeli
                            : en az bir tane sembol içermeli
*/

   static DataBase dataBase= new DataBase();
    static HashMap<String, User> users = new HashMap<>();
    /*List<User> list = new ArrayList<>();

    public void setList(List<User> list) {
        this.list = list;
    }*/

    public static void main(String[] args) {

        List<User> list = new ArrayList<>(Arrays.asList(dataBase.user1,dataBase.user2,dataBase.user3,dataBase.user4,dataBase.user5));
        System.out.println(list);

        // Add fake users
        addFakeUsers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // dummy nextLine to clear buffer

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void register(Scanner scanner) {
        System.out.println("Register");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!isValidEmail(email)) {
            System.out.println("Invalid email format!");
        } else if (!isValidPassword(password)) {
            System.out.println("Invalid password format!");
        } else if (users.containsKey(email)) {
            System.out.println("This email is already registered!");
        } else {
            User newUser = new User(name, surname, email, password);
            users.put(email, newUser);
            System.out.println("Registration successful.");
            /*System.out.println(email+""+newUser);*/
            System.out.println(users);
            System.out.println("users size = " + users.size());
        }
    }

    static void login(Scanner scanner) {
        System.out.println("Login");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(email)) {
            User user = users.get(email);
            if (user.authenticate(email, password)) {
                System.out.println("Login successful! Welcome: " + user);
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Email not found! Please register first.");
        }

    }



}





