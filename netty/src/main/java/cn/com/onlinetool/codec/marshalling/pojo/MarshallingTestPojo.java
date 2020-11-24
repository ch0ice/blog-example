package cn.com.onlinetool.codec.marshalling.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author choice
 * @date create in 2020/4/8 15:51
 */
public class MarshallingTestPojo implements Serializable {
    private static final long serialVersionUID = 5303405538548809287L;
    private long id;
    private String username;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarshallingTestPojo that = (MarshallingTestPojo) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, data);
    }

    @Override
    public String toString() {
        return "MarshallingTestPojo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
