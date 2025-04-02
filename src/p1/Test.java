package p1;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void test1() {
        //        ArrayList<String> list = new ArrayList<>();
//        addALL(list, "abf", "b", "b", "c", "ab");
//        System.out.println(list);
//        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

//        ArrayList<String> list2 = new ArrayList<>();
//        addALL(list2, "abf-s12", "acd-b34", "bff-f22", "cww-45", "asa2-2");

//        String[] arr2 = list2.stream().map(s -> s.split("-")[1]).toArray(String[]::new);
//        String[] arr2 = list2.stream().map(s -> s.split("-")[1]).toArray(value -> new String[value]);
//        System.out.println(Arrays.toString(arr2));

//        list.stream().distinct().forEach(System.out::printf);
//        Stream.concat(list.stream(), list2.stream()).forEach(s -> System.out.printf(s + " "));
    }

    @SafeVarargs
    public static <T> void addALL(Collection<T> list, T... str) {
        list.addAll(Arrays.asList(str));
    }


    public static void test2() {
        ArrayList<String> list = new ArrayList<>();
        addALL(list, "zwj-nan-15", "zhangsanfeng-nan-100", "zhaomin-nv-18", "zhangsan-nan-20", "wangermazi-nv-1");
//        String[] nans = list.stream().filter(s -> s.split("-")[1].equals("nan")).toArray(String[]::new);
//        System.out.println(Arrays.toString(nans));

//        Map<String, Integer> map = list.stream().filter(s -> s.split("-")[1].equals("nan")).
//                collect(Collectors.toMap(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) {
//
//                        return s.split("-")[0];
//                    }
//                }, new Function<String, Integer>() {
//                    @Override
//                    public Integer apply(String s) {
//                        return Integer.parseInt(s.split("-")[2]);
//                    }
//                }));

//        Map<String, Integer> map2 = list.stream().filter(s -> s.split("-")[1].equals("nan")).
//                collect(Collectors.toMap(s -> s.split("-")[0], s -> Integer.parseInt(s.split("-")[2])));
//        System.out.println(map2);
    }


    public static void test3() {
//        ArrayList<Integer> list = new ArrayList<>();
//        addALL(list,1,2,3,4,5,6,7,8,8,9,10);
//        Integer[] arr1 = list.stream().filter(s -> s % 2 == 0).toArray(s -> new Integer[s]);
//        System.out.println(Arrays.toString(arr1));

        ArrayList<String> list = new ArrayList<>();
        addALL(list, "zhangsan, 23", "lissi, 24", "wangwu, 25");
        Map<String, Integer> map3 = list.stream().filter(
                        s -> Integer.parseInt(s.split(",")[1].trim()) >= 24).
                collect(Collectors.toMap(
                        s -> s.split(",")[0],
                        s -> Integer.parseInt(s.split(",")[1].trim())));
        System.out.println(map3);
    }


    public static void test4() {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        addALL(list1, "abc, 12", "bc, 13", "wer, 34", "as, 6", "qsw, 10086", "scd, 23");
        addALL(list2, "ywq, 7", "yec, 78", "cde, 56", "ywe, 114514", "bnm, 234", "ylk, 53");
//        Map<String, Integer> map1 = list1.stream()
//                .filter(s -> s.split(",")[0].length() == 3)
//                .limit(2)
//                .collect(Collectors.toMap(s -> s.split(",")[0],
//                        s -> Integer.parseInt(s.split(",")[1].trim())));
//        System.out.println(map1);
//
//        Map<String, Integer> map2 = list2.stream().skip(1).filter(s -> s.split(",")[0].charAt(0) == 'y').
//                collect(Collectors.toMap(s -> s.split(",")[0], s -> Integer.parseInt(s.split(",")[1].trim())));
//        System.out.println(map2);

        Stream<String> stream1 = list1.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2);

        Stream<String> stream2 = list2.stream().skip(1)
                .filter(s -> s.split(",")[0].charAt(0) == 'y');
        List<Actor> col = Stream.concat(stream1, stream2).map(s ->
                new Actor(s.split(",")[0],
                        Integer.parseInt(s.split(",")[1].trim()))
        ).collect(Collectors.toList());
        System.out.println(col);
    }

    public static void main(String[] args) {
        test4();
    }
}
