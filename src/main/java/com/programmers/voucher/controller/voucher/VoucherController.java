package com.programmers.voucher.controller.voucher;

import com.programmers.voucher.controller.voucher.dto.VoucherAssignRequest;
import com.programmers.voucher.controller.voucher.dto.VoucherCreateRequest;
import com.programmers.voucher.controller.voucher.dto.VoucherUpdateRequest;
import com.programmers.voucher.model.customer.Customer;
import com.programmers.voucher.model.voucher.Voucher;
import com.programmers.voucher.service.CustomerService;
import com.programmers.voucher.service.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class VoucherController {

    private final VoucherService voucherService;
    private final CustomerService customerService;

    public VoucherController(VoucherService voucherService, CustomerService customerService) {
        this.voucherService = voucherService;
        this.customerService = customerService;
    }

    @GetMapping
    public String main() {
        return "main";
    }

    @GetMapping("voucher")
    public String create() {
        return "voucher/voucher_new";
    }

    @PostMapping("voucher")
    public String create(VoucherCreateRequest voucherCreateRequest) {
        voucherService.create(voucherCreateRequest);
        return "redirect:/";
    }

    @GetMapping("vouchers")
    public String findAll(Model model) {
        List<Voucher> vouchers = voucherService.findAll();
        model.addAttribute("vouchers", vouchers);
        return "voucher/voucher_list";
    }

    @GetMapping("voucher/{voucherId}")
    public String findById(@PathVariable UUID voucherId, Model model) {
        Voucher voucher = voucherService.findById(voucherId);
        model.addAttribute("voucher", voucher);
        try {
            Customer customer = customerService.findByVoucher(voucherId);
            model.addAttribute("customer", customer);
        } catch (IllegalArgumentException e) {
            model.addAttribute("customer", null);
        }
        return "voucher/voucher_detail";
    }

    @GetMapping("vouchers/{email}")
    public String findAllByCustomer(@PathVariable String email, Model model) {
        List<Voucher> vouchers = voucherService.findAllByCustomer(email);
        Customer customer = customerService.findByEmail(email);
        model.addAttribute("vouchers", vouchers);
        model.addAttribute("customer", customer);
        return "voucher/voucher_list";
    }

    @PutMapping("voucher")
    public String update(VoucherUpdateRequest voucherUpdateRequest) {
        voucherService.update(voucherUpdateRequest);
        return "redirect:/vouchers";
    }

    @GetMapping("assign/{voucherId}")
    public String assign(@PathVariable UUID voucherId, Model model) {
        Voucher voucher = voucherService.findById(voucherId);
        model.addAttribute("voucher", voucher);
        return "voucher/voucher_assign";
    }

    @PostMapping("assign")
    public String assign(VoucherAssignRequest voucherAssignRequest) {
        voucherService.assign(voucherAssignRequest.voucherId(), voucherAssignRequest.email());
        return "redirect:/vouchers";
    }
}
