package com.bshostak.payments.db.entity;

/**
 * User entity.
 *
 * @author B.Shostak
 *
 */

public class User extends Entity {

    private static final long serialVersionUID = -7952110221452069801L;

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String tel;
    private int userStatusId;
    private int roleId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password + ", firstName=" + firstName
                + ", secondName=" + secondName + ", email=" + email + ", tel=" + tel
                + ", userStatusId=" + userStatusId + " , roleId" + roleId + " ]";
    }

}
