import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class TestsHWPatternsCase1 {

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    Generator generator = new Generator();

    @Test
    public void testPlaningForm() {

        int daysForMeet = generator.generateRandomDays();
        var user = Generator.TestUserReg.generatorUsers();
        var randomDateUp = Generator.generateRandomDate( daysForMeet, "dd.MM.yyyy" );

        // Первая регистрация:
        $("[data-test-id='city'] [placeholder='Город']").setValue(generator.getRandomCity());
        $("[data-test-id='name'] [name='name']").setValue(user.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".grid-row button[role='button']").click();
        $("[data-test-id='success-notification'] .notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(10));

        // Новая регистрация:
        $(".grid-row button[role='button']").click();
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [placeholder='Дата встречи']").setValue(randomDateUp);
        $("[data-test-id='replan-notification'] button.button").shouldHave(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(10));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + randomDateUp), Duration.ofSeconds(10));
    }
}






