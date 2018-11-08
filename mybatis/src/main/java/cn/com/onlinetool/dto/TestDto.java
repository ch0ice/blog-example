package cn.com.onlinetool.dto;

public class TestDTO {
    private Integer id;

    private String username;

    private String password;

    public TestDTO(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}