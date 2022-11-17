package com.programmers.voucher.io;

public enum Message {
    INTRO_MESSAGE("=== Voucher Program ===\n" +
            "    Type **1** to exit the program.\n" +
            "    Type **2* to create a new voucher.\n" +
            "    Type **3** to list all vouchers.\n" +
            "    Type **4** to delete all vouchers.\n" +
            "    Type **5** to list all black customer."),
    WRONG_ORDER_MESSAGE("잘못된 입력입니다."),
    REQUEST_VOUCHER_TYPE_MESSAGE("FixedAmountVoucher는 \"1\", PercentDiscountVoucher는 \"2\"를 입력해주세요."),
    REQUEST_DISCOUNT_VALUE_MESSAGE("원하는 할인값을 입력해주세요."),
    EMPTY_VOUCHER_MESSAGE("조회되는 voucher가 없습니다."),
    EMPTY_BLACK_MESSAGE("조회되는 blacklist가 없습니다."),
    DELETE_ALL_VOUCHERS("모든 바우처가 삭제되었습니다.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
