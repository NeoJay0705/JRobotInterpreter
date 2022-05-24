package jrobot.functioners;

import java.awt.Robot;

public class Utils {
    private Utils() {

    }
    
    private static void realPressKey(Robot robot, int delay, int keycode) throws InterruptedException {
        robot.keyPress(keycode);
        robot.delay(delay);
    }

    private static void realReleaseKey(Robot robot, int delay, int keycode) throws InterruptedException {
        robot.keyRelease(keycode);
        robot.delay(delay);
    }

    public static void combinationKeys(Robot robot, int delay, int... keys) throws InterruptedException {
        for (int i = 0; i < keys.length; i++) {
            realPressKey(robot, delay, keys[i]);
        }

        for (int i = keys.length - 1; i > -1; i--) {
            realReleaseKey(robot, delay, keys[i]);
        }
    }
}
