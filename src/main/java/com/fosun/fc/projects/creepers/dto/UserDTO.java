package com.fosun.fc.projects.creepers.dto;

import java.util.Date;

public class UserDTO extends CreepersBaseDTO {

    /**
     * pojo for mongo test
     */
    private static final long serialVersionUID = 8279993922622840245L;
    private String userName;
    private String email;
    private String sex;
    private Date birthday;

    public UserDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
