package com.aceinspiration.trainingmanagementsystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.ProfitLossForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.ReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.Attendace;
import com.aceinspiration.trainingmanagementsystem.model.Batch;
import com.aceinspiration.trainingmanagementsystem.model.Register;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;
import com.aceinspiration.trainingmanagementsystem.service.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@SessionAttributes({ "batchList" })
@Controller
public class ReportController {
	@Autowired
	AttendanceJpa attendanceJpa;
	@Autowired
	RegisterJpa registerJpa;
	@Autowired
	StudentBatchJpa studentBatchJpa;
	@Autowired
	BatchJpa batchJpa;
	@Autowired
	ReportJpa reportJpa;
	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/admin/enquiryReport")
	public String enquiryReport(Model model, HttpServletRequest request) {
		System.out.println("en");
		return enreportPage(request, model, 1, null, null, null);
	}

	@GetMapping("/enquiryReportPage/{pageNumber}")
	public String enreportPage(HttpServletRequest request, Model model, @PathVariable("pageNumber") int current,
			@Param("k1") String k1, @Param("k2") String k2, @Param("k3") String k3) {

		System.out.println("k1 : " + k1);
		System.out.println("k2 : " + k2);
		System.out.println("k3 : " + k3);
		Page<Register> page = registerJpa.selectForReport(current, k1, k2, k3);
		int totalPage = page.getTotalPages();
		List<Register> list = page.getContent();
		model.addAttribute("current", current);
		model.addAttribute("totalpage", totalPage);
		model.addAttribute("list", list);
		request.getServletContext().setAttribute("list", list);
		return "/admin/Report/enquiryReport";
	}

	@GetMapping("/admin/studentReport")
	public String studentReport(HttpServletRequest request, Model model) {
		System.out.println("student");
		return studreportPage(request, model, 1, null);
	}

	@PostMapping("/studentReportPage/{pageNumber}")
	public String studreportPage(HttpServletRequest request, Model model, @PathVariable("pageNumber") int current,
			@Param("p1") String p1) {
		System.out.println("p1 : " + p1);
		System.out.println("studreportPage");
		Page<StudentRelatedForm> page = reportJpa.findAllStudentOrderByStid(current, p1);
		int totalPage = page.getTotalPages();
		List<StudentRelatedForm> list = page.getContent();
		model.addAttribute("current", current);
		model.addAttribute("totalpage", totalPage);
		model.addAttribute("list", list);
		request.getServletContext().setAttribute("list", list);
		return "/admin/Report/studentReport";

	}

	@GetMapping("/admin/studentAttendanceReport")
	public String attendanceReport(Model model) {
		List<Batch> batchList = batchJpa.getAllBatch();
		model.addAttribute("batchList", batchList);
		return "/admin/Report/studentAttendanceReport";
	}

