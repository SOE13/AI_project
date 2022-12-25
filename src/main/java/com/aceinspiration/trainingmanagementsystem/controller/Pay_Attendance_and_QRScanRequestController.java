package com.aceinspiration.trainingmanagementsystem.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.service.AttendanceJpa;
import com.aceinspiration.trainingmanagementsystem.service.BatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;

@RestController
public class Pay_Attendance_and_QRScanRequestController {

	@Autowired
	private AttendanceJpa attendanceJpa;
	@Autowired
	private StudentBatchJpa studentBatchJpa;
	@Autowired
	private BatchJpa batchJpa;

	@GetMapping("/qrscanner")
	public void QrScan(@Param("result") String result) {
		java.util.Date s = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		;
		Date date = Date.valueOf(s.toString());
		StudentBatch sb = studentBatchJpa.selectByStudent(Long.valueOf(result.substring(result.length() - 1))).get(0);
		Attendace oldattenc = attendanceJpa.findStudentBatchDate(sb, date.toString());
		if (oldattenc == null) {
			Attendace attendace = new Attendace(sb, "Present", date);
			attendanceJpa.save(attendace);
		} else {
			oldattenc.setStatus("Present");
			attendanceJpa.save(oldattenc);
		}
	}

	@GetMapping("/absent")
	public void absent(@Param("batch") String batch, @Param("name") String name, @Param("date") String date) {
		Date d = Date.valueOf(date.toString());
		Page<Batch> page = batchJpa.findAll(1, batch);
		List<Batch> blist = page.getContent();
		for (Batch b : blist) {
			List<StudentBatch> stlist = studentBatchJpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				if (sb.getStudent().getStid().equals(name)) {
					Attendace checkAtt = attendanceJpa.findStudentBatchDate(sb,date);
					if (checkAtt == null) {
						Attendace attendace = new Attendace(sb, "Absent", d);
						attendanceJpa.save(attendace);
					} else {
						checkAtt.setStatus("Absent");
						attendanceJpa.update(checkAtt);
					}
				}
			}
		}		
	}
	
	@GetMapping("/present")
	public void present(@Param("batch") String batch, @Param("name") String name, @Param("date") String date) {
		Date d = Date.valueOf(date.toString());
		Page<Batch> page = batchJpa.findAll(1, batch);
		List<Batch> blist = page.getContent();
		for (Batch b : blist) {
			List<StudentBatch> stlist = studentBatchJpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				if (sb.getStudent().getStid().equals(name)) {
					Attendace checkAtt = attendanceJpa.findStudentBatchDate(sb,date);
					if (checkAtt == null) {
						Attendace attendace = new Attendace(sb, "Present", d);
						attendanceJpa.save(attendace);
					} else {
						checkAtt.setStatus("Present");
						attendanceJpa.update(checkAtt);
					}
				}
			}
		}		
	}
	
	@GetMapping("/leave")
	public void leave(@Param("batch") String batch, @Param("name") String name, @Param("date") String date) {
		Date d = Date.valueOf(date.toString());
		Page<Batch> page = batchJpa.findAll(1, batch);
		List<Batch> blist = page.getContent();
		for (Batch b : blist) {
			List<StudentBatch> stlist = studentBatchJpa.selectAll(b);
			for (StudentBatch sb : stlist) {
				if (sb.getStudent().getStid().equals(name)) {
					Attendace checkAtt = attendanceJpa.findStudentBatchDate(sb,date);
					if (checkAtt == null) {
						Attendace attendace = new Attendace(sb, "Leave", d);
						attendanceJpa.save(attendace);
					} else {
						checkAtt.setStatus("Leave");
						attendanceJpa.update(checkAtt);
					}
				}
			}
		}		
	}
	
	@GetMapping("/update")
	public void aupdate(@Param("id") Long id,@Param("status") String status) {
		Attendace attend = attendanceJpa.selectOne(id);
		attend.setStatus(status);
		attendanceJpa.update(attend);
	}

}
