package com.example.mytamagotchi.Ulities;

public class valuesObj {
    private int age;
    private int hunger;
    private int water;
    private int happy;
    private int health;

    public valuesObj(){}

    public valuesObj(int age , int hunger , int water , int happy , int health){
        age = this.age;
        water = this.water;
        happy = this.hunger;
        happy = this.happy;
        health = this.health;
    }

    public int getAge() {
        return age;
    }

    public int getHappy() {
        return happy;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public int getWater() {
        return water;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
