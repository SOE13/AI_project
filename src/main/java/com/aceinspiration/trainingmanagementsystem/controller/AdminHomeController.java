package com.aceinspiration.trainingmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aceinspiration.trainingmanagementsystem.formmodel.RatingForm;
import com.aceinspiration.trainingmanagementsystem.model.Admin;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.ComingCourse;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.DiscountType;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.model.Role;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.Teacher;
import com.aceinspiration.trainingmanagementsystem.model.Timetable;
import com.aceinspiration.trainingmanagementsystem.service.AdminJpa;
import com.aceinspiration.trainingmanagementsystem.service.AdminRoleJpa;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.ComingCourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseMarkingJpa;
import com.aceinspiration.trainingmanagementsystem.service.DiscountTypeJpa;
import com.aceinspiration.trainingmanagementsystem.service.GalleryJpa;
import com.aceinspiration.trainingmanagementsystem.service.RegisterJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentJpa;
import com.aceinspiration.trainingmanagementsystem.service.TeacherJpa;
import com.aceinspiration.trainingmanagementsystem.service.TimetableJpa;



@Controller
public class AdminHomeController {
	// Start Soe Thant
	@Autowired
	AdminRoleJpa roleJpa;
	@Autowired
	AdminJpa adminJpa;
	@Autowired
	TeacherJpa teacherJpa;
	@Autowired
	CourseJpa courseJpa;
	@Autowired
	StudentJpa studentJpa;

	@Autowired
	StudentBatchJpa studentBatchJpa;

	 @GetMapping("/admin")

	public String index(Model model) {
		List<Admin> admin = adminJpa.selectAll();
		List<Teacher> teacher = teacherJpa.selectLatest();
		List<Course> course = courseJpa.selectAll();
		List<Student> student = studentJpa.selectAll();
		
		

		int rate = 0;
		List<RatingForm> selectrateList = studentBatchJpa.getBatchCountByBatchId();
		List<RatingForm> rateList=new ArrayList<RatingForm>();
		int i = 0;
		for (RatingForm ratingForm : selectrateList) {
			Long totalStar=5*studentBatchJpa.getStudentCount(ratingForm.getBid());
			float persent=(ratingForm.getTotalRating()*100)/totalStar ;
		System.out.println(persent);
			if(persent<=20) {
				rate=1;
			}else if(persent>20 && persent <=40){
				rate=2;
			}else if(persent>40 && persent <=60){
				rate=3;
			}else if(persent>60 && persent <=80){
				rate=4;
			}else {
				rate=5;
			}
			
			ratingForm.setTotalRating(rate);
			selectrateList.get(i).setTotalRating(ratingForm.getTotalRating());

			i++;

			Batch batch = batchService.Select(ratingForm.getBid());
			batch.setRatebyStudent((int) ratingForm.getTotalRating());
			batchService.saveBatch(batch);
			rateList.add(ratingForm);
		}

		
		
		model.addAttribute("rateList", rateList);
		model.addAttribute("admin", admin.size());
		model.addAttribute("teacher", teacher.size());
		model.addAttribute("course", course.size());
		model.addAttribute("student", student.size());
		return "admin/Dashboard/index";
	}

	@GetMapping("/login")
	public String login() {
		return "admin/Dashboard/login";
	}



	@GetMapping("/admin/role")
	public String role(Model model) {
		model.addAttribute("listRole", roleJpa.findAll());
		Role newAddRole = new Role();
		model.addAttribute("newAddRole", newAddRole);
		return "admin/Admin/adminRole";
	}

	@GetMapping("/admin/addAdmin")
	public String addAdmin(Model model) {
		model.addAttribute("list", adminJpa.selectAll());
		return "admin/Admin/addAdmin";
	}

	// End Soethant

// Start OakarPhyo

	@Autowired
	CourseMarkingJpa courseMarkingJpa;

	@GetMapping("/admin/courseMarking")
	public String courseMarking(Model model) {
		model.addAttribute("list", courseMarkingJpa.selectAll());
		return "admin/Course/courseMarking";
	}

	@GetMapping("/admin/teacher")
	public String teacher(Model model) {
		return teacherPage(model, 1);
	}

	@GetMapping("/teacherPage/{pageNumber}")
	public String teacherPage(Model model, @PathVariable("pageNumber") int current) {
		Page<Teacher> page = teacherJpa.select(current);
		int totalpage = page.getTotalPages();
		List<Teacher> list = page.getContent();
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("current", current);
		return "admin/Teacher/teacher";
	}

