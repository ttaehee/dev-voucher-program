package com.programmers.voucher.model.voucher;

import java.util.UUID;

public class FixedAmountVoucher extends Voucher {

    private static final String WRONG_DISCOUNT = "할인금액 0원 이하는 불가합니다.";
    private static final String MORE_DISCOUNT = "할인금액이 정가보다 큽니다";

    public FixedAmountVoucher(UUID voucherId, long discountValue) {
        super(voucherId, discountValue);
    }

    @Override
    protected void validateDiscountRange(long discountValue) {
        if (discountValue <= 0) {
            throw new IllegalArgumentException(WRONG_DISCOUNT);
        }
    }

    @Override
    public long discount(long fullAmount) {
        if (discountValue > fullAmount) {
            throw new IllegalArgumentException(MORE_DISCOUNT);
        }
        return fullAmount - discountValue;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%d$", VoucherType.FIXED.getName(), voucherId, discountValue);
    }
}