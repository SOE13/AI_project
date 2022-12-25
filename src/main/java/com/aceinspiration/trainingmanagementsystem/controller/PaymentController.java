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


import com.aceinspiration.trainingmanagementsystem.formmodel.StudentPaymentForm;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.model.StudentPayment;
import com.aceinspiration.trainingmanagementsystem.service.StudentBatchJpa;
import com.aceinspiration.trainingmanagementsystem.service.StudentPaymentJpa;

@Controller
public class PaymentController {

	@Autowired
	private StudentPaymentJpa stPaymentService;

	@Autowired
	StudentBatchJpa StuBatchService;

	@RequestMapping("/admin/studentPayment")
	public ModelAndView studentBatch(Model model, StudentPaymentForm studentPaymentForm,
			@RequestParam(name = "stCode", required = false) String stCode,
			@RequestParam(name = "stbId", required = false) String batchId,
			@RequestParam(name = "searchCode", required = false) String searchCode) {
		System.out.println(stCode);
		if (stCode == "") {
			model.addAttribute("ErrorM", "No data found");
			return new ModelAndView("admin/Student/studentPayment");
		}
		List<StudentBatch> studentBatchs = new ArrayList<>();
		if (stCode != null) {
			studentBatchs = StuBatchService.getStudentBatchInfo(stCode);
			if(studentBatchs.size()==0) {
				model.addAttribute("ErrorM", "No data found");
				return new ModelAndView("admin/Student/studentPayment");
			}
			model.addAttribute("studentBatchs", studentBatchs);
			model.addAttribute("keyword", stCode);
		}

		if (batchId != null && searchCode != null) {
			long batch = 0;
			Optional<StudentBatch> studentBatchIdOption = StuBatchService.getStudentBatchById(Long.parseLong(batchId));
			if (studentBatchIdOption.isPresent()) {
				batch = studentBatchIdOption.get().getId();
			}
			System.out.println("This is " + searchCode);
			studentBatchs = StuBatchService.getStudentBatchInfo(searchCode);
			model.addAttribute("batchId", batch);
			model.addAttribute("studentBatchs", studentBatchs);
			model.addAttribute("keyword", searchCode);
			model.addAttribute("Message1", "Save Successfully");

		}

		// for (com.aceinspiration.trainingmanagementsystem.model.Batch batch : batches)
		// {
		// System.out.println(batch.getId() + " : " + batch.getName());
		// }

		return new ModelAndView("admin/Student/studentPayment");
	}

	@RequestMapping("/admin/addPayment")
	public String handleStudentBatch(@Valid StudentPaymentForm studentPaymentForm, BindingResult bindingResult,
			Model model) {

//		if (studentPaymentForm.getStudentbatch() == null || studentPaymentForm.getStudentBatchId() == "") {
//			return "admin/Student/studentPayment";
//		} 
		if (bindingResult.hasErrors()) {
			System.out.println("has error");
			String searchCode = studentPaymentForm.getSearchCode();
			long stbId = Long.parseLong(studentPaymentForm.getStudentBatchId());
			List<StudentBatch> studentBatchs = new ArrayList<>();
			studentBatchs = StuBatchService.getStudentBatchInfo(searchCode);
			model.addAttribute("studentPaymentForm", studentPaymentForm);
			model.addAttribute("studentBatchs", studentBatchs);
			model.addAttribute("keyword", searchCode);
			model.addAttribute("batchId", stbId);
			model.addAttribute("ErrorM", "Insert Fail!!");
			return "admin/Student/studentPayment";
		} else {
			long stbId = Long.parseLong(studentPaymentForm.getStudentBatchId());
			String searchCode = studentPaymentForm.getSearchCode();
			String paidDate = (studentPaymentForm.getPaidDate());
			String installment = (studentPaymentForm.getInstallment());
			String vouchNo = (studentPaymentForm.getVoucherNo());
			Double paidAmount = (studentPaymentForm.getPaidAmount());
			Double actualAmount = (studentPaymentForm.getActualAmount());
			List<StudentPayment> studentPaymentList = stPaymentService.getStudentPaymentByStBatchId(stbId);
			Double totalPayAmount = 0.0;

			for (StudentPayment studentPayment : studentPaymentList) {
				if (studentPayment.getRemainAmount() < paidAmount) {
					model.addAttribute("Message", "Paid Amount should be less than Remain Amount!!");
					List<StudentBatch> studentBatchs = new ArrayList<>();
					studentBatchs = StuBatchService.getStudentBatchInfo(searchCode);
					model.addAttribute("studentBatchs", studentBatchs);
					model.addAttribute("keyword", searchCode);
					model.addAttribute("studentPaymentForm", studentPaymentForm);
					model.addAttribute("batchId", stbId);
					model.addAttribute("ErrorM", "Insert Fail!!");
					return "admin/Student/studentPayment";
				}
				totalPayAmount += studentPayment.getPaidAmount();
			}
			Double remainAmount = actualAmount - (paidAmount + totalPayAmount);

			Optional<StudentBatch> studentBatchOptional = StuBatchService.getStudentBatchById(stbId);
			if (studentBatchOptional.isPresent()) {
				stPaymentService.saveStudentPayment(studentBatchOptional.get(), paidDate, installment, actualAmount,
						paidAmount, remainAmount, vouchNo);
			}

			return "redirect:/admin/studentPayment?searchCode=" + searchCode + "&&stbId=" + stbId;
		}
	}

	@GetMapping(path = "/admin/getStudentPaymentListByStBatchId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentPayment>> getStudentPaymentListByStBatchId(Model model,
			@RequestParam(name = "stBatchId", required = true) long stBatchId) {
		List<StudentPayment> studentPaymentList = stPaymentService.getStudentPaymentByStBatchId(stBatchId);
		return new ResponseEntity<List<StudentPayment>>(studentPaymentList, HttpStatus.OK);
	}

	// testing
	@RequestMapping("/admin/test")
	public ResponseEntity<StudentBatch> test(Model model) {
		Optional<StudentBatch> studentBatchs = StuBatchService.getStudentBatchById(1);
		if (studentBatchs.isPresent()) {
			return new ResponseEntity<StudentBatch>(studentBatchs.get(), HttpStatus.OK);
		}
		return null;
	}

}