	// end OakarPhyo

	// Start Zawng Zay Batch
	@Autowired
	BatchJpa batchService;
	@Autowired
	private CourseJpa courseService;

	@GetMapping("/admin/batch")
	public String batch(Model model) {
		model.addAttribute("list", courseService.selectAll());
		return "admin/Batch/batch";
	}

	@GetMapping(path = "/admin/getBatch")

	public ModelAndView getBatchName(Model model, @RequestParam(name = "batchName", required = false) String batch) {

		List<Batch> batchList = batchService.getBatchByName(batch);

		Batch batchName = null;
		if (batchList.size() > 0) {
			batchName = batchList.get(0);
			String bname = batchName.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("Batchs", batchName);
			model.addAttribute("list", courseService.selectAll());
			return new ModelAndView("admin/Batch/batch");
		} else {
			model.addAttribute("ErrorM", "No data found");
			model.addAttribute("list", courseService.selectAll());
			return new ModelAndView("admin/Batch/batch");
		}

	}
	// End Zawng Zay Batch

	// Start Zawng Zay Student Discount

	@Autowired
	DiscountTypeJpa discountType;

	@GetMapping("/admin/studentDiscount")
	public String studentDiscount(Model model) {

		model.addAttribute("discountList", discountType.findAll());
		DiscountType addDiscount = new DiscountType();
		model.addAttribute("addDiscount", addDiscount);

		return "admin/Student/studentDiscount";
	}
	// End Zawng Zay Student Discount

//   >>>>>>>>> Soe Thant- start

	@Autowired
	RegisterJpa registerJpa;

	@GetMapping("/admin/enquiryInformation")
	public String enquiryInformation(Model model) {
		return infoPage(model, 1);
	}

	@GetMapping("/informPage/{pageNumber}")
	public String infoPage(Model model, @PathVariable("pageNumber") int current) {
		Page<Register> page = registerJpa.select(current);
		int totalPage = page.getTotalPages();
		// List<Register> list = page.getContent();
		List<Register> list = registerJpa.findByStatus("Pending");

		model.addAttribute("current", current);
		model.addAttribute("totalpage", totalPage);
		model.addAttribute("list", list);
		return "admin/Information/enquiryInformation";
	}

	@GetMapping("/enquiryInformationUpdate/{id}")
	public String enquiryInformationUpdate(@PathVariable("id") Long id, Model model) {
		Register register = registerJpa.selectOne(id);
		model.addAttribute("register", register);
		return "admin/Information/enquiryInformationEdit";
	}

	@PostMapping("/enquiryInformationUpdate/{id}")
	public String enquiryEdit(@PathVariable("id") Long id, @RequestParam("status") String str,
			@RequestParam("remark") String remark) {
		Register register = registerJpa.selectOne(id);

		register.setStatus(str);
		register.setRemark(remark);
		registerJpa.update(register);
		if(str.equals("Enroll")) {
			return "redirect:/admin/studentBatchRegister";
		}
		
		return "redirect:/admin/enquiryInformation";
	}

	@PostMapping("/searchEnquiryStatus/{pageNumber}")
	public String searchEnquiryStatus(@PathVariable("pageNumber") int current, @RequestParam("status") String str,
			Model model) {
		List<Register> registerList = registerJpa.findByStatus(str);
		Page<Register> page = registerJpa.select(current);
		int totalPage = page.getTotalPages();
		model.addAttribute("totalpage", totalPage);
		model.addAttribute("registerList", registerList);
		model.addAttribute("current", current);
		return "admin/Information/enquiryInformation";
	}

	@Autowired
	TimetableJpa timetableRepo;

	@GetMapping("/admin/timeTable")
	public String timetable(@ModelAttribute("timeTable") Timetable timeTable, Model model) {

		model.addAttribute("list", timetableRepo.selectAll());
		return "admin/Information/timetable";
	}

	@Autowired
	ComingCourseJpa comingCourseJpa;

	@GetMapping("/admin/comingCourse")
	public String comingCourse(@ModelAttribute("comingCourse") ComingCourse course, Model model) {
		model.addAttribute("list", comingCourseJpa.selectAll());
		return "admin/Information/comingCourse";
	}

	@Autowired
	GalleryJpa galleryJpa;

	@GetMapping("/admin/gallery")
	public String gallery(Model model) {
		model.addAttribute("list", galleryJpa.selectAll());
		return "/admin/Information/gallery";
	}

}
