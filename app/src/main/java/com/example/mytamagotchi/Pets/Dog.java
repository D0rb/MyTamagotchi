package com.example.mytamagotchi.Pets;
import com.example.mytamagotchi.Ulities.valuesObj;

public class Dog extends valuesObj {
    private int MIN_HUNGER_LIMIT = 5;
    private int MID_HUNGER_LIMIT = 10;
    private int MAX_HUNGER_LIMIT = 20;
    private int MIN_WATER_LIMIT = 5;
    private int MID_WATER_LIMIT = 10;
    private int MAX_WATER_LIMIT = 20;
    private int MAX_HAPPY_LIMIT = 20;
    private int MID_HAPPY_LIMIT = 10;
    private int MIN_HAPPY_LIMIT = 5;
    private int MAX_AGE_LIMIT = 10;
    private int MID_AGE_LIMIT = 5;
    private int MIN_AGE_LIMIT = 0;

    public Dog(int age, int hunger, int water, int happy, int health) {
        super(age, hunger, water, happy, health);
    }

    public void petsAge(){
        if (this.getAge() > 15){
            this.setHealth(0);
        }
    }

    public void petsHunger(){
        if (this.getAge() > MAX_AGE_LIMIT) {
            if (this.getHunger() > MAX_HUNGER_LIMIT) {
                this.setHealth(this.getHealth()-5);
                this.setHappy(this.getHappy()-5);
            }
        }
        if (this.getAge() > MID_AGE_LIMIT && this.getAge() < MAX_AGE_LIMIT) {
            if (this.getHunger() > MID_HUNGER_LIMIT) {
                this.setHealth(this.getHealth()-7);
                this.setHappy(this.getHappy()-7);
            }
        }
        if (this.getAge() > MIN_AGE_LIMIT && this.getAge() < MID_AGE_LIMIT) {
            if (this.getHunger() > MIN_HUNGER_LIMIT) {
                this.setHealth(this.getHealth()-10);
                this.setHappy(this.getHappy()-10);
            }
        }
    }

    public void petsWater(){
        if (this.getAge() > MAX_AGE_LIMIT) {
            if (this.getWater() > MIN_WATER_LIMIT) {
                this.setHealth(this.getHealth()-10);
                this.setHappy(this.getHappy()-10);
            }
        }
        if (this.getAge() > MID_AGE_LIMIT && this.getAge() < MAX_AGE_LIMIT) {
            if (this.getWater() > MID_WATER_LIMIT) {
                this.setHealth(this.getHealth()-7);
                this.setHappy(this.getHappy()-7);
            }
        }
        if (this.getAge() > MIN_AGE_LIMIT && this.getAge() < MID_AGE_LIMIT) {
            if (this.getWater() > MAX_WATER_LIMIT) {
                this.setHealth(this.getHealth()-5);
                this.setHappy(this.getHappy()-5);
            }
        }
    }

    public void petsHappy(){
        if (this.getAge() > MAX_AGE_LIMIT) {
            if (this.getHappy() < MIN_HAPPY_LIMIT) {
                this.setHealth(this.getHealth()-5);
                this.setHunger(this.getHunger()-5);
            }
        }
        if (this.getAge() > MID_AGE_LIMIT && this.getAge() < MAX_AGE_LIMIT) {
            if (this.getHappy() < MID_HAPPY_LIMIT) {
                this.setHealth(this.getHealth()-7);
                this.setHunger(this.getHappy()-7);
            }
        }
        if (this.getAge() > MIN_AGE_LIMIT && this.getAge() < MID_AGE_LIMIT) {
            if (this.getHappy() < MAX_HAPPY_LIMIT) {
                this.setHealth(this.getHealth()-10);
                this.setHunger(this.getHunger()-10);
            }
        }
    }

    public void petsHealth(){

    }
}
