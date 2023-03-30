package com.passwordmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import com.passwordmanager.domain.User;
import com.passwordmanager.repository.PwdManagerRepository;

@Controller
public class UserController {
    @Autowired
    private PwdManagerRepository repository;

    @GetMapping("/users")
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<User> users = new ArrayList<User>();

            if (keyword == null) {
                repository.findAll().forEach(users::add);
            } else {
                repository.findByNameContainingIgnoreCase(keyword).forEach(users::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "users";
    }

    @GetMapping("/users/new")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Create new User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, @Param("password") String password,
            @Param("repeatPassword") String repeatPassword,
            RedirectAttributes redirectAttributes) {
        try {
            if (!password.equals(repeatPassword)) {
                return "redirect:/users/new?passDoNotMatch";
            } else {
                repository.save(user);
                redirectAttributes.addFlashAttribute("message", "The User has been saved successfully!");
            }
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = repository.getReferenceById(id);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + user.getName() + ")");

            return "user_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/users";
        }
    }

    @GetMapping("/users/copy/{id}")
    public String copyToClipBoard(@PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes) {
        StringSelection stringSelection = new StringSelection(
                repository.getReferenceById(id).getPassword().toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        redirectAttributes.addFlashAttribute("message",
                "You have copied the passwor successfully!");

        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);

            redirectAttributes.addFlashAttribute("message",
                    "The User with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }
}
