package cn.justful.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by justful on 2018/2/13.
 * 用户表
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer familyId;
    @Column(nullable = false)
    private Integer role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
