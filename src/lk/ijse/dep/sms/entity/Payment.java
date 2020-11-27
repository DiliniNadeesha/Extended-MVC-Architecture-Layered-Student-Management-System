package lk.ijse.dep.sms.entity;

public class Payment implements SuperEntity {

    private PaymentPK paymentPK;
    private String pno;
    private double totalFee;

    public Payment(PaymentPK paymentPK, String pno, double totalFee) {
        this.paymentPK = paymentPK;
        this.pno = pno;
        this.totalFee = totalFee;
    }

    public Payment(String batchNo, String studentId, String pno, double totalFee) {
        this.paymentPK = new PaymentPK(batchNo, studentId);
        this.pno = pno;
        this.totalFee = totalFee;
    }

    public Payment() {
    }

    public PaymentPK getPaymentPK() { return paymentPK; }

    public void setPaymentPK(PaymentPK paymentPK) { this.paymentPK = paymentPK; }

    public String getPno() { return pno; }

    public void setPno(String pno) { this.pno = pno; }

    public double getTotalFee() { return totalFee; }

    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentPK=" + paymentPK +
                ", pno='" + pno + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }
}
