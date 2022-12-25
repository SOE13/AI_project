package com.aceinspiration.trainingmanagementsystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aceinspiration.trainingmanagementsystem.formmodel.StudentProfileAttendanceCarry;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.CourseMarking;
import com.aceinspiration.trainingmanagementsystem.model.Student;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentMarking;
import com.aceinspiration.trainingmanagementsystem.model.StudentPayment;
import com.aceinspiration.trainingmanagementsystem.service.AttendanceJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseMarkingJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentMarkingJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentPaymentJpa;

@Controller
public class StudentProfileController {
	
	
	@Autowired
	StudentJpa studentJpa;
	

	@Autowired
	StudentBatchJpa studentBatchJpa;
	
	@Autowired
	StudentMarkingJpa studentMarkingJpa;
	
	@Autowired
	CourseMarkingJpa courseMarkingJpa;

	@Autowired
	StudentBatchJpa sbjpa;
	
	@Autowired
	AttendanceJpa attendanceJpa; 
	
	@Autowired
	StudentPaymentJpa paymentJpa;
	
	@GetMapping("/studentlogin")
	public String sLogin() {
		return "studentProfile/studentLogin";
	}
	
	
	@PostMapping("/studentlogin")
	public String login(Model model,@RequestParam("username")String name,@RequestParam("password")String pass) {
		List<Student> stlist=studentJpa.selectAll();
		for(Student st:stlist) {
			if(st.getStid().equals(name)) {
				Student student=studentJpa.selectForProfile(name);
				if(!pass.equals(student.getPassword())) {
					model.addAttribute("error", "Invalid ID and Password!");
					return "studentProfile/studentLogin";
				}else {
					model.addAttribute("student", student);
					 return "studentProfile/index";
				}
			}
		}
		return "studentProfile/studentLogin";
		
	}
	
	@GetMapping("/studentProfile/{id}")
	public String profile(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		model.addAttribute("student", student);
		 return "studentProfile/index";
	}
	@GetMapping("/editPhoto/{id}")
	public String editPhoto(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		model.addAttribute("student", student);
		return "studentProfile/editPhoto";
	}
	
