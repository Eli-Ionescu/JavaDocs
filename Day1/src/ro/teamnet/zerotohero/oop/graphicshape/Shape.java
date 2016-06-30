package ro.teamnet.zerotohero.oop.graphicshape;

import sun.security.provider.SHA;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class Shape extends AbstractShape implements ShapeBehaviour  {

    protected int color;
    protected float saturation;



    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public void setSaturation(float saturation){
        this.saturation = saturation;
    }

    public float getSaturation(){
        return saturation;
    }


    @Override
    public double area() {
        return 3.15;
    }
}
