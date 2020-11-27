package lk.ijse.dep.sms.entity;

public class PaymentPK {

    private String batchNo;
    private String studentId;

    public PaymentPK(String batchNo) {
        this.batchNo = batchNo;
    }

    public PaymentPK(String batchNo, String studentId) {
        this.batchNo = batchNo;
        this.studentId = studentId;
    }

    public PaymentPK() {
    }

    public String getBatchNo() { return batchNo; }

    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    @Override
    public String toString() {
        return "PaymentPK{" +
                "batchNo='" + batchNo + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
