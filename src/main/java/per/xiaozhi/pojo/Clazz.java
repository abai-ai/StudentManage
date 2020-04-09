package per.xiaozhi.pojo;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:09
 *
 */

public class Clazz {
    private Integer id;
    private String name;
    private Integer number;
    private String introduction;
    private String teacherName;
    private String telephone;
    private String email;
    private String gradeName;


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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Clazz(Integer id, String name, Integer number, String introduction, String teacherName, String telephone, String email, String gradeName) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.introduction = introduction;
        this.teacherName = teacherName;
        this.telephone = telephone;
        this.email = email;
        this.gradeName = gradeName;
    }

    public Clazz(String name, Integer number, String introduction, String teacherName, String telephone, String email, String gradeName) {
        this.name = name;
        this.number = number;
        this.introduction = introduction;
        this.teacherName = teacherName;
        this.telephone = telephone;
        this.email = email;
        this.gradeName = gradeName;
    }

    public Clazz() {
    }
}
