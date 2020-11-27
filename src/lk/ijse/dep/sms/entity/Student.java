package lk.ijse.dep.sms.entity;

public class Student implements SuperEntity {

    private String sid;
    private String sname;
    private String address;
    private String contactNo;
    private String email;

    public Student() {
    }

    public Student(String sid, String sname, String address, String contactNo, String email) {
        this.sid = sid;
        this.sname = sname;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getStudentId() { return sid; }

    public void setStudentId(String sid) { this.sid = sid; }

    public String getName() { return sname; }

    public void setName(String name) { this.sname = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getcontactNo() { return contactNo; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
