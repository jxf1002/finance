package cn.justful.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by justful on 2018/1/28.
 * 家庭表
 */
@Entity
public class Family {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100, nullable = false)
    private String name;

    public Family() {
    }

    public Family(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
