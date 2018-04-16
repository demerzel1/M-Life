package com.dhu.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by demerzel on 2018/3/30.
 */
@Entity
@Table(name = "user", schema = "MCMS", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private String password;
    private String tel;
    private double money;
    private byte isAdmin;
    private String email;
    private String avatar;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 20)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "is_admin", nullable = false)
    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Double.compare(that.money, money) == 0 &&
                isAdmin == that.isAdmin &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(tel, that.tel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, tel, money, isAdmin);
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 50)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
