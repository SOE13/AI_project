package com.aceinspiration.trainingmanagementsystem.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.ProfitLossForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.repository.ReportRepository;

@Service
@Transactional
public class ReportJpa {

	@Autowired
	private ReportRepository reportRepository;

	public Page<StudentRelatedForm> findAllStudentOrderByStid(int i, String p1) {
		Pageable pageable = PageRequest.of(i - 1, 10);
		if (p1 != null) {
			return reportRepository.findAllStudentOrderByStid(p1, pageable);
		}
		return reportRepository.findAllStudentOrderByStid(pageable);
	}

	public List<ProfitLossForm> findProfitLoss(String p1, String p2, String p3) {
		System.out.println("p1 ..... : " + p1);
		if (p2 != "" && p3 != "") {
			System.out.println("if");
			return reportRepository.findProfitLoss(p2,p3);
		}else {
			return reportRepository.findProfitLoss(p1);
		}
		

	}

	public List<PaymentReportForm> findPaymentInfoByStudId(String studId) {

		return reportRepository.findPaymentInfoByStudId(studId);
	}

	public List<PaymentReportForm> findPaymentInfoByBatch(String batchName) {

		return reportRepository.findPaymentInfoByBatch(batchName);
	}

	public List<PaymentReportForm> findPaymentInfoByDate(String date1, String date2) {

		return reportRepository.findPaymentInfoByDate(date1, date2);
	}
	
	public List<Date> findDateByBatchName(String batchName){
		return reportRepository.findDateByBatchName(batchName);
	}
	
	public List<Date> findDateByBatchName(String batchName, String date1, String date2){
		return reportRepository.findDateByBatchName(batchName, Date.valueOf(date1), Date.valueOf(date2));
	}

}
