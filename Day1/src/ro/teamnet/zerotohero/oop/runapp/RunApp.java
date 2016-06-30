package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.*;
import ro.teamnet.zerotohero.oop.*;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class RunApp {
    public static void main(String[] args)  {
        Circles c = new Circles();
        System.out.println("The default area for a circle is " +  c.getAreaPub());

        c.getAreaDef();

        Shape shape = new Circle(10);
        shape.area();

        ShapeBehaviour shapeB = new Square(10);
        shapeB.area();

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        myException e = new myException("Noua mea exceptie SUPER tare");
        try {
            throw e;
        } catch (myException e1) {
            
            e1.printStackTrace();
        }

    }
}
