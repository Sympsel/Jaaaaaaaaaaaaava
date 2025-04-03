package p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

public class Test {

    public static void test1() {
//        String[] arr = {"1", "4", "6", "2", "3", "5", "8", "7", "9", "0"};
//        // lambda表达式
//        Arrays.sort(arr, (a, b) -> a - b);
//        System.out.println(Arrays.toString(arr));
//
//        ArrayList<String> list = new ArrayList<>();
//        ArrayList<Integer> list2 = new ArrayList<>();
//        addAll(list, arr);
//
//        for (String s : list) {
//            list2.add(Integer.parseInt(s));
//        }
//
//        System.out.println(list2);
//
//        // lambda表达式
//        list.stream().map(s -> Integer.parseInt(s)
//        ).forEach(s -> System.out.print(s));
//
//        // 方法引用
//        list.stream().map(Integer::parseInt).forEach(System.out::print);
    }

    public void test2() {
//        ArrayList<String> list = new ArrayList<>();
//        Collections.addAll(list, "ax", "c", "x", "wx", "v");
//
//        list.stream().filter(new Predicate<String>() {
//            public boolean test(String s) {
//                return s.length() == 1;
//            }
//        }).forEach(s -> System.out.print(s + " "));
//
//        list.stream().filter(new Demo1()::test).forEach(System.out::print);
//        list.stream().filter(new Test()::test).forEach(System.err::print);
//        System.out.println();


    }

    public static void test3() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1, zhangsan, 15", "2, lisi, 16",
                "3, wangwu, 17", "4,zhaoliu, 18");

        // 匿名内部类
        list.stream().map(new Function<String, Student>() {
            @Override
            public Student apply(String s) {
                return new Student(Integer.parseInt(s.split(",")[0].trim()),
                        s.split(",")[1].trim(),
                        Integer.parseInt(s.split(",")[2].trim()));
            }
        }).forEach(Student::show);

        // lambda表达式
        list.stream().map(s -> new Student(
                        Integer.parseInt(s.split(",")[0].trim()),
                        s.split(",")[1].trim(),
                        Integer.parseInt(s.split(",")[2].trim())))
                .toList().forEach(Student::show);

        // 方法引用
        list.stream().map(Student::new)
                .toList().forEach(Student::show);
    }

    public static void test4() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "sacsdc", "vfbvfgbg", "dfvrvgv");

        list.stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }).forEach(s -> System.out.print(s + " "));

        list.stream().map(s -> s.toUpperCase()).forEach(s -> System.out.print(s + " "));

        list.stream().map(Test::toUpper).forEach(s -> System.out.print(s + " "));

        list.stream().map(String::toUpperCase).forEach(s -> System.out.print(s + " "));
    }

    public static void test5() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer[] arr = list.stream().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static void test6() {
//        ArrayList<String> list = new ArrayList<>();
//        Collections.addAll(list, "zhangsan,23", "lisi,34", "wangwu,13");

//        Map<String, Integer> map = list.stream()
//                .collect(Collectors.toMap(
//                        s -> s.split(",")[0],
//                        s -> Integer.parseInt(
//                                s.split(",")[1])));
//        System.out.println(map);


//        ArrayList<String> list = new ArrayList<>();
//        Collections.addAll(list, "1,zhangsan,23", "2,lisi,34", "3,wangwu,13");
//        Student[] stu1 = list.stream().map(Student::new).toArray(Student[]::new);
//        for (Student s : stu1) s.show();
//
//        System.out.println(Arrays.toString(stu1));

        ArrayList<Student> list = new ArrayList<>();
        Collections.addAll(list, new Student(1, "zhangsan", 23),
                new Student(2, "lisi", 34),
                new Student(3, "wangwu", 13));

        String[] stuName = list.stream().map(Student::getName).toArray(String[]::new);
        System.out.println(Arrays.toString(stuName));
    }

    public static void main(String[] args) {
        test6();
    }

//    public boolean test_2(String s) {
//        return s.length() == 2;
//    }
//
//    public void test_2(String s) {
//    }

    public static String toUpper(String s) {
        return s.toUpperCase();
    }
}
