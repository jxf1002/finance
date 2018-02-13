package cn.justful.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by justful on 2018/2/13.
 * 科目表
 */
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 100, nullable = false)
    private String type;
    @Column(length = 100, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