	@PostMapping("/editPhoto/{id}")
	public String editPhotoUpdate(@PathVariable("id")Long id,Model model, @RequestParam("f") MultipartFile multipartFile) {
		Student student=studentJpa.selectOne(id);
//Path for delete
		String deldir = "." + student.getImg();
		Path delpath = Paths.get(deldir);
//Delete Image  		
		try {
			Files.deleteIfExists(delpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//Get file name
		String fileName = multipartFile.getOriginalFilename();

//Create path or dir
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

				// Save Image-----------------------+
		try {
			InputStream inputStream = multipartFile.getInputStream();
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		student.setImg(fileName);	
		studentJpa.update(student);
		model.addAttribute("student", student);
		return "studentProfile/index";
	}
	
	@GetMapping("/changePassword/{id}")
	public String changePassword(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		model.addAttribute("student", student);
		return "studentProfile/changePassword";
	}
	
	@PostMapping("/updatePass/{id}")
	public String updatePass(@PathVariable("id")Long id,Model model,@RequestParam("old")String old,@RequestParam("new")String ne,@RequestParam("cnew")String cnew) {
		Student student=studentJpa.selectOne(id);
		if(!student.getPassword().equals(old)) {
			model.addAttribute("student", student);
			model.addAttribute("e", "Invalid password!");
			return "studentProfile/changePassword";
		}
		if(!ne.equals(cnew)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "ConfirmPassword must be same with Password!");
			return "studentProfile/changePassword";
		}
		
		student.setPassword(ne);
		studentJpa.update(student);
		model.addAttribute("student", student);
		return "studentProfile/index";
	}
	
	@GetMapping("/qrCode/{id}")
	public String qrCode(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		model.addAttribute("student", student);
		return "studentProfile/qrCode";
	}
	
	@GetMapping("/attendance/{id}")
	public String attendance(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		List<StudentProfileAttendanceCarry> list=new ArrayList<StudentProfileAttendanceCarry>();
		List<StudentBatch> studentBatchList=sbjpa.getStudentBatchInfo(student.getStid());
		for(StudentBatch sb:studentBatchList){
			double totalvalue=0;
			double value=0;
			List<Attendace> totalList=attendanceJpa.selectByStudentBatchForMarking(sb);
			List<Attendace> valuelist=attendanceJpa.selectByStudentBatchForValue(sb);
			totalvalue=totalList.size();
			value=valuelist.size();
			double persent=value/totalvalue*100;
			StudentProfileAttendanceCarry spac=new StudentProfileAttendanceCarry(sb.getBatch().getBatchName(),(int) persent);
			list.add(spac);
		}
		model.addAttribute("list", list);
		model.addAttribute("student", student);
		return "studentProfile/attendance";
	}	
	
	@GetMapping("/attendanceDetail/{id}/{name}/{stid}")
	public String detail(@PathVariable("id")Long id,@PathVariable("name")String bname,@PathVariable("stid")String stid,Model model) {
		Student student=studentJpa.selectOne(id);
		
		List<StudentBatch> studentBatch=sbjpa.getStudentBatchInfo(stid);
		List<Attendace> list=new ArrayList<Attendace>();
		for(StudentBatch b:studentBatch) {
			List<Attendace> attList=attendanceJpa.selectByStudentBatch(b);
			for(Attendace attend:attList) {
				if(attend.getStudentBatch().getBatch().getBatchName().equals(bname)) {
					list.add(attend);
				}
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("student", student);
		return "studentProfile/attendanceDetail";
	}
	
	@GetMapping("/courseRating/{id}")
	public String rating(Model model, @PathVariable("id") Long id) {
		Student student = studentJpa.selectOne(id);
		model.addAttribute("student", student);
		Optional<Student> lstStudent = studentJpa.selectOneOp(id);
		List<StudentRelatedForm> lstBatch = studentBatchJpa.findBatchNamebyStudent(lstStudent.get().getStid());
		model.addAttribute("lstBatch", lstBatch);
		return "studentProfile/rating";
	}

	@GetMapping("student/courserating/{sid}/{bid}/{bName}/{rating}")
	public String courserating(Model model, @PathVariable("sid") Long sid, @PathVariable("bid") Long bid,
			@PathVariable("bName") String bName, @PathVariable("rating") String rateCount) {
		

		Student student = studentJpa.selectOne(sid);
		model.addAttribute("student", student);

		Batch batch = new Batch();
		batch.setId(bid);

		Student stud = new Student();
		stud.setId(sid);

		StudentBatch studBatch = studentBatchJpa.findByStudentIdAndBatchId(bid, sid);
		studBatch.setBatch(batch);
		studBatch.setStudent(stud);
		studBatch.setRating(Integer.parseInt(rateCount));

		studentBatchJpa.update(studBatch);
	
		return "studentProfile/rating";

	}
	
	@GetMapping("/payment/{id}")
	public String payment(@PathVariable("id")Long id,Model model) {
		Student student=studentJpa.selectOne(id);
		List<StudentBatch> studentBatch=sbjpa.getStudentBatchInfo(student.getStid());
		
		model.addAttribute("studentBatch",studentBatch);
		
		model.addAttribute("student", student);
		return "studentProfile/payment";
	}
	
	@GetMapping(path = "/studentProfile/getBatchName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentPayment>> getStudentPaymentListByStBatchId(Model model,
			@RequestParam(name = "batchName", required = true) long stBatchId) {
		List<StudentPayment> studentPaymentList = paymentJpa.getStudentPaymentByStBatchId(stBatchId);
		return new ResponseEntity<List<StudentPayment>>(studentPaymentList, HttpStatus.OK);
	}
}
