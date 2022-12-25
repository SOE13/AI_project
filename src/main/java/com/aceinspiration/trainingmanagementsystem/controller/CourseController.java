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

import com.aceinspiration.trainingmanagementsystem.formmodel.CourseForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.CourseMarkingForm;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.CourseMarking;
import com.aceinspiration.trainingmanagementsystem.model.CourseType;
import com.aceinspiration.trainingmanagementsystem.model.Grate;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseMarkingJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseTypeJpa;
import com.aceinspiration.trainingmanagementsystem.service.GrateJpa;
//Soethant 
@Controller
public class CourseController {

	@Autowired
	CourseTypeJpa courseTypeJpa;
	@Autowired
	CourseJpa courseJpa;
	@Autowired
	GrateJpa grateJpa;
	@GetMapping("/admin/courseType")
	public String courseType(@ModelAttribute("courseType") CourseType courseType, Model model) {
		model.addAttribute("list", courseTypeJpa.selectAll());
		return "admin/Course/courseType";
	}

	@PostMapping("/admin/addCourseType")
	public String addcourseType(@ModelAttribute("courseType") @Valid CourseType courseType, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("courseType", courseType);
			model.addAttribute("list", courseTypeJpa.selectAll());
			return "admin/Course/courseType";
		}
		courseTypeJpa.insert(courseType);

