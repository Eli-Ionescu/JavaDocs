package ro.teamnet.zerotohero.oop.graphicshape;

import javax.swing.*;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(){
        this.xPos = 5;
        this.yPos = 5;
        this.radius = 5;
    }

    public Circle(int radius){
        this.radius = radius;
    }

    public Circle(int radius, int xPos){
        this.radius = radius;
        this.xPos = xPos;
    }

    Circle(int radius, int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double area(){
        return Math.PI * radius *radius;
    }

    public void fillColour(){
        System.out.println("This color is :" + super.color);
    }
    public void fillColour( int color){
        super.setColor(color);
        System.out.println("This color is NOW :" + super.color);
    }
    public void fillColour(float saturations){
        super.setSaturation(saturation);
        System.out.println("This saturations is NOW :" + super.saturation);
    }

    @Override
    public String toString() {
        return "center = (" + xPos+ " ," + yPos+ " ) and radius = " + radius;
    }
}
