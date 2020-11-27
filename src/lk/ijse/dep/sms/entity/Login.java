package lk.ijse.dep.sms.entity;

public class Login implements SuperEntity {

    private int userId;
    private String usertype;
    private String username;
    private String password;

    public Login(int userId, String usertype, String username, String password) {
        this.userId = userId;
        this.usertype = usertype;
        this.username = username;
        this.password = password;
    }

    public Login() {
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUsertype() { return usertype; }

    public void setUsertype(String usertype) { this.usertype = usertype; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", usertype='" + usertype + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
