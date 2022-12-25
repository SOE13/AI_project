package com.aceinspiration.trainingmanagementsystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aceinspiration.trainingmanagementsystem.model.Teacher;
import com.aceinspiration.trainingmanagementsystem.service.TeacherJpa;

@Controller
public class TeacherController {

	@Autowired
	TeacherJpa teacherJpa;

	@GetMapping("/admin/teacherAddView")
	public String teacherAddView(Model model) {
		Teacher th = new Teacher();
			List<Teacher> delTeacher=teacherJpa.selectDeleted();
			if(delTeacher.size()==0) {
				Long id = (long) (teacherJpa.selectLatest().size()+1);
				String tid = String.format("T%03d", id);
				th.setTeacherId(tid);
				model.addAttribute("teacher", th);
			}else {
				Teacher deletedTeacher=delTeacher.get(0);
				Teacher newTeacher=new Teacher();
				newTeacher.setTeacherId(deletedTeacher.getTeacherId());
				model.addAttribute("teacher", newTeacher);
			}
		return "admin/Teacher/teacherAddView";
	}

	@PostMapping("/admin/addTeacher")
	public String addTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult bindingResult,
			Model model, @RequestParam("f") MultipartFile img, @RequestParam("cvf") MultipartFile cv) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("teacher", teacher);
			System.out.println(teacher);
			return "admin/Teacher/teacherAddView";
		}
// Get file name
		String imgName = img.getOriginalFilename();
		String cvName = cv.getOriginalFilename();
