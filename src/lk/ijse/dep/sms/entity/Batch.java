package lk.ijse.dep.sms.entity;

import java.sql.Date;

public class Batch implements SuperEntity {

    private String bno;
    private String courseId;
    private String status;
    private double fee;
    private Date startDate;
    private Date closeDate;

    public Batch(String bno, String courseId, String status, double fee, Date startDate, Date closeDate) {
        this.bno = bno;
        this.courseId = courseId;
        this.status = status;
        this.fee = fee;
        this.startDate = startDate;
        this.closeDate = closeDate;
    }

    public Batch() {
    }

    public String getBno() { return bno; }

    public void setBno(String bno) { this.bno = bno; }

    public String getCourseId() { return courseId; }

    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public double getFee() { return fee; }

    public void setFee(double fee) { this.fee = fee; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getCloseDate() { return closeDate; }

    public void setCloseDate(Date closeDate) { this.closeDate = closeDate; }

    @Override
    public String toString() {
        return "Batch{" +
                "bno='" + bno + '\'' +
                ", courseId='" + courseId + '\'' +
                ", status='" + status + '\'' +
                ", fee=" + fee +
                ", startDate=" + startDate +
                ", closeDate=" + closeDate +
                '}';
    }
}
