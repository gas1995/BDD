package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo(String login, String password) {
        return new AuthInfo(login, password);
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(String code) {
        return new VerificationCode(code);
    }

    @Value
    public static class CardNumber {
        private String cardCode;
    }

    public static CardNumber getCardNumberOur(String cardCode) {
        return new CardNumber(cardCode);
    }

    public static CardNumber getCardNumber(String cardCode) {
        if (cardCode.contains("0001")) {
            cardCode = "5559 0000 0000 0002";
        } else if (cardCode.contains("0002")) {
            cardCode = "5559 0000 0000 0001";
        }
        return new CardNumber(cardCode);
    }

    public static int CardValueSum;

    public static int CardValueSub;
}