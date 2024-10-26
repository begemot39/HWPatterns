import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;


public class TestsHWPatternsCase1 {

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    public String generateDate( int days, String pattern ) {

        return LocalDate.now().plusDays( days ).format( DateTimeFormatter.ofPattern( pattern ));
    }

    public int generateRandomDays() {
        Random random = new Random();
        int min = 4;
        int max = 31;
        return random.nextInt(max - min + 1 ) + min;
    }

    Faker faker = new Faker(new Locale("ru-Ru", "Russia"));
    String testCity = faker.address().city();
    String testFullName = faker.name().fullName();
    String testPhone = faker.phoneNumber().phoneNumber().replaceAll("[()\\-]", "");
    int daysForMeet;


    @Test
    public void positiveTestForm() {

        daysForMeet = generateRandomDays();
        $("[data-test-id='city'] [placeholder='Город']").setValue(testCity);
        $("[data-test-id='name'] [name='name']").setValue(testFullName);
        $("[data-test-id='phone'] [name='phone']").setValue(testPhone);
        $("[data-test-id='agreement']").click();
        $(".grid-row button[role='button']").click();
        $(".grid-row button[role='button']").click();
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [placeholder='Дата встречи']").setValue(generateDate(daysForMeet, "dd.MM.yyyy"));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification'] .notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(10));
        $("[data-test-id='success-notification'] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + generateDate(daysForMeet, "dd.MM.yyyy")), Duration.ofSeconds(10));
    }

}


