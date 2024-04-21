package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RefillPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement cardNumberInput = $("[data-test-id=from] input");
    private SelenideElement confirmButton = $("[data-test-id=action-transfer]");
    private SelenideElement cardToReceive = $("[data-test-id=to] input");

    public RefillPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage getStartRefillPage(DataHelper.CardNumber cardCode, int sum) {
        transferAmount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        transferAmount.setValue(String.valueOf(sum));
        cardNumberInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        cardNumberInput.setValue(cardCode.getCardCode());
        confirmButton.click();
        return new DashboardPage();
    }
    public String lookingForCardNumber(){
        String text = cardToReceive.getValue();
        return text;
    }
}
