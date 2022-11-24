package com.programmers.voucher.repository.customer;

import com.programmers.voucher.JdbcConfig;
import com.programmers.voucher.controller.dto.CustomerDto;
import com.programmers.voucher.model.customer.Customer;
import com.programmers.voucher.model.voucher.FixedAmountVoucher;
import com.programmers.voucher.model.voucher.Voucher;
import com.programmers.voucher.model.voucher.VoucherType;
import com.programmers.voucher.repository.voucher.VoucherRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryTest extends JdbcConfig{
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    @DisplayName("데이터베이스에 고객을 저장한다.")
    void save() {
        //given
        String email = "taehee@gmail.com";
        CustomerDto customerDto = new CustomerDto("taehee", email);

        //when
        customerRepository.save(customerDto);

        //then
        assertThat(customerRepository.findByEmail(email).get().getEmail())
                .isEqualTo(email);
    }

    @Test
    @Order(2)
    @DisplayName("데이터베이스에서 이메일을 통해 고객을 조회한다.")
    void findByEmail() {
        //given
        String email = "taehee@gmail.com";

        //when
        Customer result = customerRepository.findByEmail(email).get();

        //then
        assertThat(result.getEmail())
                .isEqualTo(email);
    }

    @Test
    @Order(3)
    @DisplayName("데이터베이스에서 고객이 가지고 있는 바우처 아이디를 통해 고객을 조회한다.")
    void findByVoucher() {
        //given
        String email = "taehee@gmail.com";
        Customer customer = customerRepository.findByEmail(email).get();
        Voucher voucher = insertSingleVoucherData();
        voucher.setCustomer(customer);
        voucherRepository.assign(voucher);

        //when
        Customer result = customerRepository.findByVoucher(voucher.getVoucherId()).get();

        //then
        assertThat(result.getEmail())
                .isEqualTo(email);
    }

    private Voucher insertSingleVoucherData() {
        Voucher voucher = new FixedAmountVoucher(UUID.randomUUID(), 3000);
        voucher.setVoucherType(VoucherType.toVoucherType("1"));
        return voucherRepository.save(voucher);
    }
}