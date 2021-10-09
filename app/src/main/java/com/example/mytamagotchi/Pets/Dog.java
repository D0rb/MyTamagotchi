package com.example.mytamagotchi.Pets;
import com.example.mytamagotchi.Ulities.valuesObj;

public class Dog extends valuesObj {
    private int MIN_HUNGER_LIMIT = 5;
    private int MAX_HUNGER_LIMIT = 100;
    private int MIN_WATER_LIMIT = 5;
    private int MAX_WATER_LIMIT = 100;
    private int MIN_HAPPY_LIMIT = 5;
    private int MAX_HAPPY_LIMIT = 100;

    public Dog(int age, int hunger, int water, int happy, int health) {
        super(age, hunger, water, happy, health);
    }

    public void petsAge(){
        if (this.getAge() > 15){
            this.setHealth(0);
        }
    }

    public void petsHunger(){
        if (this.getAge() > 10) {
            if (this.getHunger() > 20) {
                this.setHealth(this.getHealth()-5);
                this.setHappy(this.getHappy()-5);
            }
        }
        if (this.getAge() > 5 && this.getAge() < 10) {
            if (this.getHunger() > 10) {
                this.setHealth(this.getHealth()-7);
                this.setHappy(this.getHappy()-7);
            }
        }
        if (this.getAge() > 0 && this.getAge() < 5) {
            if (this.getHunger() > 5) {
                this.setHealth(this.getHealth()-10);
                this.setHappy(this.getHappy()-10);
            }
        }
    }

    public void petsWater(){
        if (this.getAge() > 10) {
            if (this.getWater() > 5) {
                this.setHealth(this.getHealth()-10);
                this.setHappy(this.getHappy()-10);
            }
        }
        if (this.getAge() > 5 && this.getAge() < 10) {
            if (this.getWater() > 10) {
                this.setHealth(this.getHealth()-7);
                this.setHappy(this.getHappy()-7);
            }
        }
        if (this.getAge() > 0 && this.getAge() < 5) {
            if (this.getWater() > 20) {
                this.setHealth(this.getHealth()-5);
                this.setHappy(this.getHappy()-5);
            }
        }
    }

    public void petsHappy(){
        if (this.getAge() > 10) {
            if (this.getHappy() < 5) {
                this.setHealth(this.getHealth()-5);
                this.setHunger(this.getHunger()-5);
            }
        }
        if (this.getAge() > 5 && this.getAge() < 10) {
            if (this.getHappy() < 10) {
                this.setHealth(this.getHealth()-7);
                this.setHunger(this.getHappy()-7);
            }
        }
        if (this.getAge() > 0 && this.getAge() < 5) {
            if (this.getHappy() < 20) {
                this.setHealth(this.getHealth()-10);
                this.setHunger(this.getHunger()-10);
            }
        }
    }

    public void petsHealth(){

    }
}
