package ca.jrvs.apps.practice;
import java.util.regex.*;

public class RegexExcImp implements RegexExc{

    public boolean matchJpeg(String filename){
        String name = ".+(\\.jpg)$\n" +
                ".+(\\.jpeg)$";

        Pattern p = Pattern.compile(name);

        if (filename == null) {
            return false;
        }

        Matcher m = p.matcher(filename);

        return m.matches();
    }

    public boolean matchIp(String ip){
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        Pattern p = Pattern.compile(regex);

        if (ip == null) {
            return false;
        }

        Matcher m = p.matcher(ip);

        return m.matches();
    }

    public boolean isEmptyLine(String line){
        String empty = "^\\s*$";

        Pattern p = Pattern.compile(empty);

        if (line == null) {
            return false;
        }

        Matcher m = p.matcher(line);

        return m.matches();
    }
}