		return "redirect:/admin/courseType";
	}

	@GetMapping("/admin/courseTypeEditView/{id}")
	public String courseTypeEditView(@PathVariable("id") @Valid Long id, Model model) {
		CourseType courseType = courseTypeJpa.selectOne(id);
		model.addAttribute("courseType", courseType);
		return "admin/Course/courseTypeEditView";
	}

	@PostMapping("/admin/updateCourseType/{id}")
	public String courseTypeUpdate(@ModelAttribute("courseType") @Valid CourseType courseType,
			BindingResult bindingResult, @PathVariable("id") Long id, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("courseType", courseType);
			return "admin/Course/courseTypeEditView";
		}
		CourseType updateCourseType = courseTypeJpa.selectOne(id);
		updateCourseType.setName(courseType.getName());
		courseTypeJpa.update(updateCourseType);
		return "redirect:/admin/courseType";
	}

	@GetMapping("/admin/courseTypeDelete/{id}")
	public String courseTypeDelete(@PathVariable("id") Long id) {
		List<Course> clist = courseJpa.selectAll();
		CourseType ct = courseTypeJpa.selectOne(id);
		for (Course c : clist) {
			if (c.getType().equals(ct)) {
				return "redirect:/admin/courseType";
			}
		}
		courseTypeJpa.delete(id);
		return "redirect:/admin/courseType";
	}

	@GetMapping("/admin/addCourse")
	public String addCourse(Model model) {
		model.addAttribute("list", courseJpa.selectAll());
		return "admin/Course/addCourse";
	}

	@GetMapping("/admin/courseAddView")
	public String courseAddView(Model model) {
		CourseForm c = new CourseForm();
		List<CourseType> list = courseTypeJpa.selectAll();
		model.addAttribute("course", c);
		model.addAttribute("list", list);
		return "admin/Course/courseAddView";
	}

	@PostMapping("/admin/addCourse")
	public String addCourse(@ModelAttribute("course") @Valid CourseForm course, BindingResult bindingResult,
			Model model, @RequestParam("f") MultipartFile multipartFile) {
		if (bindingResult.hasErrors()) {
			List<CourseType> list = courseTypeJpa.selectAll();
			model.addAttribute("course", course);
			model.addAttribute("list", list);
			return "admin/Course/courseAddView";
		}
// Get file name
		String fileName = multipartFile.getOriginalFilename();

// Create path or dir
		String dir = "./images/CourseImg/";
		Path path = Paths.get(dir);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Path filePath = path.resolve(fileName);

//Save Image 
		try {
			InputStream inputStream = multipartFile.getInputStream();
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		course.setImg(fileName);
		CourseType ct = new CourseType(course.getType());
		Course co = new Course(course.getName(), course.getDesName(), course.getFee(), ct, course.getDuration(),
				course.getDetail(), course.getImg());
		courseJpa.save(co);
		return "redirect:/admin/addCourse";
	}

	@GetMapping("/admin/courseDelete/{id}")
	public String courseDelete(@PathVariable("id") Long id) {
		Course course = courseJpa.selectOne(id);
		String dir = "." + course.getImg();
		Path path = Paths.get(dir);

		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		course.setD("deleted");
		courseJpa.update(course);
		return "redirect:/admin/addCourse";
	}

	@GetMapping("/admin/courseEditView/{id}")
	public String courseEditView(@PathVariable("id") Long id, Model model) {
		List<CourseType> list = courseTypeJpa.selectAll();
		model.addAttribute("list", list);
		Course c = courseJpa.selectOne(id);
		CourseForm course = new CourseForm(c.getId(), c.getName(), c.getDesName(), c.getFee(), c.getType().getId(),
				c.getDuration(), c.getDetail(), c.getImg());
		model.addAttribute("course", course);
		return "admin/Course/courseEditView";
	}

	@PostMapping("/admin/updateCourse/{id}")
	public String updateCourse(@ModelAttribute("course") @Valid CourseForm course, BindingResult bindingResult,
			@PathVariable("id") Long id, Model model, @RequestParam("f") MultipartFile multipartFile) {
		if (bindingResult.hasErrors()) {
			List<CourseType> list = courseTypeJpa.selectAll();
			model.addAttribute("list", list);
			return "admin/Course/courseEditView";
		}

		Course updateCourse = courseJpa.selectOne(id);
//Path for delete
				String deldir = "." + updateCourse.getImg();
				Path delpath = Paths.get(deldir);

//Delete Image  		
				try {
					Files.deleteIfExists(delpath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
// Get file name
				String fileName = multipartFile.getOriginalFilename();

// Create path or dir
				String dir = "./images/CourseImg/";
				Path path = Paths.get(dir);
				if (!Files.exists(path)) {
					try {
						Files.createDirectories(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				Path filePath = path.resolve(fileName);

//Save Image 
				try {
					InputStream inputStream = multipartFile.getInputStream();
					Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		CourseType ct = new CourseType(course.getType());

		updateCourse.setName(course.getName());
		updateCourse.setDesName(course.getDesName());
		updateCourse.setDetail(course.getDetail());
		updateCourse.setDuration(course.getDuration());
		updateCourse.setFee(course.getFee());
		updateCourse.setImg(fileName);
		updateCourse.setType(ct);
		courseJpa.update(updateCourse);
		return "redirect:/admin/addCourse";
	}


	@Autowired
	CourseMarkingJpa courseMarkingJpa;
	@GetMapping("/admin/courseMarkingAddView")
	public String courseMarkingAddView(@ModelAttribute("courseMarking") CourseMarkingForm courseMarking, Model model) {
		model.addAttribute("courses", courseJpa.selectAll());
		return "admin/Course/courseMarkingAddView";
	}

	@PostMapping("/admin/addCourseMarking")
	public String addCourseMarking(@ModelAttribute("courseMarking") @Valid CourseMarkingForm c,
			BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("courses", courseJpa.selectAll());
			return "admin/Course/courseMarkingAddView";
		}
		List<CourseMarking> cmlist=courseMarkingJpa.selectAll();
		Course course=new Course(c.getCourse());
		for(CourseMarking cm:cmlist) {
			if(cm.getCourse().getId()==c.getCourse()) {
				model.addAttribute("courses", courseJpa.selectAll());
				model.addAttribute("courseMarking", c);
				model.addAttribute("message", "This Course is already used in CourseMarking!");
				return "admin/Course/courseMarkingAddView";
			}
		}
		CourseMarking courseMark=new CourseMarking(course, 	c.getAttendanceMark(), c.getAssignmentMark(), c.getMidTermMark(), c.getFinalMark(), c.getProjectMark());
		courseMarkingJpa.insert(courseMark);
		return "redirect:/admin/courseMarking";

	}
	@GetMapping("/admin/courseMarkingEditView/{id}")
	public String courseMarkingEditView(@PathVariable("id") long id, Model model) {
		CourseMarking cm=courseMarkingJpa.selectOne(id);
		model.addAttribute("courseMarking", cm);
		return "admin/Course/courseMarkingEditView";
	}
	

	@PostMapping("/admin/updateCourseMarking/{id}")
	public String updateCourseMarking(@ModelAttribute("courseMarking") @Valid CourseMarking courseMark,
			BindingResult bindingResult, @PathVariable("id") long id, Model model) {
		CourseMarking updateCourseMarking = courseMarkingJpa.selectOne(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("courseMarking", courseMark);
			return "/admin/Course/courseMarkingEditView";
		}
		updateCourseMarking.setAttendanceMark(courseMark.getAttendanceMark());
		updateCourseMarking.setAssignmentMark(courseMark.getAssignmentMark());
		updateCourseMarking.setMidTermMark(courseMark.getMidTermMark());
		updateCourseMarking.setFinalMark(courseMark.getFinalMark());
		updateCourseMarking.setProjectMark(courseMark.getProjectMark());
		courseMarkingJpa.update(updateCourseMarking);
		return "redirect:/admin/courseMarking";
	}

	

	@GetMapping("/admin/courseMarkingDelete/{id}")
	public String courseMarkingDelete(@PathVariable("id") long id) {
		 CourseMarking cm=courseMarkingJpa.selectOne(id);
		 courseMarkingJpa.delete(cm);
		return "redirect:/admin/courseMarking";
	}
}
