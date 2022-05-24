package jrobot.tools;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class ScreenFetcher {

    public static void main(String[] args) {
        
        try {
            Robot robot = new Robot();
            while (true) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                Color color = robot.getPixelColor(point.x, point.y);
                System.out.printf("RGB: %d,%d,%d at XY: %d,%d with Alpha: %d\n", color.getRed(), color.getGreen(), color.getBlue(), point.x, point.y, color.getAlpha());
                robot.delay(500);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
