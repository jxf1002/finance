package cn.justful.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by justful on 2018/2/13.
 * 账本表
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer accountId;
    @Column(nullable = false)
    private Date accountDate;
    @Column(length = 100, nullable = false)
    private String summary;
    @Column(nullable = false)
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
