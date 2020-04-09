package per.xiaozhi.pojo;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:15
 *
 */

public class Student {
    private Integer id;
    private String sno;
    private String name;
    private String gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String introduction;
    private String portraitPath;
    private String clazzName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Student(String sno, String name, String gender, String password, String email, String telephone, String address, String introduction, String portraitPath, String clazzName) {
        this.sno = sno;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.introduction = introduction;
        this.portraitPath = portraitPath;
        this.clazzName = clazzName;
    }

    public Student(Integer id, String sno, String name, String gender, String password, String email, String telephone, String address, String introduction, String portraitPath, String clazzName) {
        this.id = id;
        this.sno = sno;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.introduction = introduction;
        this.portraitPath = portraitPath;
        this.clazzName = clazzName;
    }

    public Student() {
    }
}
