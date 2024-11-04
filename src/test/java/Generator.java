import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Generator {

    public Generator() {

    }

    public static int generateRandomDays() {
        Random random = new Random();
        int min = 4;
        int max = 31;
        return random.nextInt(max - min + 1 ) + min;
    }

    public static String generateRandomDate( int days, String pattern ) {

        return LocalDate.now().plusDays(days).format( DateTimeFormatter.ofPattern( pattern ));
    }

    Faker faker = new Faker(new Locale("ru-Ru", "Russia"));

    public  String generateTestCity() {
        return faker.address().city();
    }

    public  String generatetestUserName() {
        return faker.name().fullName();
    }

    public String generateTestPhone() {
        return faker.phoneNumber().phoneNumber().replaceAll("[()\\-]", "");
    }

//    @Value
//    public static class UserInfo {
//        String city;
//        String name;
//        String phone;
//    }
}
