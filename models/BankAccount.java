package models;

import com.fasterxml.jackson.annotation.JsonInclude;
public class BankAccount {
    private Integer id;
    private String name;
    private Double balance;

    public BankAccount(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    // Builder class
    public static class Builder {
        private Integer id;
        private String name;
        private Double balance;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBalance(Double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}

