package com.aceinspiration.trainingmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aceinspiration.trainingmanagementsystem.formmodel.StudentBatchForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.DiscountType;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.DiscountTypeJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentJpa;

@Controller
public class StudentBatchController {

	@Autowired
	StudentJpa studentService;

	@Autowired
	BatchJpa batchService;

	@Autowired
	DiscountTypeJpa discountTypeService;

	@Autowired
	CourseJpa courseService;

	@Autowired
	StudentBatchJpa studentBatchService;

	@RequestMapping("/admin/studentBatchRegister")
	public ModelAndView studentBatch(Model model, StudentBatchForm studentBatchForm,
			@RequestParam(name = "stCode", required = false) String stCode,
			@RequestParam(name = "message", required = false) String message) {
		List<Student> students = new ArrayList<>();
		if (stCode != null) {
			students = studentService.getStudentByStudentCode(stCode);
			if(students.size()==0) {
				model.addAttribute("ErrorM", "No data found");
				return new ModelAndView("admin/Student/studentBatchRegister");
			}
		} 
		if(stCode=="") {
			model.addAttribute("ErrorM", "No data found");
			return new ModelAndView("admin/Student/studentBatchRegister");
		}
		model.addAttribute("studentList", students);
		List<Batch> batches = batchService.getAllBatch();
		List<DiscountType> discountTypes = discountTypeService.findAll();
		List<Course> courseName = courseService.selectAll();
		model.addAttribute("courseName", courseName);
		model.addAttribute("batches", batches);
		model.addAttribute("discountTypes", discountTypes);
		model.addAttribute("stCode", stCode);
		model.addAttribute("Message1", message);
		return new ModelAndView("admin/Student/studentBatchRegister");
	}

	@GetMapping(path = "/admin/getBatchInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Batch> sayHello(
			@RequestParam(name = "batchName", required = true) String batch) {
		com.aceinspiration.trainingmanagementsystem.model.Batch batchName = null;
		List<Batch> batches = batchService.getAllBatch();
		for (Batch batchAllName : batches) {
			boolean name = batchAllName.getBatchName().contains(batch);
			if (name) {
				return new ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Batch>(batchAllName,
						HttpStatus.OK);

			}
		}
		return new ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Batch>(batchName, HttpStatus.OK);
	}

	@GetMapping(path = "/admin/getDiscountType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiscountType> getDiscountType(
			@RequestParam(name = "discountTypeId", required = true) String discountTypeId) {

		Optional<DiscountType> discountTypeOption = discountTypeService
				.getDiscountTypById(Long.parseLong(discountTypeId));

		DiscountType discountType = null;
		if (discountTypeOption.isPresent()) {
			discountType = discountTypeOption.get();
		}

		return new ResponseEntity<DiscountType>(discountType, HttpStatus.OK);
	}

