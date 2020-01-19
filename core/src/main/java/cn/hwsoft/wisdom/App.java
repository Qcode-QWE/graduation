package cn.hwsoft.wisdom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args){

        String string = "D:/java/imgaes";
        String string2 = "D:/java";
        System.out.println(string.substring(string.indexOf(string2)+string2.length()));

    }
}
