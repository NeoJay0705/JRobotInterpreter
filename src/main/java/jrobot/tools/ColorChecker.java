/**
 * 
 */
package jrobot.tools;

import java.nio.ByteBuffer;

/**
 * TODO
 * <Write a short description on the purpose of the program>
 *
 * @since 2021年12月2日
 * @author AP3NTABS
 * @version 1.0
 * @version $Id$
 *
 **/
public class ColorChecker {
    private int rgb;
    public final int X;
    public final int Y;
    
    public ColorChecker(int r, int g, int b, int x, int y) {
        this.rgb = ByteBuffer.wrap(new byte[] {new Integer(255).byteValue(), new Integer(r).byteValue(), new Integer(g).byteValue(), new Integer(b).byteValue()}).getInt();
        this.X = x;
        this.Y = y;
    }
    
    public boolean equals(int rgb) {
        return this.rgb == rgb;
    }
    
    public int getRGB() {
        return rgb;
    }
}
