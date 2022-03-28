package am.epam.pollWebApp.model;

import org.springframework.stereotype.Component;

import java.sql.Date;

public class Users {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String userName;
    private String email;
    private String password;
    private Date result_date;
    private String poll_result;

    public Users(int id, String name, String lastName, int age, String userName, String email, String password, Date result_date, String poll_result) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.result_date = result_date;
        this.poll_result = poll_result;
    }

    public Users() {}

    public String getPoll_result() {
        return poll_result;
    }

    public void setPoll_result(String poll_result) {
        this.poll_result = poll_result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getResult_date() {
        return result_date;
    }

    public void setResult_date(Date result_date) {
        this.result_date = result_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}