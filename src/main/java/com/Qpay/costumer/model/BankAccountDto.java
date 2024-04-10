package com.Qpay.costumer.model;

public class BankAccountDto {
    private boolean Default;
    private String accountBankCode;
    private String accountNumber;
    private String accountName;
    private boolean isDefault;

    public boolean getDefault() {
        return Default;
    }

    public void setDefault(boolean Default) {
        this.Default = Default;
    }

    public String getAccountBankCode() {
        return accountBankCode;
    }

    public void setAccountBankCode(String accountBankCode) {
        this.accountBankCode = accountBankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
