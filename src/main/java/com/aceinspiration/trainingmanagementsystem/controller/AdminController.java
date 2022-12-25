package com.aceinspiration.trainingmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aceinspiration.trainingmanagementsystem.formmodel.AdminForm;
import com.aceinspiration.trainingmanagementsystem.model.Admin;
import com.aceinspiration.trainingmanagementsystem.model.Permission;
import com.aceinspiration.trainingmanagementsystem.model.Role;
import com.aceinspiration.trainingmanagementsystem.service.AdminJpa;
import com.aceinspiration.trainingmanagementsystem.service.AdminRoleJpa;
import com.aceinspiration.trainingmanagementsystem.service.PermissionJpa;

@Controller
public class AdminController {
	@Autowired
	AdminRoleJpa roleJpa;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	AdminJpa adminJpa;
	@Autowired
	PermissionJpa permissionJpa;

// Profile
	@GetMapping("/profile")
	public String profile() {
		return "admin/Dashboard/profile";
	}

	@PostMapping("/updateViewProfile")
	public String updateView(@RequestParam("password") String pass, @RequestParam("mail") String mail, Model model) {
		List<Admin> admlist = adminJpa.selectAll();
		for (Admin adm : admlist) {
			if (adm.getMail().equals(mail)) {
				Admin admin = adminJpa.selectForProfile(mail);
				if (!admin.getConfpass().equals(pass)) {
					model.addAttribute("passError", "Invalid Password");
					return "admin/Dashboard/profile";
				}
				model.addAttribute("admin", admin);
				return "admin/Dashboard/profileUpdate";
			}
		}
		model.addAttribute("mailError", "Invalid Email");
		return "admin/Dashboard/profile";
	}

	@PostMapping("/updateProfile/{id}")
	public String updateProfile(@ModelAttribute("admin") @Valid AdminForm admin, BindingResult bindingResult,
			@PathVariable("id") Long id, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/Dashboard/profileUpdate";
		}
		if (!admin.getPass().equals(admin.getConfpass())) {
			model.addAttribute("error", "Confirm Password must be same with Password!");
			return "admin/Dashboard/profileUpdate";
		}

		Admin ad = adminJpa.selectOne(id);

		List<Permission> list = permissionJpa.select(ad.getMail());
		for (Permission p : list) {
			p.setMail(admin.getMail());
			permissionJpa.update(p);
		}

		ad.setName(admin.getName());
		ad.setMail(admin.getMail());
		ad.setPass(encoder.encode(admin.getPass()));
		ad.setConfpass(admin.getConfpass());
		adminJpa.update(ad);
		return "redirect:/admin";
	}

