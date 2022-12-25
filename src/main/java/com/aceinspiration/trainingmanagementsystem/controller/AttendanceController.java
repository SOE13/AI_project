package com.aceinspiration.trainingmanagementsystem.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.BatchCarry;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.service.AttendanceJpa;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.CourseJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentJpa;

@Controller
public class AttendanceController {

	@Autowired
	StudentBatchJpa sbjpa;

	@Autowired
	StudentJpa studentJpa;

	@Autowired
	BatchJpa jpa;

	@Autowired
	CourseJpa course;

	@Autowired
	AttendanceJpa attendanceJpa;
	
	@GetMapping("/admin/studentAttendanceQr")
	public String studentAttendanceWithQr() {	
		return "admin/Attendance/studentAttendanceWithQrScan";
	}
	@GetMapping("/admin/studentAttendance")
	public String studentAttendance(Model model) {
		model.addAttribute("bb", course.selectAll());
		return att(model, 1, null, null);
	}
	@GetMapping("/att/{pageNumber}")
	public String att(Model model, @PathVariable("pageNumber") int current, @Param("k1") String k1,
			@Param("k2") String k2) {
		String keyword = null;
		if (k1 != null && k2 != null) {
			keyword = k1 + "-" + k2;
		}
		Page<Batch> page = jpa.findAll(current, keyword);
		int totalpage = page.getTotalPages();

		List<Batch> blist = page.getContent();
		List<BatchCarry> list = new ArrayList<BatchCarry>();
		for (Batch b : blist) {
			List<StudentBatch> stlist = sbjpa.selectAll(b);
			BatchCarry bc = new BatchCarry(b.getBatchName(), stlist.size());
			list.add(bc);
		}
		model.addAttribute("bb", course.selectAll());
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("current", current);
		model.addAttribute("list", list);
		return "admin/Attendance/studentAttendance";
	}
	@GetMapping("/admin/studentAttendanceDetail")
	public String studentAttendanceDetail(Model model) {
		model.addAttribute("bb", course.selectAll());
		return attD(model, 1, null, null);
	}
	@GetMapping("/attD/{pageNumber}")
	public String attD(Model model, @PathVariable("pageNumber") int current, @Param("k1") String k1,
			@Param("k2") String k2) {
		String keyword = null;
		if (k1 != null && k2 != null) {
			keyword = k1 + "-" + k2;
		}
		Page<Batch> page = jpa.findAll(current, keyword);
		int totalpage = page.getTotalPages();

		List<Batch> blist = page.getContent();
		List<BatchCarry> list = new ArrayList<BatchCarry>();
		for (Batch b : blist) {
			List<StudentBatch> stlist = sbjpa.selectAll(b);
			BatchCarry bc = new BatchCarry(b.getBatchName(), stlist.size());
			list.add(bc);
		}
		model.addAttribute("bb", course.selectAll());
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("current", current);
		model.addAttribute("list", list);
		return "admin/Attendance/studentAttendanceDetail";
	}

	

	@GetMapping("/admin/studentAttendancePayView/{name}/{date}")
	public String studentAttendancePayView(@PathVariable("date") String today,@PathVariable("name") String keyword, Model model,@Param("data")String data) {
		List<Attendace> list = null;
		Page<Batch> page = jpa.findAll(1, keyword);
		List<Batch> blist = page.getContent();
		Set<Attendace> attenList = new LinkedHashSet<Attendace>();
		for (Batch b : blist) {
			List<StudentBatch> stlist = sbjpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				Attendace at = null;
				if(today.equals("today")) {
					java.util.Date s = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
					;
					Date date = Date.valueOf(s.toString());
					String d= date.toString();
					 at = attendanceJpa.findStudentBatchDate(sb,d);
					if (at == null) {
						at = new Attendace(sb, "Pending", date);
					}
				}else {
					Date date = Date.valueOf(today);
					list = attendanceJpa.selectByDate(date);
				}
				attenList.add(at);
			}
		}
		model.addAttribute("key", keyword);
		if(today.equals("today")) {
			model.addAttribute("list", attenList);
		}else {
			model.addAttribute("list",list );
		}
		
		return "admin/Attendance/studentAttendancePayView";
	}
	
	@GetMapping("/admin/studentAttendanceDetailView/{name}")
	public String studentAttendanceDetailView(@PathVariable("name") String keyword, Model model) {
		Page<Batch> page = jpa.findAll(1, keyword);
		List<Batch> blist = page.getContent();
		List<Attendace> alist = new ArrayList<Attendace>();
		for (Batch b : blist) {
			List<StudentBatch> stlist = sbjpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				List<Attendace> al = attendanceJpa.selectByStudentBatch(sb);
				alist.addAll(al);
			}
		}
		model.addAttribute("key", keyword);
		model.addAttribute("list", alist);
		return "admin/Attendance/studentAttendanceDetailView";
	}

	@GetMapping("/admin/studentAttendanceDetailView/searchDate")
	public String searchData(@Param("data") String data, @Param("key") String key, Model model) {
		Page<Batch> page = jpa.findAll(1, key);
		List<Batch> blist = page.getContent();
		Set<Attendace> attenList = new LinkedHashSet<Attendace>();
		for (Batch b : blist) {
			List<StudentBatch> stlist = sbjpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				Attendace at = null;
					Date date = Date.valueOf(data);
					String d= date.toString();
					 at = attendanceJpa.findStudentBatchDate(sb,d);
					if (at == null) {
						at = new Attendace(sb, "Pending", date);
					}
				attenList.add(at);
			}
		}		
		model.addAttribute("data", data);
		model.addAttribute("key", key);
		model.addAttribute("list", attenList);
		return "admin/Attendance/studentAttendanceDetailView";
		
	}

	@GetMapping("/admin/studentAttendanceDetailView/searchStudentId")
	public String searchStudentId(@Param("stId") String stId, @Param("key") String key, Model model) {
		List<StudentBatch> studentBatch = sbjpa.selectByStudent(Long.valueOf(stId.substring(stId.length() - 1)));
		List<Attendace> list = new ArrayList<Attendace>();
		for (StudentBatch b : studentBatch) {
			List<Attendace> attList = attendanceJpa.selectByStudentBatch(b);
			list.addAll(attList);
		}
		model.addAttribute("list", list);
		model.addAttribute("data", stId);
		model.addAttribute("key", key);
		return "admin/Attendance/studentAttendanceDetailView";
	}

}