	@PostMapping("/studentAttendanceReportPage")
	public String attendancereportPage(HttpServletRequest request, Model model,
	@Param("p2") String p2, @Param("p3") String p3, @Param("p1") String p1) {
		List<Date> dlist = new ArrayList<Date>();
		List<String> idlist = new ArrayList<String>();
		List<Attendace> alist = new ArrayList<Attendace>();
		List<List<Attendace>> list = new ArrayList<List<Attendace>>();
		List<ReportForm> rlist = new ArrayList<ReportForm>();
		List<StudentBatch> stlist = new ArrayList<StudentBatch>();
	
		Map<String, List<Attendace>> report = new LinkedHashMap<String, List<Attendace>>();
		List<Batch> blist = new ArrayList<Batch>();
		 if (p2 != "") {
			if(p1!="" && p3!="") {
				dlist = reportJpa.findDateByBatchName(p2,p1, p3);
				System.out.println(dlist);
				System.out.println("dlist : " + dlist.size());
				
				
			}else {
				dlist = reportJpa.findDateByBatchName(p2);
				System.out.println(dlist);
				System.out.println("dlist : " + dlist.size());
				
				
			}

			Page<Batch> page = batchJpa.findAll(1, p2);

			blist = page.getContent();
			

			for (Batch b : blist) {
				System.out.println("b : " + b.getBatchName());
				stlist = studentBatchJpa.selectAll(b);
				System.out.println("stlist : " + stlist.size());

			}
		}

		for (StudentBatch sb : stlist) {
			
			List<Attendace> al = new ArrayList<Attendace>();
			if(p1!="" && p3!="") {
				al = attendanceJpa.selectByStudentBatch(sb, p1, p3);
				System.out.println("if al : "+al.size());
			}else {
				al = attendanceJpa.selectByStudentBatch(sb);
				System.out.println("else al : "+al.size());
			}
			
			
			System.out.println("al size : "+al.size());
			report.put(al.get(0).getStudentBatch().getStudent().getName(), al);
			idlist.add(al.get(0).getStudentBatch().getStudent().getStid());

			
		}

		for (Entry entry : report.entrySet()) {
			List<Attendace> value = (List<Attendace>) entry.getValue();
			list.add(value);

		}
		

		
		for (int i = 0; i < list.size(); i++) {
			ReportForm rform = new ReportForm();
			rform.setId(list.get(i).get(i).getStudentBatch().getStudent().getStid());
			rform.setName(list.get(i).get(i).getStudentBatch().getStudent().getName());
			rform.setBatchName(list.get(i).get(i).getStudentBatch().getBatch().getBatchName());
			

			List<Attendace> at = list.get(i);
			
			int present=0;
			for (int j = 0; j < at.size(); j++) {
				
				String result = at.get(j).getStatus();
				if(result.equals("Present")) {
					present++;
				}
				switch (j) {
				case 0:
					rform.setStatus1(result);
					break;

				case 1:
					rform.setStatus2(result);
					break;

				case 2:
					rform.setStatus3(result);
					break;
				case 3:
					rform.setStatus4(result);
					break;

				case 4:
					rform.setStatus5(result);
					break;

				case 5:
					rform.setStatus6(result);
					break;

				case 6:
					rform.setStatus7(result);
					break;

				case 7:
					rform.setStatus8(result);
					break;

				case 8:
					rform.setStatus9(result);
					break;

				case 9:
					rform.setStatus10(result);
					break;

				case 10:
					rform.setStatus11(result);
					break;

				case 11:
					rform.setStatus12(result);
					break;

				case 12:
					rform.setStatus13(result);
					break;

				case 13:
					rform.setStatus14(result);
					break;

				case 14:
					rform.setStatus15(result);
					break;

				case 15:
					rform.setStatus16(result);
					break;

				case 16:
					rform.setStatus17(result);
					break;

				case 17:
					rform.setStatus18(result);
					break;

				case 18:
					rform.setStatus19(result);
					break;

				case 19:
					rform.setStatus20(result);
					break;

				case 20:
					rform.setStatus21(result);
					break;

				case 21:
					rform.setStatus22(result);
					break;

				case 22:
					rform.setStatus23(result);
					break;

				case 23:
					rform.setStatus24(result);
					break;

				case 24:
					rform.setStatus25(result);
					break;

				case 25:
					rform.setStatus26(result);
					break;

				case 26:
					rform.setStatus27(result);
					break;

				case 27:
					rform.setStatus28(result);
					break;

				case 28:
					rform.setStatus29(result);
					break;

				case 29:
					rform.setStatus30(result);
					break;

				case 30:
					rform.setStatus31(result);
					break;
					
				case 31:
					rform.setStatus32(result);
					break;

				case 32:
					rform.setStatus33(result);
					break;
				}
				
				rform.setTotalAttendance(dlist.size());
				rform.setStudentAttendance(present);
				rform.setPercentage(new DecimalFormat("0").format(((double)present/dlist.size()*100))+" %");
				if(present!=0)
				rform.setMarks(20*(dlist.size()/present));
				
			}
			
			rlist.add(rform);
		}
		
		
		
		
		model.addAttribute("dlist", dlist);
		model.addAttribute("alist", report);
		model.addAttribute("idlist", idlist);	
		model.addAttribute("rlist", rlist);	
		request.getServletContext().setAttribute("list", dlist);

		request.getServletContext().setAttribute("alist", rlist);

		
		return "/admin/Report/studentAttendanceReport";

	}

