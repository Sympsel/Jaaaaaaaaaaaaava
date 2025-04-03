package error_deal.practice;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        GirlFriend gf = null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            try{
                gf = new GirlFriend(str);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

            if(gf == null) {
                System.err.println("输入为空");
            }
            gf.show();
            break;
        }
    }
}
