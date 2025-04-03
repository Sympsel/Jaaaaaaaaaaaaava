package p3_about_file;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test {

    public static void test1() {
        String str = "tempfile";
        File f1 = new File(str, "1.txt");
        File f2 = new File(f1, "2.txt");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1.exists());
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f1.length());
        System.out.println(f1.getName());
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getPath());
    }

    public static void test2() {
        String str = "tempfile";
        File f1 = new File(str);
        System.out.println(f1);
        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());
        System.out.println(f1.exists());

        File f2 = new File("D:/桌面/安装包/rustup-init.exe");
        System.out.println(f2);
        System.out.println(f2.exists());
        System.out.println(f2.length() / 1024 / 1024);
        System.out.println(f2.getName());
        System.out.println(f2.lastModified() / 1000 / 3600 / 24);
    }

    public static void test3() throws IOException {
        File f1 = new File("D:/桌面/安装包/rustup-init.exe");

        File f2 = new File("tempfile/2.txt");
        System.out.println(f2.createNewFile());
        File f3 = new File("tempfile/aaa/bbb");
        System.out.println(f3.mkdir());
        ;
        System.out.println(f3.mkdirs());
        ;
        System.out.println(f3.delete());
        ;
    }

    public static void test4(final String rootPath, String endWith) {
        File f = new File(rootPath);
        if (!f.exists()) return;
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null)
                for (File file : files)
                    test4(String.valueOf(file), endWith);
        } else if (f.isFile()) {
            if (String.valueOf(f).endsWith(endWith)) {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    //        test4("D:/桌面/游戏/.minecraft", ".json");

    public static void test5(String rootPath) {
        File f = new File(rootPath);
        if (!f.exists()) return;
        File[] files = f.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile()) file.delete();
            else test5(file.getAbsolutePath());
        }
        f.delete();
    }

    //    test5("tempfile2");

    public static long test6(final String rootPath) {
        final File f = new File(rootPath);
        if (!f.exists()) return 0;
        long sum = 0;
        File[] files = f.listFiles();
        if (files == null) return 0;
        for (File file : files) {
            if (file.isFile()) sum += file.length();
            else sum += test6(file.getAbsolutePath());
        }
        return sum;
    }

    public static long calculateTotalSize(String rootPath) {
        Deque<File> queue = new ArrayDeque<>();
        File root = new File(rootPath);
        queue.offer(root);

        long totalSize = 0;
        while (!queue.isEmpty()) {
            File currentFile = queue.poll();
            if (currentFile.isDirectory()) {
                File[] files = currentFile.listFiles();
                if (files != null)
                    for (File file : files)
                        queue.offer(file);
            } else totalSize += currentFile.length();
        }
        return totalSize;
    }
//    System.out.println(test6("D:/桌面/游戏/.minecraft"));
//    System.out.println(calculateTotalSize("D:/桌面/游戏/.minecraft"));

    public static HashMap<String, Integer> test8(final String rootPath) {
//        HashMap<String, Integer> hm = new HashMap<>();
//        File f = new File(rootPath);
//        File[] files = f.listFiles();
//        if (files == null) return hm;
//        for (File file : files) {
//            if (file.isFile()) {
//                String[] split = file.getName().split("\\.");
//                if (split.length >= 2) {
//                    String end = split[split.length - 1];
//                    if (hm.containsKey(end)) {
//                        int count = hm.get(end);
//                        hm.put(end, ++count);
//                    } else hm.put(end, 1);
//                }
//            } else if (file.isDirectory()) {
//                HashMap<String, Integer> sonMap = test8(file.getAbsolutePath());
//                Set<Map.Entry<String, Integer>> entries = sonMap.entrySet();
//                for (Map.Entry<String, Integer> entry : entries) {
//                    String key = entry.getKey();
//                    int value = entry.getValue();
//                    if (hm.containsKey(key)) hm.put(key, hm.get(key) + value);
//                    else hm.put(key, value);
//                }
//            }
//        }
//        return hm;

        HashMap<String, Integer> hm = new HashMap<>();
        File f = new File(rootPath);
        File[] files = f.listFiles();
        if (files == null) return hm;
        for (File file : files) {
            if (file.isFile()) {
                String[] split = file.getName().split("\\.");
                if (split.length >= 2) {
                    String end = split[split.length - 1];
                    if (hm.containsKey(end)) hm.put(end, hm.get(end) + 1);
                    else hm.put(end, 1);
                }
            } else {
                HashMap<String, Integer> sonMap = test8(file.getAbsolutePath());
                Set<Map.Entry<String, Integer>> entries = sonMap.entrySet();
                for (Map.Entry<String, Integer> entry : entries) {
                    String key = entry.getKey();
                    if (hm.containsKey(key)) hm.put(key, hm.get(key) + entry.getValue());
                    else hm.put(key, entry.getValue());
                }
            }
        }
        return hm;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> ret = test8("D:/桌面/游戏/.minecraft");
        System.out.println(ret);
    }
}
