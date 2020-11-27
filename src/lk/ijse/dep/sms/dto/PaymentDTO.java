package lk.ijse.dep.sms.dto;

import lk.ijse.dep.sms.entity.PaymentPK;

public class PaymentDTO {

    private String pno;
    private String batchNo;
    private String studentId;
    private double totalFee;

    public PaymentDTO(String pno, String batchNo, String studentId, double totalFee) {
        this.pno = pno;
        this.batchNo = batchNo;
        this.studentId = studentId;
        this.totalFee = totalFee;
    }

    public PaymentDTO(String pno,PaymentPK paymentPK, double totalFee) {
        this.pno=pno;
        this.batchNo=paymentPK.getBatchNo();
        this.batchNo=paymentPK.getStudentId();
        this.totalFee=totalFee;
    }

    public PaymentDTO() {
    }

    public String getPno() { return pno; }

    public void setPno(String pno) { this.pno = pno; }

    public String getBatchNo() { return batchNo; }

    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    public double getTotalFee() { return totalFee; }

    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "pno='" + pno + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }
}
