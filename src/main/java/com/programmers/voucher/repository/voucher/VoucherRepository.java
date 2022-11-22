package com.programmers.voucher.repository.voucher;

import com.programmers.voucher.model.voucher.Voucher;

import java.util.List;
import java.util.UUID;

public interface VoucherRepository {
    Voucher save(Voucher voucher);

    List<Voucher> findAll();

    List<Voucher> findAllByEmail(String email);

    Voucher findById(UUID voucherId);

    void update(Voucher voucher);

    void deleteAll();

    void deleteByEmail(String email);

    void assign(Voucher voucher);
}
