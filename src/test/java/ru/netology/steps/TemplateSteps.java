package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.*;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static RefillPage refillPage;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin(DataHelper.getAuthInfo(login, password));
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor(verificationCode));
    }
    @И("происходит успешная авторизация, пользователь попадает на страницу 'Личный кабинет'")
    public void shouldBePersonalArea() {
        dashboardPage.verifyIsDashboardPage();
    }
    @И("баланс первой карты пользователя равен {string} рублей")
    public void cardAccountCheck(String sum) {
        refillPage = dashboardPage.getCardBalanceButton();
        refillPage.getStartRefillPage(DataHelper.getCardNumber(refillPage.lookingForCardNumber()), DataHelper.CardValueSum / 2 + Math.abs(DataHelper.CardValueSub) / 2 - Integer.parseInt(sum.replaceAll("\\s", "")));
    }

    @И("пользователь попадает на страницу 'Пополнение карты'")
    public void transferAccountCheck() {
        refillPage = dashboardPage.getCardBalanceButton();
    }

    @Тогда("пользователь вводит сумму перевода {string} рублей и номер карты {string}")
    public void enteringDetails(String amount, String cardCode) {
        dashboardPage = refillPage.getStartRefillPage(DataHelper.getCardNumberOur(cardCode), Integer.parseInt(amount.replaceAll("\\s", "")));
    }

    @И("происходит успешное пополнение, баланс 1 карты из списка на странице 'Личный кабинет' равен {string} рублей")
    public void transferCheck(String sum) {
        int expected = Integer.parseInt(sum.replaceAll("\\s", ""));
        int actual = dashboardPage.getCardBalanceFirst();
        assertEquals(expected, actual);
    }
}
