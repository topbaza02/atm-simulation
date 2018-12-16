package com.test.atm.dto;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;


public class Account implements Cloneable {
    private BigDecimal banknotes_1000 = BigDecimal.ZERO;
    private BigDecimal banknotes_500 = BigDecimal.ZERO;
    private BigDecimal banknotes_100 = BigDecimal.ZERO;
    private BigDecimal banknotes_50 = BigDecimal.ZERO;
    private BigDecimal banknotes_20 = BigDecimal.ZERO;

    private BigDecimal balance = BigDecimal.ZERO;

    public Account() {
    }

    public Account(Account account) {
        BeanUtils.copyProperties(account,this);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBanknotes_1000() {
        return banknotes_1000!=null?banknotes_1000:BigDecimal.ZERO;
    }

    public void setBanknotes_1000(BigDecimal banknotes_1000) {
        this.banknotes_1000 = banknotes_1000;
    }

    public BigDecimal getBanknotes_500() {
        return banknotes_500!=null?banknotes_500:BigDecimal.ZERO;
    }

    public void setBanknotes_500(BigDecimal banknotes_500) {
        this.banknotes_500 = banknotes_500;
    }

    public BigDecimal getBanknotes_100() {
        return banknotes_100!=null?banknotes_100:BigDecimal.ZERO;
    }

    public void setBanknotes_100(BigDecimal banknotes_100) {
        this.banknotes_100 = banknotes_100;
    }

    public BigDecimal getBanknotes_50() {
        return banknotes_50!=null?banknotes_50:BigDecimal.ZERO;
    }

    public void setBanknotes_50(BigDecimal banknotes_50) {
        this.banknotes_50 = banknotes_50;
    }

    public BigDecimal getBanknotes_20() {
        return banknotes_20!=null?banknotes_20:BigDecimal.ZERO;
    }

    public void setBanknotes_20(BigDecimal banknotes_20) {
        this.banknotes_20 = banknotes_20;
    }
}