//Create path and dir
		String imgDir = "./images/Teacher/TeacherPhoto/";
		String cvDir = "./images/Teacher/TeacherCv/";
		Path imgPath = Paths.get(imgDir);
		Path cvPath = Paths.get(cvDir);
		if (!Files.exists(imgPath) || !Files.exists(cvPath)) {
			try {
				Files.createDirectories(imgPath);
				Files.createDirectories(cvPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Path imgfilePath = imgPath.resolve(imgName);
		Path cvfilePath = cvPath.resolve(cvName);
//Save image
		try {
			InputStream imgInputStream = img.getInputStream();
			InputStream cvInputStream = cv.getInputStream();
			Files.copy(imgInputStream, imgfilePath, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(cvInputStream, cvfilePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		teacher.setImg(imgName);
		teacher.setCv(cvName);
		teacherJpa.save(teacher);
		List<Teacher> delTeacher=teacherJpa.selectDeleted();
		if(delTeacher.size()!=0) {
			Teacher deletedTeacher=delTeacher.get(0);
			deletedTeacher.setD("replace");
			teacherJpa.update(deletedTeacher);
		}
		return "redirect:/admin/teacher";
	}

	@GetMapping("/admin/deleteTeacher/{id}")
	public String deleteTeacher(@PathVariable("id") Long id) {
		Teacher teacher = teacherJpa.selectOne(id);

		String imgDir = "." + teacher.getImg();
		String cvDir = "." + teacher.getCv();

		Path imgPath = Paths.get(imgDir);
		Path cvPath = Paths.get(cvDir);

		try {
			Files.deleteIfExists(imgPath);
			Files.deleteIfExists(cvPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		teacher.setD("deleted");
		teacherJpa.update(teacher);
		return "redirect:/admin/teacher";
	}

	@GetMapping("/admin/teacherDetailView/{id}")
	public String teacherDetailView(@PathVariable("id") Long id, Model model) {
		Teacher teacher = teacherJpa.selectOne(id);
		model.addAttribute("teacher", teacher);

		return "admin/Teacher/teacherDetailView";
	}

	@GetMapping("/admin/teacherEditView/{id}")
	public String teacherEditView(@PathVariable("id") Long id, Model model) {
		Teacher teacher = teacherJpa.selectOne(id);
		model.addAttribute("teacher", teacher);
		return "admin/Teacher/teacherEditView";
	}

	@PostMapping("/admin/updateTeacher/{id}")
	public String updateTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, @PathVariable("id") Long id,
			BindingResult bindingResult, Model model, @RequestParam("f") MultipartFile img,
			@RequestParam("cvf") MultipartFile cv) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("teacher", teacher);
			return "admin/Teacher/teacherEditView";
		}
		Teacher updateTeacher = teacherJpa.selectOne(id);
		updateTeacher.setTeacherId(teacher.getTeacherId());
		updateTeacher.setName(teacher.getName());
		updateTeacher.setNrc(teacher.getNrc());
		updateTeacher.setEmail(teacher.getEmail());
		updateTeacher.setPhNo(teacher.getPhNo());
		updateTeacher.setAddress(teacher.getAddress());
		updateTeacher.setEducation(teacher.getEducation());
		updateTeacher.setOther(teacher.getOther());
		if (img.isEmpty() && cv.isEmpty()) {
			teacherJpa.update(updateTeacher);
		} else if (cv.isEmpty()) {
//Get file name
			String imgName = img.getOriginalFilename();
//Create path and dir
			String imgDir = "./images/Teacher/TeacherPhoto/";
			Path imgPath = Paths.get(imgDir);
			if (!Files.exists(imgPath) ) {
				try {
					Files.createDirectories(imgPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Path imgfilePath = imgPath.resolve(imgName);
//Path for delete
			String delimgDir = "." + updateTeacher.getImg();
			Path delimgPath = Paths.get(delimgDir);
			// Save and delete image
			try {
				InputStream imgInputStream = img.getInputStream();
				Files.deleteIfExists(delimgPath);
				Files.copy(imgInputStream, imgfilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			updateTeacher.setImg(imgName);
		} else if (img.isEmpty()) {
//Get file name
			String cvName = cv.getOriginalFilename();
//Create path and dir
			String cvDir = "./images/Teacher/TeacherCv/";
			Path cvPath = Paths.get(cvDir);
			if ( !Files.exists(cvPath)) {
				try {
					Files.createDirectories(cvPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Path cvfilePath = cvPath.resolve(cvName);
//Path for delete
			String delcvDir = "." + updateTeacher.getCv();
			Path delcvPath = Paths.get(delcvDir);
// Save and delete image
			try {
				InputStream cvInputStream = cv.getInputStream();
				Files.deleteIfExists(delcvPath);
				Files.copy(cvInputStream, cvfilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			updateTeacher.setCv(cvName);
		} else {
//Get file name
			String imgName = img.getOriginalFilename();
			String cvName = cv.getOriginalFilename();

//Path for delete
			String delimgDir = "." + updateTeacher.getImg();
			String delcvDir = "." + updateTeacher.getCv();

			Path delimgPath = Paths.get(delimgDir);
			Path delcvPath = Paths.get(delcvDir);
			System.out.println("error");
//Delete Image  			
			try {
				Files.deleteIfExists(delimgPath);
				Files.deleteIfExists(delcvPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
//Create path and dir
			String imgDir = "./images/Teacher/TeacherPhoto/";
			String cvDir = "./images/Teacher/TeacherCv/";
			Path imgPath = Paths.get(imgDir);
			Path cvPath = Paths.get(cvDir);
			if (!Files.exists(imgPath) || !Files.exists(cvPath)) {
				try {
					Files.createDirectories(imgPath);
					Files.createDirectories(cvPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Path imgfilePath = imgPath.resolve(imgName);
			Path cvfilePath = cvPath.resolve(cvName);
// Save Image
			try {
				InputStream imgInputStream = img.getInputStream();
				InputStream cvInputStream = cv.getInputStream();
				Files.copy(imgInputStream, imgfilePath, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(cvInputStream, cvfilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			updateTeacher.setCv(cvName);
			updateTeacher.setImg(imgName);
		}

		teacherJpa.update(updateTeacher);
		return "redirect:/admin/teacher";
	}

}
