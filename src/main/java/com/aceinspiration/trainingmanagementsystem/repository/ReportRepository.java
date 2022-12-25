package com.aceinspiration.trainingmanagementsystem.repository;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.ProfitLossForm;
import com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm;
import com.aceinspiration.trainingmanagementsystem.model.StudentBatch;

@Repository
public interface ReportRepository extends JpaRepository<StudentBatch, Long> {

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(s.stid, s.name, b.batchName, sm.grade) from StudentBatch sb join sb.student s on s.id = sb.student join sb.batch b on b.id = sb.batch\r\n"
			+ "join StudentMarking sm on sm.studentBatch=s.id order by s.stid desc")
	public Page<StudentRelatedForm> findAllStudentOrderByStid(Pageable pageable);

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.StudentRelatedForm(s.stid, s.name, b.batchName, sm.grade) from StudentBatch sb join sb.student s on s.id = sb.student join sb.batch b on b.id = sb.batch\r\n"
			+ "join StudentMarking sm on sm.studentBatch=s.id where s.stid=?1 or b.batchName=?1")
	public Page<StudentRelatedForm> findAllStudentOrderByStid(String p1, Pageable pageable);

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.ProfitLossForm(b.batchName, t.name,count(sb.student),sum(sb.actualAmount),  b.teacherFee, b.supervisorFee, b.judgeFee,b.otherExpense)\r\n"
			+ "from StudentBatch sb join sb.batch b on sb.batch=b.id join Teacher t on t.id = b.teacher.id where b.batchName=?1 \r\n"
			+ "group by b.batchName")
	public List<ProfitLossForm> findProfitLoss(String p1);

	@Query("select new com.aceinspiration.trainingmanagementsystem.formmodel.ProfitLossForm(b.batchName, t.name,count(sb.student),sum(sb.actualAmount),  b.teacherFee, b.supervisorFee, b.judgeFee,b.otherExpense)\r\n"
			+ "from StudentBatch sb join sb.batch b on sb.batch=b.id join Teacher t on t.id = b.teacher.id where b.actualStartDate between ?1 and  ?2 \r\n"
			+ "group by b.batchName")
	public List<ProfitLossForm> findProfitLoss(String date1, String date2);

	@Query("SELECT new com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm( sb.id,s.stid, s.name, b.batchName, concat(b.actualStartDate, ' - ',b.actualEndDate) as Start_End ,c.duration, c.fee,sb.discountAmount, (c.fee-sb.discountAmount)as AfterDiscount, \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidDate else '' end) as FirstDate ,  \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.voucherNo else '' end) as FirstVrNo ,    \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidAmount else '' end) as FirstAmount,\r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidDate else '' end) as SecondDate ,  \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.voucherNo else '' end) as SecondVrNo ,    \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidAmount else '' end) as SecondAmount,\r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidDate else '' end) as ThirdDate ,  \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.voucherNo else '' end) as ThirdVrNo ,    \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidAmount else '' end) as ThirdAmount,\r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidDate else '' end) as FourthDate ,  \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.voucherNo else '' end) as FourthVrNo ,    \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidAmount else '' end) as FourthAmount,\r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidDate else '' end) as FifthDate,  \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.voucherNo else '' end) as FifthVrNo ,    \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidAmount else '' end) as FifthAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then 0 else sum(sp.paidAmount) end) as TotalAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then (c.fee-sb.discountAmount) else ((c.fee-sb.discountAmount)- sum(sp.paidAmount)) end) as RemainAmount,\r\n"
			+ "  d.discountName) from StudentBatch sb join sb.student s on s.id = sb.student\r\n"
			+ "join sb.batch b on b.id = sb.batch join Course c on c.id = b.course join DiscountType d\r\n" +

			"on d.id = sb.discountType left join StudentPayment sp on sp.studentBatch = sb.id \r\n"
			+ "where s.stid=?1 group by b.batchName")
	public List<PaymentReportForm> findPaymentInfoByStudId(String studId);

	@Query("SELECT new com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm( sb.id,s.stid, s.name, b.batchName, concat(b.actualStartDate, ' - ',b.actualEndDate) as Start_End ,c.duration, c.fee,sb.discountAmount, (c.fee-sb.discountAmount)as AfterDiscount, \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidDate else '' end) as FirstDate ,  \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.voucherNo else '' end) as FirstVrNo ,    \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidAmount else '' end) as FirstAmount,\r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidDate else '' end) as SecondDate ,  \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.voucherNo else '' end) as SecondVrNo ,    \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidAmount else '' end) as SecondAmount,\r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidDate else '' end) as ThirdDate ,  \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.voucherNo else '' end) as ThirdVrNo ,    \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidAmount else '' end) as ThirdAmount,\r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidDate else '' end) as FourthDate ,  \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.voucherNo else '' end) as FourthVrNo ,    \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidAmount else '' end) as FourthAmount,\r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidDate else '' end) as FifthDate,  \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.voucherNo else '' end) as FifthVrNo ,    \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidAmount else '' end) as FifthAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then 0 else sum(sp.paidAmount) end) as TotalAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then (c.fee-sb.discountAmount) else ((c.fee-sb.discountAmount)- sum(sp.paidAmount)) end) as RemainAmount,\r\n"
			+ "  d.discountName) from StudentBatch sb join sb.student s on s.id = sb.student\r\n"
			+ "join sb.batch b on b.id = sb.batch join Course c on c.id = b.course join DiscountType d\r\n" +


			"on d.id = sb.discountType left join StudentPayment sp on sp.studentBatch = sb.id\r\n"
			+ "where b.batchName =?1 group by s.stid")
	public List<PaymentReportForm> findPaymentInfoByBatch(String batchName);

	
	@Query("SELECT new com.aceinspiration.trainingmanagementsystem.formmodel.PaymentReportForm(sb.id,s.stid, s.name, b.batchName, concat(b.actualStartDate, ' - ',b.actualEndDate) as Start_End ,c.duration, c.fee,sb.discountAmount, (c.fee-sb.discountAmount)as AfterDiscount, \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidDate else '' end) as FirstDate ,  \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.voucherNo else '' end) as FirstVrNo ,    \r\n"
			+ "max(case when sp.installment = '1st Installment' then sp.paidAmount else '' end) as FirstAmount,\r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidDate else '' end) as SecondDate ,  \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.voucherNo else '' end) as SecondVrNo ,    \r\n"
			+ "max(case when sp.installment = '2nd Installment' then sp.paidAmount else '' end) as SecondAmount,\r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidDate else '' end) as ThirdDate ,  \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.voucherNo else '' end) as ThirdVrNo ,    \r\n"
			+ "max(case when sp.installment = '3rd Installment' then sp.paidAmount else '' end) as ThirdAmount,\r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidDate else '' end) as FourthDate ,  \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.voucherNo else '' end) as FourthVrNo ,    \r\n"
			+ "max(case when sp.installment = '4th Installment' then sp.paidAmount else '' end) as FourthAmount,\r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidDate else '' end) as FifthDate,  \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.voucherNo else '' end) as FifthVrNo ,    \r\n"
			+ "max(case when sp.installment = '5th Installment' then sp.paidAmount else '' end) as FifthAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then 0 else sum(sp.paidAmount) end) as TotalAmount,\r\n"
			+ "(case when sum(sp.paidAmount) is null then (c.fee-sb.discountAmount) else ((c.fee-sb.discountAmount)- sum(sp.paidAmount)) end) as RemainAmount,\r\n"
			+ "  d.discountName) from StudentBatch sb join sb.student s on s.id = sb.student\r\n"
			+ "join sb.batch b on b.id = sb.batch join Course c on c.id = b.course join DiscountType d\r\n" +


			"on d.id = sb.discountType left join StudentPayment sp on sp.studentBatch = sb.id\r\n"
			+ "where b.actualStartDate between ?1 and  ?2 group by sb.id order by b.actualStartDate desc")
	
	public List<PaymentReportForm> findPaymentInfoByDate(String date1, String date2);

	@Query("select distinct att.date from StudentBatch sb join sb.batch b on b.id = sb.batch join Attendace att on att.studentBatch = sb.id\r\n"
			+ "where b.batchName = ?1 order by att.date")
	public List<Date> findDateByBatchName(String batchName);

	@Query("select distinct att.date from StudentBatch sb join sb.batch b on b.id = sb.batch join Attendace att on att.studentBatch = sb.id\r\n"
			+ "where b.batchName = ?1 and att.date between ?2 and ?3 order by att.date")
	public List<Date> findDateByBatchName(String batchName, Date date1, Date date2);

}
