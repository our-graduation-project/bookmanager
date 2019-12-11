package cn.hnist.bookmanager.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class User implements Serializable {

    public User(Integer userId, String userName, String mailbox, String userPwd, String phone, Integer sex, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.mailbox = mailbox;
        this.userPwd = userPwd;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
    }


    public User(Integer userId, String userName, String mailbox, String userPwd, String phone, Integer sex) {
        this.userId = userId;
        this.userName = userName;
        this.mailbox = mailbox;
        this.userPwd = userPwd;
        this.phone = phone;
        this.sex = sex;
    }

    @ApiModelProperty(value = "主键")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别(0:男 1:女)")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", mailbox=").append(mailbox);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}