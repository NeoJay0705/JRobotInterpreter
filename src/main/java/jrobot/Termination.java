package jrobot;

import java.awt.MouseInfo;
import java.awt.Point;

public class Termination {
    public static void mouseOnLeftTop() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        if (point.x == 0 && point.y == 0) {
            System.out.println("Terminate manually");
            System.exit(1);
        }
    }
}
