package com.lab8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab8.entity.Account;
import com.lab8.repository.AccountRepository;

@Controller
public class HomeController {
	@Autowired
	AccountRepository dao;

	@RequestMapping({ "/", "/home/index" })
	public String home() {
		return "redirect:/product/list";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}

	@RequestMapping("/capnhatuser")
	public String capnhatuser(Model model) {
		Account Account = new Account();
		model.addAttribute("Account", Account);
		List<Account> Accounts = dao.findAll();
		model.addAttribute("Accounts", Accounts);
		return "security/capnhatuser";
	}

	@RequestMapping("/capnhatuser/update")
	public String update(Model model, Account Account) {
		dao.save(Account);
		model.addAttribute("message", "Cập nhật thành công");
		return "redirect:/capnhatuser/edit/" + Account.getUsername();
	}

	@RequestMapping("/capnhatuser/edit/{username}")
	public String edit(Model model, @PathVariable("username") String id) {
		// 2. tìm theo id
		Account Account = dao.findById(id).get();
		model.addAttribute("Account", Account);
		List<Account> Accounts = dao.findAll();
		model.addAttribute("Accounts", Accounts);
		return "security/capnhatuser";
	}

	@RequestMapping("/capnhatuser/delete/{username}")
	public String delete(@PathVariable("id") String id) {
		dao.deleteById(id);
		return "redirect:/capnhatuser";
	}

	// khi click vào button create
	@RequestMapping("/capnhatuser/create")
	public String create(Model model, Account Account) {
		// thêm 1 danh mục mới vào bảng categories
		dao.save(Account);
		model.addAttribute("message", "Tạo tài khoản thành công");
		return "redirect:/capnhatuser";
	}
}