	@GetMapping("/admin/studentPaymentReport")
	public String studentPaymentReport(Model model) {
		List<Batch> batchList = batchJpa.getAllBatch();
		model.addAttribute("batchList", batchList);
		return "/admin/Report/studentPaymentReport";
	}

	@PostMapping("/studentPaymentReportPage")
	public String payreportPage(Model model, @Param("p1") String p1, @Param("p2") String p2, @Param("p3") String p3,
			@Param("p4") String p4, HttpServletRequest request) throws JRException, IOException {
		List<PaymentReportForm> list = new ArrayList<PaymentReportForm>();
		
		if (p1 != "") {
			System.out.println("if");
			list = reportJpa.findPaymentInfoByStudId(p1);

		}

		else if (p2 != "") {
			System.out.println("else if");
			list = reportJpa.findPaymentInfoByBatch(p2);

		} else if (p3 != "" && p4 != "") {
			list = reportJpa.findPaymentInfoByDate(p3, p4);

		}

		

		model.addAttribute("lstPay", list);
		request.getServletContext().setAttribute("list", list);
		
		return "/admin/Report/studentPaymentReport";

	}

	@SuppressWarnings("deprecation")
	@GetMapping("/generatePaymentReport/{name}")
	public void generatePaymentReport(@PathVariable("name") String name, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {

		

		JRBeanCollectionDataSource source = null;
		String path = null;
		JasperReport jasperReport = null;

		// Parameters for report
		Map<String, Object> parameters = new HashMap<String, Object>();

		String sheetName="";
		
		if (name.equals("enquiry")) {
			List<Register> list = (List<Register>) request.getServletContext().getAttribute("list");
			source = new JRBeanCollectionDataSource(list);
			sheetName="enquiry";
			

		} else if (name.equals("student")) {
			List<StudentRelatedForm> list = (List<StudentRelatedForm>) request.getServletContext().getAttribute("list");
			source = new JRBeanCollectionDataSource(list);
		
			sheetName="student";

		} else if (name.equals("payment")) {
			List<PaymentReportForm> list = (List<PaymentReportForm>) request.getServletContext().getAttribute("list");
			source = new JRBeanCollectionDataSource(list);
			
			
			
			
			
			
			
			sheetName="payment";

		} else if (name.equals("profitloss")) {
			List<ProfitLossForm> list = (List<ProfitLossForm>) request.getServletContext().getAttribute("list");
			source = new JRBeanCollectionDataSource(list);
			
			sheetName="profitloss";

		} else if (name.equals("attendance")) {
			List<String> lstStatus = new ArrayList<String>();
			List<ReportForm> list = new ArrayList<ReportForm>();
			List<Date> list1 = (List<Date>) request.getServletContext().getAttribute("list");
			
			list = (List<ReportForm>) request.getServletContext().getAttribute("alist");

			for (int i = 0; i < list1.size(); i++) {
				
				parameters.put("date" + i, list1.get(i).toString());
			}

			
			
			sheetName=list.get(0).getBatchName();
			source = new JRBeanCollectionDataSource(list);

		

		}

		path = resourceLoader.getResource("classpath:report/" + name + ".jrxml").getURI().getPath();
		jasperReport = JasperCompileManager.compileReport(path);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, source);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + name + ".xls"); // final OutputStream
																							// outputStream =
		response.getOutputStream();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, name + ".xls");
		exporterXLS.setParameter(JRXlsExporterParameter.SHEET_NAMES,  new String[] {sheetName});
		exporterXLS.exportReport();

		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.write(byteArrayOutputStream.toByteArray());
		servletOutputStream.flush();

	}

	@GetMapping("/admin/profitAndLossReport")
	public String profitAndLossReport(Model model) {
		List<Batch> batchList = batchJpa.getAllBatch();
		model.addAttribute("batchList", batchList);
		return "/admin/Report/profitAndLossReport";
	}

	@GetMapping("/profitlossReportPage")
	public String profitlossPage(HttpServletRequest request, Model model, @Param("p1") String p1, @Param("p2") String p2, @Param("p3") String p3) {
		List<ProfitLossForm> list = reportJpa.findProfitLoss(p1,p2,p3);
		
		
		model.addAttribute("list", list);
		request.getServletContext().setAttribute("list", list);
		return "/admin/Report/profitAndLossReport";
	}

}
