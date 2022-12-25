package com.aceinspiration.trainingmanagementsystem.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aceinspiration.trainingmanagementsystem.formmodel.BatchForm;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.Teacher;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.DiscountTypeJpa;
import com.aceinspiration.trainingmanagementsystem.service.TeacherJpa;

@Controller
public class BatchController {

	@Autowired
	private CourseJpa courseService;

	@Autowired
	private TeacherJpa teacherService;

	@Autowired
	private BatchJpa batchService;

	@GetMapping("/admin/batchAddView")
	public String batchAddView(Model model, @RequestParam(name = "message", required = false) String message) {
		model.addAttribute("Tlist", teacherService.selectLatest());
		model.addAttribute("list", courseService.selectAll());
		model.addAttribute("meg", message);
		BatchForm newBatch = new BatchForm();
		model.addAttribute("newBatch", newBatch);
		return "admin/Batch/batchAddView";
	}

	// Start of 12 hour Format Convertor
	public String timeConvertor(String time) {
		int h = (int) time.charAt(0);
		int h2 = (int) time.charAt(1);
		int hh = (h * 10) + h2;
		String meridrien = "";
		char extra = 'a';
		String realTime = "";
		if (hh < 12 || hh == 0) {
			meridrien = " AM";
		} else {
			meridrien = " PM";
		}
		hh %= 12;
		if (hh == 0) {
			hh = 12;
			realTime = hh + ":" + time.charAt(3) + "" + time.charAt(4) + meridrien;

		} else {
			realTime = hh + ":" + time.charAt(3) + "" + time.charAt(4) + meridrien;

		}

		return realTime;
	}

//End of 12 hour format Convertor
	@PostMapping("/admin/addBatch")
	public String addBatch(@Valid BatchForm batchModel, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			if (batchModel.getStartTime() == "" || batchModel.getEndTime() == "") {
				model.addAttribute("Time", "Times can not be blank!");
			}
			if (batchModel.getPlanStartDate() == "" || batchModel.getPlanEndDate() == "") {
				model.addAttribute("Dates", "Plan Dates can not be blank!");
			}
			if (batchModel.getTeacherId() == "") {
				model.addAttribute("Teachers", "Teacher must be selected!");
			}
			if (batchModel.getCourseId() == "") {
				model.addAttribute("Batches", "Batch msut be selected!");
			}
			if (batchModel.getBatchNo() == "") {
				model.addAttribute("BatchNo", "Batch_No cannot be blank!");
			}
			if (batchModel.getDays() == "") {
				model.addAttribute("Days", "Days cannot be blank!");
			}
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			model.addAttribute("newBatch", batchModel);

			return "admin/Batch/batchAddView";
		}
		// Start of Time and Date validation
		//

		int start = batchModel.getStartTime().charAt(0);
		int end = batchModel.getEndTime().charAt(0);
		int start1 = batchModel.getStartTime().charAt(1);
		int end1 = batchModel.getEndTime().charAt(1);

		int pStart = batchModel.getPlanStartDate().charAt(5);
		int pEnd = batchModel.getPlanEndDate().charAt(5);
		int pStart1 = batchModel.getPlanStartDate().charAt(6);
		int pEnd1 = batchModel.getPlanEndDate().charAt(6);
		int pStart2 = batchModel.getPlanStartDate().charAt(8);
		int pEnd2 = batchModel.getPlanEndDate().charAt(8);
		int pStart3 = batchModel.getPlanStartDate().charAt(9);
		int pEnd3 = batchModel.getPlanEndDate().charAt(9);
		int pStart4 = batchModel.getPlanStartDate().charAt(2);
		int pEnd4 = batchModel.getPlanEndDate().charAt(2);
		int pStart5 = batchModel.getPlanStartDate().charAt(3);
		int pEnd5 = batchModel.getPlanEndDate().charAt(3);

		if (batchModel.getStartTime().equals(batchModel.getEndTime())
				|| batchModel.getPlanEndDate().equals(batchModel.getPlanStartDate())) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			// System.out.println(teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());

