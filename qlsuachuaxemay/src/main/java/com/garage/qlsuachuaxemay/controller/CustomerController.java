package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.entity.Customer;
import com.garage.qlsuachuaxemay.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private boolean checkLogin(HttpSession session) {
        return session.getAttribute("userLogon") != null;
    }

    @GetMapping
    public String listCustomers(@RequestParam(value = "keyword", required = false) String keyword,
                                Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        List<Customer> list = customerService.searchCustomers(keyword);
        model.addAttribute("listCustomers", list);
        model.addAttribute("keyword", keyword);
        return "customers/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Thêm Khách Hàng Mới");
        return "customers/form";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer,
                              RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm khách hàng thành công!");
            return "redirect:/customers";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("customer", customer);
            return "redirect:/customers/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            Customer customer = customerService.getCustomerById(id);
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Chỉnh Sửa Thông Tin Khách Hàng");
            return "customers/form";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/customers";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable("id") int id,
                                 @ModelAttribute("customer") Customer customer,
                                 RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            customerService.updateCustomer(id, customer);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin khách hàng thành công!");
            return "redirect:/customers";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/customers/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa khách hàng thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/customers";
    }
}
