package com.aceinspiration.trainingmanagementsystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentBatchForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentMarkingForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Course;
import com.aceinspiration.trainingmanagementsystem.model.CourseMarking;
import com.aceinspiration.trainingmanagementsystem.model.DiscountType;
import com.aceinspiration.trainingmanagementsystem.model.Grate;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentMarking;
import com.aceinspiration.trainingmanagementsystem.service.AttendanceJpa;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseMarkingJpa;
import com.aceinspiration.trainingmanagementsystem.service.DiscountTypeJpa;
import com.aceinspiration.trainingmanagementsystem.service.GrateJpa;
import com.aceinspiration.trainingmanagementsystem.service.RegisterJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentMarkingJpa;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class StudentController {

	@Autowired
	CourseJpa courseJpa;

	@Autowired
	CourseMarkingJpa courseMarkingJpa;

	@Autowired
	StudentJpa studentJpa;

	@Autowired
	StudentMarkingJpa studentMarking;

	@Autowired
	GrateJpa grateJpa;

	@Autowired
	DiscountTypeJpa discountTypeService;

	@Autowired
	BatchJpa batchJpa;

	@Autowired
	StudentBatchJpa studentBatchJpa;

	@Autowired
	AttendanceJpa attendanceJpa;

	@Autowired
	RegisterJpa registerJpa;

	@GetMapping("/admin/addStudent")
	public String addStudent(Model model) {
		Student st = new Student();
		List<Student> stud = studentJpa.selectAll();
		if (stud.isEmpty()) {
			Date da = new Date();
			@SuppressWarnings("deprecation")
			int date = da.getYear() - 100;
			//String d = String.format("AI1%d1200000001", date);
			String d = String.format("AI1200000001", date);
			st.setStid(d);
			model.addAttribute("addstudent", st);
		} else {
			for (int i = 0; i < stud.size(); i++) {
				Student stu = stud.get(i);
				//Long id = stu.getId() + 1;
				Long id=(long) (stud.size()+1);
				Date da = new Date();
				@SuppressWarnings("deprecation")
				int date = da.getYear() - 100;
				//String stid = String.format("AI1%d12%08d", date, id);
				String stid = String.format("AI12%08d", id);
				st.setStid(stid);
				model.addAttribute("addstudent", st);
			}
		}
		return "admin/Student/addStudent";
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/admin/addStudent")
	public String addStu(@ModelAttribute("addstudent") @Valid Student student, BindingResult bindingResult, Model model,
			@RequestParam("f") MultipartFile multiPartfile) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", student);
			return "admin/Student/addStudent";
		}

		if (student.getPassword().length() < 8) {
			model.addAttribute("student", student);
			model.addAttribute("perror", "Password must be at least 8 characters!");
			return "admin/Student/addStudent";
		}

		if (!student.getPassword().equals(student.getCpassword())) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Confirm passward must be same with password!");
			return "admin/Student/addStudent";
		}

//Get file name
		String fileName = multiPartfile.getOriginalFilename();

