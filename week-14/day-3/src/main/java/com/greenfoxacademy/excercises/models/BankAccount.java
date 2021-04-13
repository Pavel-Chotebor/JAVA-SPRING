package com.greenfoxacademy.excercises.models;

public class BankAccount {

    protected String name;
    protected double balance;
    protected String animalType;
    protected String typeOfAccount;
    protected boolean behaviour;

    //DecimalFormat df = new DecimalFormat("0.00");

    public BankAccount(String name, double balance, String animalType, String typeOfAccount, boolean behaviour) {
        this.name = name;
        this.balance = balance;
        this.animalType = animalType;
        this.typeOfAccount = typeOfAccount;
        this.behaviour = behaviour;
    }

    public boolean isBehaviour() {
        return behaviour;
    }

    public void setBehaviour(boolean behaviour) {
        this.behaviour = behaviour;
    }


    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
