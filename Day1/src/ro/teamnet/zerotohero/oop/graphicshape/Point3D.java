package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class Point3D extends Point{
    int zPos;

    public Point3D(int xPos, int yPos, int zPos){
        super(xPos, yPos);
        this.zPos = zPos;
    }
}
