package per.xiaozhi.pojo;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/19-23:13
 *
 */

public class Grade {
    private Integer id;
    private String name;
    private String manager;
    private String email;
    private String telephone;
    private String introduction;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Grade(Integer id, String name, String manager, String email, String telephone, String introduction) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.email = email;
        this.telephone = telephone;
        this.introduction = introduction;
    }

    public Grade(String name, String manager, String email, String telephone, String introduction) {
        this.name = name;
        this.manager = manager;
        this.email = email;
        this.telephone = telephone;
        this.introduction = introduction;
    }

    public Grade() {
    }
}