// Admin
	@GetMapping("/admin/addAdminView")
	public String addAdminView(@ModelAttribute("admin") AdminForm admin, Model model) {
		List<Role> list = roleJpa.findAll();
		model.addAttribute("roles", list);
		return "admin/Admin/adminAddView";
	}

	@PostMapping("/addAdmin")
	public String addAdmin(@ModelAttribute("admin") @Valid AdminForm admin, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Role> list = roleJpa.findAll();
			model.addAttribute("roles", list);
			model.addAttribute("admin", admin);
			return "admin/Admin/adminAddView";
		}

		if (admin.getPass().length() < 8) {
			List<Role> list = roleJpa.findAll();
			model.addAttribute("roles", list);
			model.addAttribute("admin", admin);
			model.addAttribute("perror", "Password must be at least 8 characters");
			return "admin/Admin/adminAddView";
		}

		if (!admin.getPass().equals(admin.getConfpass())) {
			List<Role> list = roleJpa.findAll();
			model.addAttribute("roles", list);
			model.addAttribute("admin", admin);
			model.addAttribute("error", "Confirm Password must be same with Password!");
			return "admin/Admin/adminAddView";
		}

		List<Admin> admlist = adminJpa.selectAll();
		for (Admin adm : admlist) {
			if (adm.getMail().equals(admin.getMail())) {
				List<Role> list = roleJpa.findAll();
				model.addAttribute("roles", list);
				model.addAttribute("admin", admin);
				model.addAttribute("mailmessage", "Your Mail is already have try with another Mail!");
				return "admin/Admin/adminAddView";
			}
		}

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < admin.getRoles().length; i++) {
			Permission permission = new Permission(admin.getMail(), "ROLE_" + admin.getRoles()[i]);
			permissionJpa.save(permission);
			str.append(admin.getRoles()[i]).append(", ");
		}
		System.out.println("perm : "+str+", length : "+str.length());
		System.out.println("str.length-1 "+str.charAt(str.length()-1)+", str.length-2 : "+str.charAt(str.length()-2));
		str.deleteCharAt(str.length()-2);
		System.out.println("after delete perm : "+str);
		String perm = str.toString();
		Role r = new Role(admin.getRole());
		Admin a = new Admin(admin.getName(), admin.getMail(), 
				encoder.encode(admin.getPass()),
				admin.getConfpass(),
				true, r, perm, admin.getRoles());

		adminJpa.insert(a);

		return "redirect:/admin/addAdmin";
	}

	@GetMapping("/admin/adminEditView/{id}")
	public String adminEditView(@PathVariable("id") Long id, Model model) {
		List<Role> list = roleJpa.findAll();
		model.addAttribute("roles", list);
		Admin a = adminJpa.selectOne(id);
		System.out.println("admin  : "+a.getId()+", "+a.getPermit());
		AdminForm form = new AdminForm(a.getId(), a.getName(), a.getMail(), a.getPass(), a.getConfpass(),
				a.getRole().getId(), a.getRoles(),a.getPermit());
		model.addAttribute("admin", form);
		return "admin/Admin/adminEditView";
	}

	@PostMapping("/updateAdmin/{id}")
	public String updateAdmin(@ModelAttribute("admin") AdminForm admin, @PathVariable("id") Long id, Model model) {
		Admin adm = adminJpa.selectOne(id);
		permissionJpa.delete(adm.getMail());

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < admin.getRoles().length; i++) {
			Permission permission = new Permission(admin.getMail(), "ROLE_" + admin.getRoles()[i]);
			permissionJpa.save(permission);
			str.append(admin.getRoles()[i]).append(", ");
		}
		System.out.println("perm : "+str+", length : "+str.length());
		System.out.println("str.length-1 "+str.charAt(str.length()-1)+", str.length-2 : "+str.charAt(str.length()-2));
		str.deleteCharAt(str.length()-2);
		System.out.println("after delete perm : "+str);
		String perm = str.toString();
		Role r = new Role(admin.getRole());
		adm.setName(admin.getName());
		adm.setMail(admin.getMail());
		adm.setRole(r);
		adm.setPermit(perm);
		adm.setEnable(true);
		adminJpa.update(adm);

		return "redirect:/admin/addAdmin";
	}

	@GetMapping("/admin/adminDelete/{id}")
	public String adminDelete(@PathVariable("id") Long id) {
		Admin admin = adminJpa.selectOne(id);
		permissionJpa.delete(admin.getMail());
		adminJpa.delete(id);
		return "redirect:/admin/addAdmin";
	}

	// AdminRole

	@PostMapping("/admin/addRole")
	public ModelAndView addRole(@ModelAttribute("newAddRole") @Valid Role role, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/Admin/adminRole");
		modelAndView.addObject("listRole", roleJpa.findAll());
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("newAddRole", role);
			return modelAndView;
		}

		Role newRole = new Role();
		newRole.setName(role.getName());

		roleJpa.insert(newRole);
		modelAndView.setViewName("redirect:/admin/role");
		return modelAndView;

	}

	@PostMapping("/admin/updateRole/{id}")
	public ModelAndView updateRole(@PathVariable("id") long id, @ModelAttribute("role") @Valid Role role,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/Admin/adminRoleEditView");
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("role", role);
			return modelAndView;
		}
		Role updateRole = roleJpa.findById(id);
		updateRole.setName(role.getName());
		roleJpa.update(updateRole);
		modelAndView.setViewName("redirect:/admin/role");
		return modelAndView;
	}

	@GetMapping("/admin/deleteRole/{id}")
	public String deleteRole(@PathVariable("id") long id) {
		Admin admin = adminJpa.selectOne(id);
		List<Admin> list = adminJpa.selectAll();
		for (Admin a : list) {
			if (a.getRole().getId().equals(admin.getId())) {
				return "redirect:/admin/role";
			}
		}
		roleJpa.deleteById(id);
		return "redirect:/admin/role";
	}

	@GetMapping("/admin/adminRoleEditView/{id}")
	public String adminRoleEditView(@PathVariable("id") long id, Model model) {
		Role role = roleJpa.findById(id);
		model.addAttribute("role", role);
		return "admin/Admin/adminRoleEditView";
	}

}