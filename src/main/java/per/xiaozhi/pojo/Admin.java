package per.xiaozhi.pojo;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-22:58
 *
 */

public class Admin {
    private Integer id;
    private String name;
    private String gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String portraitPath;

    public Admin() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public Admin(String name, String gender, String password, String email, String telephone, String address, String portraitPath) {
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.portraitPath = portraitPath;
    }

    public Admin(Integer id, String name, String gender, String email, String telephone, String address, String portraitPath) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.portraitPath = portraitPath;
    }
}
