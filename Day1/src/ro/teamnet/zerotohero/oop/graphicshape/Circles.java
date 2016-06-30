package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class Circles {

    public double getAreaPub(){
        Circle c = new Circle();
        return c.area();
    }

    public void getAreaDef(){
        Circle circle = new Circle();

        circle.fillColour();
        circle.fillColour(10);
        circle.fillColour(3);
    }
}
