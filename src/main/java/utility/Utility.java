package utility;

import org.openqa.selenium.WebElement;

public class Utility {
    public static void sendKeysOptimized(WebElement element, String text) throws InterruptedException {
        element.clear();
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
            Thread.sleep(1000);
        }
    }

    public static String getOnlyNumberString(String str){
        StringBuilder strBuff = new StringBuilder();
        char c = 0;
        for(int i=0;i<str.length();i++){
            c=str.charAt(i);
            if(Character.isDigit(c)){
                strBuff.append(c);
            }
        }
        return strBuff.toString();
    }
}