			model.addAttribute("newBatch", batchModel);
			model.addAttribute("Less", "*Times and Dates must not be equals!");
			;
			return "admin/Batch/batchAddView";
		}
		if (start > end) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			// System.out.println(teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());

			model.addAttribute("newBatch", batchModel);
			model.addAttribute("Less", "Warning : Please, Start hour must be behind End hour!");

			return "admin/Batch/batchAddView";
		}
		if (start == end && start1 > end1) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			// System.out.println(teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());

			model.addAttribute("newBatch", batchModel);

			model.addAttribute("Less", "Warning : Please,Start hour must be behind End hour!");
			return "admin/Batch/batchAddView";
		}
		// for year
		if (pStart4 > pEnd4) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			// System.out.println(teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());

			model.addAttribute("newBatch", batchModel);
			model.addAttribute("plan", "Warning : Please,Start Year must be behind End Year!");

			return "admin/Batch/batchAddView";
		}
		if (pStart4 == pEnd4 && pStart5 > pEnd5) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			// System.out.println(teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());

			model.addAttribute("newBatch", batchModel);

			model.addAttribute("plan", "Warning : Please,Start Year must be behind End Year!");
			return "admin/Batch/batchAddView";
		}

		// for month
		if (pStart4 == pEnd4 && pStart5 == pEnd5) {
			if (pStart > pEnd) {
				model.addAttribute("Tlist", teacherService.selectLatest());
				// System.out.println(teacherService.selectLatest());
				model.addAttribute("list", courseService.selectAll());

				model.addAttribute("newBatch", batchModel);
				model.addAttribute("plan", "Warning : Please,Start Month must be behind End Month!");

				return "admin/Batch/batchAddView";
			}
			if (pStart == pEnd && pStart1 > pEnd1) {
				model.addAttribute("Tlist", teacherService.selectLatest());
				// System.out.println(teacherService.selectLatest());
				model.addAttribute("list", courseService.selectAll());

				model.addAttribute("newBatch", batchModel);

				model.addAttribute("plan", "Warning : Please,Start Month must be behind End Month!");
				return "admin/Batch/batchAddView";
			} else {
				String batchNameCombine = batchModel.getCourseName() + "-" + batchModel.getBatchNo();
				String batchName = batchNameCombine;
				String planStartDate = batchModel.getPlanStartDate();
				String planEndDate = batchModel.getPlanEndDate();
				String actualStartDate = batchModel.getActualStartDate();
				String actualEndDate = batchModel.getActualEndDate();
				Double teacherFee = batchModel.getTeacherFee();
				Double supervisorFee = batchModel.getSupervisorFee();
				Double judgefee = batchModel.getJudgeFee();
				Double otherExpense = batchModel.getOtherExpense();
				Long course = Long.parseLong(batchModel.getCourseId());
				Long teacher = Long.parseLong(batchModel.getTeacherId());
				String days = batchModel.getDays();
				String startTime = batchModel.getStartTime();
				String endTime = batchModel.getEndTime();
				batchService.Batch(batchName, planStartDate, planEndDate, actualStartDate, actualEndDate, teacherFee,
						supervisorFee, judgefee, otherExpense, course, teacher, days, startTime, endTime);
				// model.addAttribute("meg", "Successfully Added");
				String message = "Successfully Added";
				return "redirect:/admin/batchAddView?message=" + message;
			}

		}
		// End of Time and Date Validation
		else {

			String batchNameCombine = batchModel.getCourseName() + "-" + batchModel.getBatchNo();
			System.out.println("Batch Name is " + batchNameCombine);
			List<Batch> batches = batchService.getAllBatch();
			if (batches.size() == 0) {
				String batchName = batchNameCombine;

				String planStartDate = batchModel.getPlanStartDate();
				String planEndDate = batchModel.getPlanEndDate();
				String actualStartDate = batchModel.getActualStartDate();
				String actualEndDate = batchModel.getActualEndDate();
				Double teacherFee = batchModel.getTeacherFee();
				Double supervisorFee = batchModel.getSupervisorFee();
				Double judgefee = batchModel.getJudgeFee();
				Double otherExpense = batchModel.getOtherExpense();
				Long course = Long.parseLong(batchModel.getCourseId());
				Long teacher = Long.parseLong(batchModel.getTeacherId());
				String days = batchModel.getDays();
				String startTime = batchModel.getStartTime();
				String endTime = batchModel.getEndTime();
				batchService.Batch(batchName, planStartDate, planEndDate, actualStartDate, actualEndDate, teacherFee,
						supervisorFee, judgefee, otherExpense, course, teacher, days, startTime, endTime);
				return "redirect:/admin/batch";
			}
			// Start of checking batchName are same
			else {
				for (Batch batchAllName : batches) {
					// System.out.println( "BatchName" +batchAllName.getBatchName());
					boolean name = batchAllName.getBatchName().contains(batchNameCombine);
					System.out.println("The Condition" + name);
					if (name) {
						model.addAttribute("Tlist", teacherService.selectLatest());
						model.addAttribute("list", courseService.selectAll());
						model.addAttribute("err", "Batch Name already exit!!!");
						model.addAttribute("newBatch", batchModel);
						return "admin/Batch/batchAddView";
					}
				}
			}
			String batchName = batchNameCombine;
			String planStartDate = batchModel.getPlanStartDate();
			String planEndDate = batchModel.getPlanEndDate();
			String actualStartDate = batchModel.getActualStartDate();
			String actualEndDate = batchModel.getActualEndDate();
			Double teacherFee = batchModel.getTeacherFee();
			Double supervisorFee = batchModel.getSupervisorFee();
			Double judgefee = batchModel.getJudgeFee();
			Double otherExpense = batchModel.getOtherExpense();
			Long course = Long.parseLong(batchModel.getCourseId());
			Long teacher = Long.parseLong(batchModel.getTeacherId());
			String days = batchModel.getDays();
			String startTime = batchModel.getStartTime();
			String endTime = batchModel.getEndTime();
			batchService.Batch(batchName, planStartDate, planEndDate, actualStartDate, actualEndDate, teacherFee,
					supervisorFee, judgefee, otherExpense, course, teacher, days, startTime, endTime);
			// model.addAttribute("meg", "Successfully Added");
			String message = "Successfully Added";
			return "redirect:/admin/batchAddView?message=" + message;
		}

	}

	@GetMapping(path = "/admin/getTeacherName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Teacher> teacherName(@RequestParam(name = "teacherId", required = true) String teacherId) {
		Optional<Teacher> teacherOption = teacherService.getTeacherById(Long.parseLong(teacherId));

		Teacher teacher = null;
		if (teacherOption.isPresent()) {
			teacher = teacherOption.get();
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}

		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	}

	@GetMapping(path = "/admin/getCourseNameByID", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> courseName(@RequestParam(name = "courseId", required = true) String courseId) {
		Course courseOption = courseService.selectOne(Long.parseLong(courseId));
		Course course = courseOption;

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@Autowired
	DiscountTypeJpa discountService;

	@GetMapping("/admin/batchDetailView/{id}")
	public String batchDetailView(BatchForm batchModel, @PathVariable("id") Long id, Model model) {
		model.addAttribute("Tlist", teacherService.selectLatest());
		model.addAttribute("list", courseService.selectAll());
		Batch batch = batchService.Select(id);
		model.addAttribute("StartTime", timeConvertor(batch.getStartTime()));
		model.addAttribute("EndTime", timeConvertor(batch.getEndTime()));
		model.addAttribute("newBatch", batch);
		return "admin/Batch/batchDetailView";
	}

	@GetMapping("/admin/batchEditView/{id}")
	public String batchEditView(Batch batch, @PathVariable("id") Long id, Model model) {
		model.addAttribute("Tlist", teacherService.selectLatest());
		model.addAttribute("list", courseService.selectAll());
		BatchForm batchModel = new BatchForm();
		batch = batchService.Select(id);
		String bname = batch.getBatchName();
		String [] bsplit=bname.split("-");
		String courseName = bsplit[bsplit.length-2];
		String batchNumber = bsplit[bsplit.length-1];
		batchModel.setBatchNo(batchNumber);
		Long teacher = batch.getTeacher().getId();
		String tea = teacher.toString();
		batchModel.setTeacherId(tea);
		Long course = batch.getCourse().getId();
		String co = course.toString();
		batchModel.setCourseId(co);
		model.addAttribute("courseName", courseName);
		model.addAttribute("batchNumber", batchNumber);
		model.addAttribute("newBatch", batch);
		model.addAttribute("batchForm", batchModel);
		return "admin/Batch/batchEditView";
	}

	@PostMapping("/admin/batchEditView/{id}")

	public String EditBatch(@Valid BatchForm batchModel, BindingResult bindingResult, @PathVariable("id") Long id,
			Model model) {
		Batch batch = batchService.Select(id);
		if (bindingResult.hasErrors()) {
			if (batchModel.getStartTime() == "" || batchModel.getEndTime() == "") {
				model.addAttribute("Time", "Times can not be blank!");
			}
			if (batchModel.getPlanStartDate() == "" || batchModel.getPlanEndDate() == "") {
				model.addAttribute("Dates", "Plan Dates can not be blank!");
			}
			
			if (batchModel.getCourseId() == "") {
				model.addAttribute("Batches", "Batch msut be selected!");
			}
			if (batchModel.getBatchNo() == "") {
				model.addAttribute("BatchNo", "Batch_No cannot be blank!");
			}
			if (batchModel.getDays() == "") {
				model.addAttribute("Days", "Days cannot be blank!");
			}
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);

			return "admin/Batch/batchEditView";
		}
		// Start of Time and Date validation
		//

		int start = batchModel.getStartTime().charAt(0);
		int end = batchModel.getEndTime().charAt(0);
		int start1 = batchModel.getStartTime().charAt(1);
		int end1 = batchModel.getEndTime().charAt(1);

		int pStart = batchModel.getPlanStartDate().charAt(5);
		int pEnd = batchModel.getPlanEndDate().charAt(5);
		int pStart1 = batchModel.getPlanStartDate().charAt(6);
		int pEnd1 = batchModel.getPlanEndDate().charAt(6);
		int pStart2 = batchModel.getPlanStartDate().charAt(8);
		int pEnd2 = batchModel.getPlanEndDate().charAt(8);
		int pStart3 = batchModel.getPlanStartDate().charAt(9);
		int pEnd3 = batchModel.getPlanEndDate().charAt(9);
		int pStart4 = batchModel.getPlanStartDate().charAt(2);
		int pEnd4 = batchModel.getPlanEndDate().charAt(2);
		int pStart5 = batchModel.getPlanStartDate().charAt(3);
		int pEnd5 = batchModel.getPlanEndDate().charAt(3);

		if (batchModel.getStartTime().equals(batchModel.getEndTime())
				|| batchModel.getPlanEndDate().equals(batchModel.getPlanStartDate())) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);
			model.addAttribute("Less", "*Times and Dates must not be equals!");
			
			return "admin/Batch/batchEditView";
		}
		if (start > end) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);
			model.addAttribute("Less", "Warning : Please, Start hour must be behind End hour!");

			return "admin/Batch/batchEditView";
		}
		if (start == end && start1 > end1) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);
			model.addAttribute("Less", "Warning : Please,Start hour must be behind End hour!");
			return "admin/Batch/batchEditView";
		}
		// for year
		if (pStart4 > pEnd4) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);
			model.addAttribute("plan", "Warning : Please,Start Year must be behind End Year!");

			return "admin/Batch/batchEditView";
		}
		if (pStart4 == pEnd4 && pStart5 > pEnd5) {
			model.addAttribute("Tlist", teacherService.selectLatest());
			model.addAttribute("list", courseService.selectAll());
			batch = batchService.Select(id);
			String bname = batch.getBatchName();
			String courseName = bname.substring(0, 3);
			String batchNumber = bname.substring(4);
			batchModel.setBatchNo(batchNumber);

			Long teacher = batch.getTeacher().getId();
			String tea = teacher.toString();
			batchModel.setTeacherId(tea);
			Long course = batch.getCourse().getId();
			String co = course.toString();
			batchModel.setCourseId(co);
			model.addAttribute("courseName", courseName);
			model.addAttribute("batchNumber", batchNumber);
			model.addAttribute("newBatch", batch);
			model.addAttribute("batchForm", batchModel);
			model.addAttribute("plan", "Warning : Please,Start Year must be behind End Year!");
			return "admin/Batch/batchEditView";
		}

		// for month
		if (pStart4 == pEnd4 && pStart5 == pEnd5) {
			if (pStart > pEnd) {
				model.addAttribute("Tlist", teacherService.selectLatest());
				model.addAttribute("list", courseService.selectAll());
				batch = batchService.Select(id);
				String bname = batch.getBatchName();
				String courseName = bname.substring(0, 3);
				String batchNumber = bname.substring(4);
				batchModel.setBatchNo(batchNumber);

				Long teacher = batch.getTeacher().getId();
				String tea = teacher.toString();
				batchModel.setTeacherId(tea);
				Long course = batch.getCourse().getId();
				String co = course.toString();
				batchModel.setCourseId(co);
				model.addAttribute("courseName", courseName);
				model.addAttribute("batchNumber", batchNumber);
				model.addAttribute("newBatch", batch);
				model.addAttribute("batchForm", batchModel);
				return "admin/Batch/batchEditView";
			}
			if (pStart == pEnd && pStart1 > pEnd1) {
				model.addAttribute("Tlist", teacherService.selectLatest());
				model.addAttribute("list", courseService.selectAll());
				batch = batchService.Select(id);
				String bname = batch.getBatchName();
				String courseName = bname.substring(0, 3);
				String batchNumber = bname.substring(4);
				batchModel.setBatchNo(batchNumber);

				Long teacher = batch.getTeacher().getId();
				String tea = teacher.toString();
				batchModel.setTeacherId(tea);
				Long course = batch.getCourse().getId();
				String co = course.toString();
				batchModel.setCourseId(co);
				model.addAttribute("courseName", courseName);
				model.addAttribute("batchNumber", batchNumber);
				model.addAttribute("newBatch", batch);
				model.addAttribute("batchForm", batchModel);

				model.addAttribute("plan", "Warning : Please,Start Month must be behind End Month!");
				return "admin/Batch/batchEditView";
			} else {
				String planStartDate = batch.setPlanStartDate(batchModel.getPlanStartDate());
				String planEndDate = batch.setPlanEndDate(batchModel.getPlanEndDate());
				String actualStartDate = batch.setActualStartDate(batchModel.getActualStartDate());
				String actualEndDate = batch.setActualEndDate(batchModel.getActualEndDate());
				Double teacherF0ee = batch.setTeacherFee(batchModel.getTeacherFee());
				Double supervisorFee = batch.setSupervisorFee(batchModel.getSupervisorFee());
				Double judgefee = batch.setJudgeFee(batchModel.getJudgeFee());
				Double otherExpense = batch.setOtherExpense(batchModel.getOtherExpense());

				String days = batch.setDays(batchModel.getDays());
				String startTime = batch.setStartTime(batchModel.getStartTime());
				String endTime = batch.setEndTime(batchModel.getEndTime());
				Long teacher = Long.parseLong(batchModel.getTeacherId());
				Optional<Teacher> teacherOption = teacherService.getTeacherById(teacher);

				Teacher t = teacherOption.get();
				batch.setTeacher(t);
				Long course = Long.parseLong(batchModel.getCourseId());
				Optional<Course> courseOption = courseService.getCourseById(course);

				Course c = courseOption.get();
				batch.setCourse(c);

				batchService.saveBatch(batch);
				String message = "Successfully Added";
				return "redirect:/admin/batch?message=" + message;
			}

		}
		// End of Time and Date Validation
		
		batch = batchService.Select(id);
		String planStartDate = batch.setPlanStartDate(batchModel.getPlanStartDate());
		String planEndDate = batch.setPlanEndDate(batchModel.getPlanEndDate());
		String actualStartDate = batch.setActualStartDate(batchModel.getActualStartDate());
		String actualEndDate = batch.setActualEndDate(batchModel.getActualEndDate());
		Double teacherF0ee = batch.setTeacherFee(batchModel.getTeacherFee());
		Double supervisorFee = batch.setSupervisorFee(batchModel.getSupervisorFee());
		Double judgefee = batch.setJudgeFee(batchModel.getJudgeFee());
		Double otherExpense = batch.setOtherExpense(batchModel.getOtherExpense());

		String days = batch.setDays(batchModel.getDays());
		String startTime = batch.setStartTime(batchModel.getStartTime());
		String endTime = batch.setEndTime(batchModel.getEndTime());
		Long teacher = Long.parseLong(batchModel.getTeacherId());
		Optional<Teacher> teacherOption = teacherService.getTeacherById(teacher);

		Teacher t = teacherOption.get();
		batch.setTeacher(t);
		Long course = Long.parseLong(batchModel.getCourseId());
		Optional<Course> courseOption = courseService.getCourseById(course);

		Course c = courseOption.get();
		batch.setCourse(c);

		batchService.saveBatch(batch);
		model.addAttribute("message", "Successfully Added");
		String message = "Successfully Updated";

		return "redirect:/admin/batch?message=" + message;
		

	}
}