	@RequestMapping("/admin/handleStudentBatch")
	public String handleStudentBatch(@Valid StudentBatchForm studentBatchForm, 
			BindingResult bindingResult,
			Model model) {
		String oldBatch = null;
		Optional<Batch> batchInfo;
		List<Batch> batchList;
		
		if (bindingResult.hasErrors()) {
			long stId = Long.parseLong(studentBatchForm.getStudentId());
			System.out.println("if if if if");
			System.out.println("stid : "+stId);
			String stCode=studentBatchForm.getCode();
			System.out.println("stCode : "+stCode); 
			List<Student> students = new ArrayList<>();
			students = studentService.getStudentByStudentCode(stCode);
			System.out.println("students : "+students.size());
			if(students.size()>0) {
				for(int i=0;i<students.size();i++) {
					System.out.println("student : "+students.get(i).toString());
				}   				
			}
			List<Batch> batches = batchService.getAllBatch();
			List<DiscountType> discountTypes = discountTypeService.findAll();
			List<Course> courseName = courseService.selectAll();
			model.addAttribute("stId", stId);
			model.addAttribute("stCode", stCode);
			model.addAttribute("studentList", students);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batches", batches);
			model.addAttribute("discountTypes", discountTypes);
			model.addAttribute("studentBatchForm", studentBatchForm);
			return "admin/Student/studentBatchRegister";
		}
		
		else {
			Optional<Course> course;
			long batchId = Long.parseLong(studentBatchForm.getBatchName());
			System.out.println("else else else");
			long stId = Long.parseLong(studentBatchForm.getStudentId());
			
			System.out.println("stid : "+stId);
			System.out.println("batchId : "+batchId);
			if(studentBatchForm.isStatus()) {
				System.out.println("c name :"+studentBatchForm.getCouname());
				batchList=batchService.findByCourseId(Long.parseLong(studentBatchForm.getCouname()));
				course=courseService.getCourseById(Long.parseLong(studentBatchForm.getCouname()));
				System.out.println("cname : "+course.get().getName());
				Student student=new Student();
				student.setId(stId);
				List<StudentBatch> studBatchList=studentBatchService.findByStudent(student);
				
				
				batchInfo=batchService.getBatchById(batchId);
				System.out.println("batch name : "+batchInfo.get().getBatchName());
				
				
				for(int sb = 0; sb < studBatchList.size(); sb++) {
					for (int b=0;b<batchList.size();b++) {
						if(studBatchList.get(sb).getBatch().getId()==batchList.get(b).getId()) {
							oldBatch=batchList.get(b).getBatchName();
							System.out.println("old batch name : "+oldBatch);
							
							
							Optional<StudentBatch> studentBatch=studentBatchService.getStudentBatchById(studBatchList.get(sb).getId());
							StudentBatch studBatch= studentBatch.get();
							studBatch.setStatus("Change To "+batchInfo.get().getBatchName());
							studentBatchService.update(studBatch);
							break;
						}
					}
				}
				
				
			}
			
					
						
			String stCode=studentBatchForm.getCode();
			System.out.println("stCode : "+stCode);
			
			List<StudentBatch> studentBatch=studentBatchService.getAllStudentBatch();
			for(StudentBatch stubalist:studentBatch ) {
				if(stId==stubalist.getStudent().getId() && batchId==stubalist.getBatch().getId()) {
					List<Student> students = new ArrayList<>();
					students = studentService.getStudentByStudentCode(stCode);
					
					
					
					List<Batch> batches = batchService.getAllBatch();
					List<DiscountType> discountTypes = discountTypeService.findAll();
					List<Course> courseName = courseService.selectAll();
					model.addAttribute("stId", stId);
					model.addAttribute("stCode", stCode);
					model.addAttribute("studentList", students);
					model.addAttribute("courseName", courseName);
					model.addAttribute("batches", batches);
					model.addAttribute("discountTypes", discountTypes);
					model.addAttribute("studentBatchForm", studentBatchForm);
					model.addAttribute("ErrorM", "Already Register!!");
					return "admin/Student/studentBatchRegister";
				}
			}
			
			long discountTypeId=0;
			if(studentBatchForm.getDiscountType() == null || studentBatchForm.getDiscountType().equals("")) {
				
				 studentBatchForm.setDiscountType("0");
				 discountTypeId= Long.parseLong(studentBatchForm.getDiscountType());
			}else {
				 discountTypeId = Long.parseLong(studentBatchForm.getDiscountType());
			}
			
			Double actualAmount=null;
			if(studentBatchForm.getActualAmount() == null || studentBatchForm.getActualAmount().equals("") ) {
				studentBatchForm.setActualAmount(studentBatchForm.getCourseFee());
				actualAmount=Double.parseDouble(studentBatchForm.getActualAmount());
			}else {
				actualAmount=Double.parseDouble(studentBatchForm.getActualAmount());
			}
			Double discountAmount=null;
			if(studentBatchForm.getDiscountAmount() == null || studentBatchForm.getDiscountAmount().equals("") ) {
				studentBatchForm.setDiscountAmount("0");
				discountAmount=Double.parseDouble(studentBatchForm.getDiscountAmount());
			}else {
				discountAmount=Double.parseDouble(studentBatchForm.getDiscountAmount());
			}
			
			Double totalAmount = Double.parseDouble(studentBatchForm.getCourseFee());
			System.out.println("isStatus : "+studentBatchForm.isStatus());
			String status = null;
			if (studentBatchForm.isStatus()) {
				status = "Change from "+oldBatch;
				totalAmount=new Double(0);
				
				
			} else {
				status = "New Student";
			}
			if(studentBatch.size()>=0) {
				studentBatchService.saveStudentBatch(stId, batchId, discountTypeId, totalAmount, actualAmount, discountAmount, status);
			}
			//studentBatchService.saveStudentBatch(stId, batchId, discountTypeId, totalAmount, actualAmount,
					//discountAmount, status);

			String message="Save Successfuly";
			return "redirect:/admin/studentBatchRegister?message="+message;
		}

	}

	@GetMapping(path = "/admin/getCourseType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Course> sayHello1(
			@RequestParam(name = "courseId", required = true) String courseId) {

		Optional<com.aceinspiration.trainingmanagementsystem.model.Course> courseOption = courseService
				.getCourseById(Long.parseLong(courseId));
		com.aceinspiration.trainingmanagementsystem.model.Course course = null;
		if (courseOption.isPresent()) {
			course = courseOption.get();
			return new ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Course>(course, HttpStatus.OK);
		}

		return new ResponseEntity<com.aceinspiration.trainingmanagementsystem.model.Course>(course, HttpStatus.OK);
	}
}
