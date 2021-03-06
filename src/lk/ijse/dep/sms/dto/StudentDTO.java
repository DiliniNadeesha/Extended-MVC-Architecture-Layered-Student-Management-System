package lk.ijse.dep.sms.dto;

public class StudentDTO {

    private String sid;
    private String sname;
    private String address;
    private String contactNo;
    private String email;

    public StudentDTO(String sid, String sname, String address, String contactNo, String email) {
        this.sid = sid;
        this.sname = sname;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
    }

    public StudentDTO() {
    }

    public String getSid() { return sid; }

    public void setSid(String sid) { this.sid = sid; }

    public String getSname() { return sname; }

    public void setSname(String sname) { this.sname = sname; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getContactNo() { return contactNo; }

    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
