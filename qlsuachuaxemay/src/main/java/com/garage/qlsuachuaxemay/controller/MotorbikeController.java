package com.garage.qlsuachuaxemay.controller;

import com.garage.qlsuachuaxemay.entity.Customer;
import com.garage.qlsuachuaxemay.entity.Motorbike;
import com.garage.qlsuachuaxemay.service.CustomerService;
import com.garage.qlsuachuaxemay.service.MotorbikeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/motorbikes")
public class MotorbikeController {

    @Autowired
    private MotorbikeService motorbikeService;

    @Autowired
    private CustomerService customerService;

    private boolean checkLogin(HttpSession session) {
        return session.getAttribute("userLogon") != null;
    }

    @GetMapping
    public String listMotorbikes(@RequestParam(value = "keyword", required = false) String keyword,
                                 Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        List<Motorbike> list = motorbikeService.searchMotorbikes(keyword);
        model.addAttribute("listMotorbikes", list);
        model.addAttribute("keyword", keyword);
        return "motorbikes/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("motorbike", new Motorbike());
        model.addAttribute("listCustomers", customerService.getAllActiveCustomers());
        model.addAttribute("pageTitle", "Thêm Xe Máy Mới");
        return "motorbikes/form";
    }

    @PostMapping("/add")
    public String addMotorbike(@ModelAttribute("motorbike") Motorbike motorbike,
                               @RequestParam("customerId") int customerId,
                               RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            Customer customer = customerService.getCustomerById(customerId);
            motorbike.setCustomer(customer);
            motorbikeService.saveMotorbike(motorbike);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm xe máy thành công!");
            return "redirect:/motorbikes";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("motorbike", motorbike);
            return "redirect:/motorbikes/add";
        }
    }

    @GetMapping("/edit/{licensePlate}")
    public String showEditForm(@PathVariable("licensePlate") String licensePlate,
                               Model model, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            Motorbike motorbike = motorbikeService.getMotorbikeByLicensePlate(licensePlate);
            model.addAttribute("motorbike", motorbike);
            model.addAttribute("listCustomers", customerService.getAllActiveCustomers());
            model.addAttribute("pageTitle", "Chỉnh Sửa Thông Tin Xe Máy");
            return "motorbikes/form";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/motorbikes";
        }
    }

    @PostMapping("/edit/{licensePlate}")
    public String updateMotorbike(@PathVariable("licensePlate") String licensePlate,
                                  @ModelAttribute("motorbike") Motorbike motorbike,
                                  @RequestParam("customerId") int customerId,
                                  RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            Customer customer = customerService.getCustomerById(customerId);
            motorbike.setCustomer(customer);
            motorbikeService.updateMotorbike(licensePlate, motorbike);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin xe máy thành công!");
            return "redirect:/motorbikes";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/motorbikes/edit/" + licensePlate;
        }
    }

    @GetMapping("/delete/{licensePlate}")
    public String deleteMotorbike(@PathVariable("licensePlate") String licensePlate,
                                  RedirectAttributes redirectAttributes, HttpSession session) {
        if (!checkLogin(session)) {
            return "redirect:/login";
        }
        try {
            motorbikeService.deleteMotorbike(licensePlate);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa xe máy thành công!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/motorbikes";
    }
}
