package lk.ijse.dep.sms.util;

public class PaymentTM {

    private String pno;
    private String batchNo;
    private String studentId;
    private double totalFee;

    public PaymentTM(String pno, String batchNo, String studentId, double totalFee) {
        this.pno = pno;
        this.batchNo = batchNo;
        this.studentId = studentId;
        this.totalFee = totalFee;
    }

    public PaymentTM() {
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
        return "PaymentTM{" +
                "pno='" + pno + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }
}
