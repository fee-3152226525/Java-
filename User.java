package StudentManagementSystem;

public class User {
    private String username;
    private String password;
    private String idcardnum;
    private String telnum;

    public User() {
    }

    public User(String username, String password, String idcardnum, String telnum) {
        this.username = username;
        this.password = password;
        this.idcardnum = idcardnum;
        this.telnum = telnum;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return idcardnum
     */
    public String getIdcardnum() {
        return idcardnum;
    }

    /**
     * 设置
     * @param idcardnum
     */
    public void setIdcardnum(String idcardnum) {
        this.idcardnum = idcardnum;
    }

    /**
     * 获取
     * @return telnum
     */
    public String getTelnum() {
        return telnum;
    }

    /**
     * 设置
     * @param telnum
     */
    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + ", idcardnum = " + idcardnum + ", telnum = " + telnum + "}";
    }
}
