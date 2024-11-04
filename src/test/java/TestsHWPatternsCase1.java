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

        $("[data-test-id='city'] [placeholder='Город']").setValue(generator.generateTestCity());
        $("[data-test-id='name'] [name='name']").setValue(generator.generatetestUserName());
        $("[data-test-id='phone'] [name='phone']").setValue(generator.generateTestPhone());
        $("[data-test-id='agreement']").click();
        $(".grid-row button[role='button']").click();
        $("[data-test-id='success-notification'] .notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(10));
    }

    @Test
    public void positiveTestFormReregistration() {
        int daysForMeet = generator.generateRandomDays();

        $("[data-test-id='city'] [placeholder='Город']").setValue(generator.generateTestCity());
        $("[data-test-id='name'] [name='name']").setValue(generator.generatetestUserName());
        $("[data-test-id='phone'] [name='phone']").setValue(generator.generateTestPhone());
        $("[data-test-id='agreement']").click();
        $(".grid-row button[role='button']").click();
        $(".grid-row button[role='button']").click();
        $("[data-test-id='date'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [placeholder='Дата встречи']").setValue(generator.generateRandomDate( daysForMeet,"dd.MM.yyyy"));
        $("[data-test-id='replan-notification'] button.button").shouldHave(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(10));
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + generator.generateRandomDate( daysForMeet,"dd.MM.yyyy")), Duration.ofSeconds(10));
    }

}






