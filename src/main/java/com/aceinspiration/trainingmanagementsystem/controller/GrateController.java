package com.aceinspiration.trainingmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aceinspiration.trainingmanagementsystem.formmodel.GrateForm;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.Grate;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.GrateJpa;

@Controller
public class GrateController {
	
	@Autowired
	GrateJpa grateJpa;
	@Autowired
	CourseJpa courseJpa;

	@GetMapping("/admin/grate")
	public String grate(@ModelAttribute("grate")GrateForm grate,Model model) {
		List<Grate>list=grateJpa.select();
		model.addAttribute("list", list);
		model.addAttribute("courses", courseJpa.selectAll());
		return "admin/Course/grate";
	}
	
	@PostMapping("/admin/addGrate")
	public String add(@ModelAttribute("grate")@Valid GrateForm grate,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("courses", courseJpa.selectAll());
			model.addAttribute("grate", grate);
			return "admin/Course/grate";
		}
		Course c=new Course(grate.getCourse());
		Grate g=new Grate(c, grate.getFromMark(),grate.getToMark(), grate.getSate());
		grateJpa.save(g);
		return "redirect:/admin/grate";
	}
	
	@GetMapping("/admin/grateEdit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Grate grate=grateJpa.selectOne(id);
		GrateForm form=new GrateForm(grate.getId(), grate.getCourse().getId(), grate.getFromMark(),grate.getToMark(), grate.getSate());
		model.addAttribute("courses", courseJpa.selectAll());
		model.addAttribute("grate",form);
		return "admin/Course/grateEdit";
	}
	
	@PostMapping("/updateGrate/{id}")
	public String update(@PathVariable("id")Long id,@ModelAttribute("grate")@Valid GrateForm grate,BindingResult bindingResult,Model model) {
	if(bindingResult.hasErrors()) {
		model.addAttribute("courses", courseJpa.selectAll());
	model.addAttribute("grate", grate);
	return "admin/Course/grateEdit";
	}
	Grate update=grateJpa.selectOne(id);
	update.setSate(grate.getSate());
	update.setFromMark(grate.getFromMark());
	update.setToMark(grate.getToMark());
	grateJpa.update(update);
	return "redirect:/admin/grate";
	}
	
	@GetMapping("/admin/grateDelete/{id}")
	public String grateDelete(@PathVariable("id") Long id) {
		Grate grate=grateJpa.selectOne(id);
		grate.setD("deleted");
		grateJpa.update(grate);
		return "redirect:/admin/grate";
	}
}
