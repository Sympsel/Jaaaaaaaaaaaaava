package error_deal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void test1() throws ParseException {
        // 编译时异常
        String time = "1234年1月1日";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date d = dateFormat.parse(time);
        System.out.println(d);
    }


    public static void test2() throws ParseException {
        Student[] stu = new Student[3];
        stu[0] = new Student();
        stu[0].setName("张三");
        stu[0].setAge(20);
        System.out.println(stu[0].getName() + " " + stu[0].getAge());
    }

    public static void test3() throws ParseException {
        int a = 2;
        int b = 0;
        try {
//            System.out.println(a / b);
            int[] arr = {1,2};
            System.out.println();
            System.out.println(arr[3]);
        } catch (ArithmeticException e) {
//            throw new RuntimeException("零作为除数");
            System.out.println("错误: 零作为除数");
            System.out.println(a / (b + 1));
        } catch (Exception e) {
//            System.out.println("错误: " + e.getMessage());
            throw new NullPointerException("数组越界");
        }
        System.out.println(111);
    }

    public static void main(String[] args) throws ParseException {
//        test3();
        System.err.println("错误");
        System.out.println("错误");
    }
}