//Create path or dir
		String dir = "./images/Student/StudentPhoto/";

		Path path = Paths.get(dir);

		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Path filePath = path.resolve(fileName);

		// Save Image-----------------------+
		try {
			InputStream inputStream = multiPartfile.getInputStream();
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// QR Code

		String QRdata = student.getStid();
		try {
			String QRCode = "./images/Student/QRCode/";
			Path QRPath = Paths.get(QRCode);
			if (!Files.exists(QRPath)) {
				try {
					Files.createDirectories(QRPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String QRdir = QRCode + QRdata + ".png";
			String charset = "UTF-8";
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = new MultiFormatWriter().encode(new String(QRdata.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, 250, 250, hintMap);
			MatrixToImageWriter.writeToFile(matrix, QRdir.substring(QRdir.lastIndexOf('.') + 1), new File(QRdir));
		} catch (Exception e) {
			System.err.println(e);
		}

		student.setQR(QRdata + ".png");
		student.setImg(fileName);
		studentJpa.save(student);
		return "redirect:/admin/studentBatchRegister";
	}


	
	@GetMapping("/admin/studentList/{name}")
	public String studentList(@PathVariable("name") String name, Model model, HttpServletRequest request) {
		int totalpage = 0, pagenumber = 0;
		String p1 = null, p2 = null;
		List<Batch> batchList = batchJpa.getAllBatch();
		model.addAttribute("batchList", batchList);
		List<Student> list = new ArrayList<Student>();
		if (name.equals("1")) {
		if(request.getServletContext().getAttribute("list")!=null) {
			System.out.println("size : "+list.size());
			list=(List<Student>) request.getServletContext().getAttribute("list");
			model.addAttribute("list", list);
		}
		}
		
		if (request.getServletContext().getAttribute("current") != null
				&& request.getServletContext().getAttribute("totalpage") != null) {
			totalpage = (int) request.getServletContext().getAttribute("totalpage");
			pagenumber = (int) request.getServletContext().getAttribute("current");

			System.out.println("totalpage : " + totalpage);
			System.out.println("pagenumber : " + pagenumber);
		}
		
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("current", pagenumber);

		return "admin/Student/studentList";
	}

	@GetMapping("/studenPage/{pageNumber}")
	public String studentPage(HttpServletRequest request,Model model, @PathVariable("pageNumber") int current, 
			@Param("p1") String p1, @Param("p2") String p2) {
		List<Batch> batchList = batchJpa.getAllBatch();
		model.addAttribute("batchList", batchList);
		
		System.out.println("p1 : "+p1+", p2 : "+p2);
		if(p1!=null) {
			if(p1!="") {
				model.addAttribute("value", p1);
				System.out.println("p1 : "+p1);
				request.getServletContext().setAttribute("p1", p1);
				request.getServletContext().removeAttribute("p2");
			}
		}
		if(p2!=null) {
			if(!p2.equals("BatchName")) {
				model.addAttribute("value", p2);
				System.out.println("p2 : "+p2);
				request.getServletContext().setAttribute("p2", p2);
				request.getServletContext().removeAttribute("p1");
			}
		}
		
		if(request.getServletContext().getAttribute("p1")!=null  || request.getServletContext().getAttribute("p2")!=null) {
			p1=(String) request.getServletContext().getAttribute("p1");
			p2=(String) request.getServletContext().getAttribute("p2");
			System.out.println("p1 in both : "+p1);
			System.out.println("p2 in both : "+p2);
		}
		
		
		Page<StudentRelatedForm> page = studentJpa.select(current, p1, p2);
		int totalpage = page.getTotalPages();
		request.getServletContext().setAttribute("totalpage", totalpage);

		System.out.println("total Page : "+totalpage);
		List<StudentRelatedForm> list = page.getContent();
		System.out.println("list size : "+list.size());
		model.addAttribute("current", current);
		request.getServletContext().setAttribute("current",current);

		System.out.println("current : "+current);
		model.addAttribute("list", list);
		request.getServletContext().setAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		//model.addAttribute("keywork", keyword);
		return "admin/Student/studentList";
	}	
	// Start Zawng Zay DiscountType

	@PostMapping("/admin/addDiscount")
	public String addDiscount(@Valid @ModelAttribute("addDiscount") DiscountType newDiscount,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("addDiscount", newDiscount);
			model.addAttribute("list", discountTypeService.findAll());
			return "admin/Student/studentDiscount";
		}

		discountTypeService.insert(newDiscount);

		return "redirect:/admin/studentDiscount";

	}

	// DiscountType Edit
	@GetMapping("/admin/studentDiscountEditView/{id}")
	public String studentDiscountEditView(@PathVariable("id") long id, Model model) {
		DiscountType discount = discountTypeService.findById(id);
		model.addAttribute("discount", discount);
		return "admin/Student/studentDiscountEditView";
	}

	@PostMapping("/admin/updateDiscount/{id}")
	public String discountUpdate(@ModelAttribute("discount") @Valid DiscountType discountType,
			BindingResult bindingResult, @PathVariable("id") long id, Model model) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("courseType", discountType);
			return "admin/Student/studentDiscountEditView";
		}
		DiscountType updateDiscountType = discountTypeService.findById(id);
		updateDiscountType.setDiscountName(discountType.getDiscountName());
		updateDiscountType.setDiscountPercent(discountType.getDiscountPercent());
		discountTypeService.update(updateDiscountType);
		return "redirect:/admin/studentDiscount";
	}
	// DiscountType Delete

	@GetMapping("/admin/deleteDiscount/{id}")
	public String deleteRole(@PathVariable("id") long id) {

		discountTypeService.deleteById(id);
		return "redirect:/admin/studentDiscount";
	}
	// End Zawng Zay DiscountType

	@GetMapping("/admin/studentDetailView/{id}")
	public String studentDetailView(@PathVariable("id") Long id, Model model) {
		Student student = studentJpa.selectOne(id);
		model.addAttribute("student", student);

		List<StudentRelatedForm> list = studentJpa.findByID(id);

		model.addAttribute("list", list);
		return "admin/Student/studentDetailView";
	}

	@GetMapping("/admin/studentEditView/{id}")
	public String studentEditView(@PathVariable("id") Long id, Model model) {
		Student student = studentJpa.selectOne(id);
		model.addAttribute("updateStudent", student);
		return "admin/Student/studentEditView";
	}

	@PostMapping("/admin/updateStudent/{id}")
	public String updateStudent(@ModelAttribute("updateStudent") Student student, @PathVariable("id") Long id,
			@RequestParam("f") MultipartFile multipartFile) {
		Student st = studentJpa.selectOne(id);
		st.setName(student.getName());
		st.setNRC(student.getNRC());
		st.setFather(student.getFather());
		st.setDOB(student.getDOB());
		st.setPhone(student.getPhone());
		st.setEmail(student.getEmail());
		st.setAddress(student.getAddress());
		st.setRdate(student.getRdate());
		st.setEdu(student.getEdu());
		st.setOther(student.getOther());

		if (multipartFile.isEmpty()) {
			studentJpa.update(st);
		} else {
			// Get new file name
			String fileName = multipartFile.getOriginalFilename();
			
			// Path for delete
			String deldir = "." + st.getImg();
			Path delpath = Paths.get(deldir);
			//Delete image
			try {
				Files.deleteIfExists(delpath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Create new path
			String dir = "./images/Student/StudentPhoto/";
			Path path = Paths.get(dir);
			if (!Files.exists(path)) {
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Path filePath = path.resolve(fileName);
			//Save Img
			try {
				InputStream inputStream = multipartFile.getInputStream();
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}

			st.setImg(fileName);
			studentJpa.save(st);
		}
		return "redirect:/admin/studentDetailView/"+st.getId();
	}

	@GetMapping("/admin/studentMarking")
	public String studentMarking(Model model, @Param("bName") String bName, @Param("bNum") String bNum) {
		String key = bName + "-" + bNum;
		System.out.println("key : " + key);
		List<StudentMarkingForm> stmlist = new ArrayList<StudentMarkingForm>();
		List<StudentMarkingForm> updateList = new ArrayList<StudentMarkingForm>();

		List<Batch> blist = batchJpa.getAllBatch();

		for (int i = 0; i < blist.size(); i++) {
			System.out.println("blist : " + blist.get(i).getBatchName());
		}

		for (Batch b : blist) {
			if (b.getBatchName().equals(key)) {
				stmlist = studentMarking.findMarkingByBatchName(key);
				System.out.println("stmlists : " + stmlist.toString());
				break;
			}

		}
		if (stmlist.size() > 0) {
			for (int i = 0; i < stmlist.size(); i++) {
				StudentMarking marking = null;

				marking = studentMarking.findByStudentBatchId(stmlist.get(i).getStudentBatchId());
				if (marking == null) {
					marking = new StudentMarking();
					marking.setAssignmentMark(stmlist.get(i).getAssignmentMark());
					marking.setAttendanceMark(stmlist.get(i).getAttendanceMark().intValue());
					marking.setMidTermMark(stmlist.get(i).getMidTermMark());
					marking.setFinalMark(stmlist.get(i).getFinalMark());
					marking.setProjectMark(stmlist.get(i).getProjectMark());
					StudentBatch sb = new StudentBatch();
					sb.setId(stmlist.get(i).getStudentBatchId());
					marking.setStudentBatch(sb);
					marking.setGrade("No Grade");

					studentMarking.save(marking);
				} else {
					// stmlist.clear();
					StudentMarkingForm stmForm = new StudentMarkingForm(marking.getStudentBatch().getId(),
							marking.getStudentBatch().getStudent().getStid(),
							marking.getStudentBatch().getStudent().getName(),
							marking.getStudentBatch().getBatch().getBatchName(), marking.getGrade(),
							marking.getAttendanceMark().longValue(), marking.getAssignmentMark(),
							marking.getMidTermMark(), marking.getFinalMark(), marking.getProjectMark());
					updateList.add(stmForm);

				}

			}
		}

		model.addAttribute("courses", courseJpa.selectAll());
		if (updateList.size() != 0) {
			stmlist.clear();
			stmlist.addAll(updateList);
		}
		model.addAttribute("list", stmlist);
		model.addAttribute("key", key);
		model.addAttribute("studentMark", new StudentMarking());
		return "admin/Student/studentMarking";
	}

	@PostMapping("/studentMarkingUpdate/{id}")
	public String updateMarking(@PathVariable("id") Long id, @ModelAttribute("studentMark") @Valid StudentMarking mark,
			BindingResult bindingResult, Model model, @Param("key") String key) {
		if (bindingResult.hasErrors()) {
			return "admin/Student/studentMarking";
		}
		StudentMarking update = studentMarking.findByStudentBatchId(id);
		if (mark.getAssignmentMark() != null) {
			update.setAssignmentMark(mark.getAssignmentMark());
		}
		if (mark.getMidTermMark() != null) {
			update.setMidTermMark(mark.getMidTermMark());
		}
		if (mark.getFinalMark() != null) {
			update.setFinalMark(mark.getFinalMark());
		}
		if (mark.getProjectMark() != null) {
			update.setProjectMark(mark.getProjectMark());
		}
		if (mark.getAttendanceMark() != null) {
			update.setAttendanceMark(mark.getAttendanceMark());
		}
		int a, b, c, d, e;
		List<StudentBatch> sblist = studentBatchJpa.selectAll(update.getStudentBatch().getBatch());
		List<Attendace> totallist = attendanceJpa.selectByStudentBatchForMarking(sblist.get(0));
		double totalvalue = totallist.size();
		if (update.getAttendanceMark() == null) {
			a = 0;
		} else {
			a = update.getAttendanceMark();
		}
		if (update.getAssignmentMark() == null) {
			b = 0;
		} else {
			b = update.getAssignmentMark();
		}
		if (update.getMidTermMark() == null) {
			c = 0;
		} else {
			c = update.getMidTermMark();
		}
		if (update.getFinalMark() == null) {
			d = 0;
		} else {
			d = update.getFinalMark();
		}
		if (update.getProjectMark() == null) {
			e = 0;
		} else {
			e = update.getProjectMark();
		}
		int total = a + b + c + d + e;
		String bName = update.getStudentBatch().getBatch().getBatchName();
		String[] bN = bName.split("-");
		String cName = bN[0];
		List<Grate> glist = grateJpa.selectGrate(cName, total);
		if (glist.isEmpty()) {
			update.setGrade("No Grade");
		}
		for (Grate g : glist) {
			if (g.getSate().equals("D")) {
				update.setGrade("D");
			}
		}
		for (Grate g : glist) {
			if (g.getSate().equals("C")) {
				update.setGrade("C");
			}
		}
		for (Grate g : glist) {
			if (g.getSate().equals("B")) {
				update.setGrade("B");
			}
		}
		for (Grate g : glist) {
			if (g.getSate().equals("A")) {
				update.setGrade("A");
			}
		}
		for (Grate g : glist) {
			if (g.getSate().equals("A+")) {
				update.setGrade("A+");
			}
		}
		String[] k = key.split("-");
		String urlName = k[0];
		String urlNum = k[1];
		studentMarking.update(update);
		return "redirect:/admin/studentMarking?bName=" + urlName + "&bNum=" + urlNum;
	}

	@GetMapping("/admin/enquiryform")
	public String enquiryform(@ModelAttribute("register") Register register, Model model) {
		List<Course> clist = courseJpa.selectAll();
		model.addAttribute("clist", clist);
		return "admin/Student/enquiryform";
	}

	@PostMapping("/enquiryRegister")
	public String enquiryRegister(@ModelAttribute("register") Register register) {
		register.setStatus("Pending");
		registerJpa.save(register);
		return "redirect:/admin/enquiryform";
	}

	@GetMapping("/admin/changePassword")
	public String changePassword(Model model) {
		Student st = new Student();
		model.addAttribute("student", st);
		return "admin/Student/changePassword";
	}

	@GetMapping("/admin/searchStudent")
	public String searchStudent(@ModelAttribute("student") Student student, Model model, @Param("p1") String p1) {
		Student st = new Student();
		if (p1 != "") {
			st = studentJpa.findByStidOrName(p1);
			if(st==null) {
				model.addAttribute("ErrorM", "Student not found!");
				return "admin/Student/changePassword";
			}
			model.addAttribute("student", st);
			model.addAttribute("code", st.getStid());
		}

		return "admin/Student/changePassword";
	}

	@PostMapping("/admin/changePassword")
	public String changePassword(@ModelAttribute("student") Student student, Model model, @Param("p1") String p1) {
		if (student.getPassword() == "" && student.getCpassword() == "") {
			model.addAttribute("student", student);
			model.addAttribute("perror", "Password can not be blank!");
			model.addAttribute("cerror", "Confirm Password can not be blank!");
			return "admin/Student/changePassword";
		}
		if (student.getPassword().length() < 8) {
			model.addAttribute("student", student);
			model.addAttribute("perror", "Password must be at least 8 characters!");
			return "admin/Student/changePassword";
		}
		if (!student.getPassword().equals(student.getCpassword())) {
			model.addAttribute("student", student);
			model.addAttribute("cerror", "Confirm passward must be same with password!");
			return "admin/Student/changePassword";
		} else {
			String pass, cpass;
			pass = student.getPassword();
			cpass = student.getCpassword();
			student = studentJpa.findByStidOrName(student.getStid());
			student.setPassword(pass);
			student.setCpassword(cpass);
			studentJpa.update(student);
		}
		return "redirect:/admin/changePassword";

	}

}
