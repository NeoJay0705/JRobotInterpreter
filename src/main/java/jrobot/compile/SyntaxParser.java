package jrobot.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxParser {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("name:(.*)");
        String artical = "name:neo";
        Matcher m = p.matcher(artical);
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.groupCount());
            System.out.println(artical.substring(m.start(1), m.end(1)));
        }
    }
}
