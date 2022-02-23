package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc{

    public boolean matchJpeg(String filename){
        if ( filename.toLowerCase().matches("[^\\s]+(\\.(jpg|jpeg)$)")) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean matchIp(String ip){
        if( ip.matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$") ) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEmptyLine(String line){
        if( line.matches("\\s*$") ) {
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String args[]){
        RegexExcImp rg=new RegexExcImp();
        System.out.println(rg.matchJpeg("abc.jpg"));
        System.out.println(rg.matchIp("192.168.0.1"));
        System.out.println(rg.isEmptyLine(""));
    }
}