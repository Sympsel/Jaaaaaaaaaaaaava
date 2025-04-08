package p4_iostream;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Test {

    public static void test1() throws IOException {
        FileOutputStream fos1 = new FileOutputStream("tempfile/1.txt", true);

//        fos1.write(65);
//        fos1.write(9 + '0');
//        fos1.write(7 + '0');
//    fos1.write(97);

        String str = "Hello\r\n world!\n你好世界\r\n";
        byte[] b = str.getBytes();

        fos1.write(b);
        fos1.close();
    }

    public static void test2() throws IOException {
        FileInputStream fis = new FileInputStream("tempfile/1.txt");
        int b1;
        while ((b1 = fis.read()) != -1) {
            System.out.print((char) b1);
        }

        fis.close();
    }

    // 小文件拷贝
    public static void test3() throws IOException {
        // 计算拷贝时间
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("tempfile/1.txt");
        FileOutputStream fos = new FileOutputStream("tempfile/copy_1.txt");
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        long end = System.currentTimeMillis();
        System.out.printf("文件拷贝完成, 花费%d毫秒\n", end - start);
        fos.close();
        fis.close();
    }

    // 大文件拷贝
    public static void fileCopies(final String sourceFile, String targetDir) throws IOException {
        File file = new File(sourceFile);
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetDir +
                sourceFile.split("/")[sourceFile.split("/").length - 1]);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        long start = System.currentTimeMillis();

        int len = (int) file.length();
        byte[] b;

        if (len < 1024 * 4) b = new byte[len];
        else b = new byte[1024 * 4];

        while ((len = fis.read(b)) != -1)
            fos.write(b, 0, len);

        long end = System.currentTimeMillis();

        System.out.printf("文件拷贝完成,总共耗时%d毫秒\n", end - start);
        fos.close();
        fis.close();
    }
//    String str = "tempfile/ccc/reName.bat";
//    fileCopies(str, "tempfile/aaa/");

    public static void test4() throws UnsupportedEncodingException {
        String str = "你好世界！";
        byte[] bytes1 = str.getBytes();
        System.out.println(Arrays.toString(bytes1));

        byte[] bytes2 = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes2));

        String str2 = new String(bytes1);
        String str3 = new String(bytes2, "GBK");
        String str4 = new String(bytes1, "GBK");
        String str5 = new String(bytes2);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
    }

    public static void test5() throws IOException, InterruptedException {
        String batFile = "reName.bat";
        String arg = "D:/桌面/code/javacodepractice/date4.1/tempfile/aaa/bbb/*.*";
        System.out.println(arg);
        String replace = arg.replace("/", "\\");
        System.out.println(replace);
        String command = "powershell /c start " + batFile + " " + replace;

        Process p = Runtime.getRuntime().exec(command);
        int exitCode = p.waitFor();
        System.out.println("Exited with code : " + exitCode);
//        try {
//            p.waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void test6() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream("tempfile/1.txt");
        FileOutputStream fos = new FileOutputStream("tempfile/2.txt");

        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }

        fos.close();
        fis.close();
    }

    public static void test7() throws IOException, InterruptedException {
        FileReader fr = new FileReader("tempfile/1.txt");
        FileWriter fw = new FileWriter("tempfile/2.txt", true);

//        int b;
//        while((b = fr.read()) != -1) {
//            fw.write(b);
//        }

        String str = "Hello\r\n world!\n你好世界\r\n";
        fw.write(str);

        fw.close();
        fr.close();
    }

    public static void copyDir(final String srcDir, String destDir) throws IOException {
        File f = new File(srcDir);
        File d = new File(destDir);
        d.mkdirs();

        File[] files = f.listFiles();

        for (File file : files) {
            if (file.isDirectory())
                copyDir(file.getAbsolutePath(), String.valueOf(new File(destDir, file.getName())));
            else if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(destDir, file.getName()));
                byte[] read = new byte[1024];
                int len;
                while ((len = fis.read(read)) != -1) fos.write(read, 0, len);
                fos.close();
                fis.close();
            }
        }
    }
    // copyDir("tempfile", "tempfile2");

    public static void lockedFile(String srcFile, String destFile) throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 123);


        fos.close();
        fis.close();
    }

    public static void unlockedFile(String lockedFile, String unlockedFile) throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(lockedFile);
        FileOutputStream fos = new FileOutputStream(unlockedFile);

        int b;
        while ((b = fis.read()) != -1) fos.write(b ^ 123);

        fos.close();
        fis.close();
    }
//    lockedFile("D:/桌面/小桃qq头像.jpg","tempfile/小桃qq头像.jpg");
//    unlockedFile("tempfile/小桃qq头像.jpg","tempfile2/小桃qq头像.jpg");

    public static void test8(final String src) throws IOException, InterruptedException {
        FileReader fr = new FileReader(src);
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) sb.append((char) ch);
        fr.close();
        System.out.println(sb);
    }
    // test8("tempfile2/copy_reName.bat");

    public static void test9(String src, String dest) throws IOException, InterruptedException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        int b;
        while ((b = bis.read()) != -1) bos.write(b ^ 123);
        bos.close();
        bis.close();
    }

    public static void test10(String src, String dest) throws IOException, InterruptedException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        int len;
        byte[] r = new byte[1024];
        while ((len = bis.read(r)) != -1) bos.write(r, 0, len);
        bos.close();
        bis.close();
    }
//    test9("tempfile/小桃qq头像.jpg", "tempfile/小桃qq头像2.jpg");
//    test10("tempfile/小桃qq头像2.jpg", "tempfile2/小桃qq头像.jpg");

    public static void test11(String src) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(src));
        String line;
        while ((line = br.readLine()) != null) System.out.println(line);
        br.close();
    }
    // test11("D:/桌面/code/javacodepractice/date4.1/tempfile2/copy_reName.bat");

    public static int test12(int maxTimes) throws IOException {
        File f = new File("tempfile/runtimes.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();
        int cnt = Integer.parseInt(line) + 1;
        if (cnt > maxTimes) return maxTimes;
        System.out.println("当前运行次数为：" + cnt);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(cnt + "");
        br.close();
        bw.close();
        return cnt;
    }

    public static void reCount() throws IOException {
        File f = new File("tempfile/runtimes.txt");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write('0');
        fos.close();
    }

    public static void test13(int runTimes, int maxTimes) throws IOException {
        int times = 0;
        for (int i = 0; i < runTimes; i++) {
            times = test12(maxTimes);
            if (times >= maxTimes) {
                System.out.println("已经免费运行了" + maxTimes + "次，不能再继续运行了，得充VIP");
                break;
            }
        }
    }

//            int MAX_TIMES = 12;
//            int RUN_TIMES = 13;
//
//            reCount();
//            test13(RUN_TIMES, MAX_TIMES);

    public static void test14(String src) throws IOException, InterruptedException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(src), StandardCharsets.UTF_8);
        InputStreamReader isr2 = new InputStreamReader(new FileInputStream(src), "GBK");
        int b;
        while ((b = isr.read()) != -1) System.out.print((char) b);
        while ((b = isr2.read()) != -1) System.out.print((char) b);
    }

    public static void test15(String src) throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(src);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String r;
        while ((r = br.readLine()) != null) {
            System.out.print(r);
            br.lines();
        }
    }

    public static void test16(String src) throws IOException, InterruptedException {
        Student s = new Student("张三", 24);
        Student s2 = new Student("张三", 21);
        Student s3 = new Student("张三", 22);
        Student s4 = new Student("张三", 23);
        Student s5 = new Student("张三", 25);
        ArrayList<Student> stu = new ArrayList<>();
        Collections.addAll(stu, s, s2, s3, s4, s5);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(src));

        oos.writeObject(stu);

        oos.close();
    }

    public static void test17(String src) throws IOException, InterruptedException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(src));
        ArrayList<Student> list = (ArrayList<Student>) ois.readObject();
        for (Student student : list) {
            System.out.println(student);
        }
        ois.close();
    }
    //        test16("tempfile/copy_1.txt");
    //        test17("tempfile/copy_1.txt");

    public static void test18(String src) throws IOException, InterruptedException {
        PrintStream ps = new PrintStream(src);
        ps.println("hello");
        ps.print("true");
        ps.println(true);
        System.out.println(true);
        ps.printf("qqq%s", "sss");

        ps.close();
    }

    public static void test19(String src) throws IOException, InterruptedException {
        PrintWriter pw = new PrintWriter(new FileWriter(src), true);
        pw.println("hello");
        pw.print("true");
        pw.println(true);
        System.out.println(true);
        pw.printf("qqq%s", "sss");
        pw.close();
    }
    //        test19("tempfile/3.txt");

    public static void unzip(String src, String dest) throws IOException, InterruptedException {
        File s = new File(src);
        File d = new File(dest);
        if (!d.exists()) d.mkdirs();
        ZipInputStream zis = new ZipInputStream(new FileInputStream(s));

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println(entry);
            if (entry.isDirectory()) {
                File file = new File(d, entry.toString());
                file.mkdirs();
            } else {
                FileOutputStream fos = new FileOutputStream(new File(d, entry.toString()));
                int b;
                byte[] r = new byte[1024];
                while ((b = zis.read(r)) != -1) fos.write(r, 0, b);
                fos.close();
            }
            zis.closeEntry();
        }
    }
    // unzip("tempfile3/aikflfpejipbpjdlfabpgclhblkpaafo.zip", "tempfile3/aikflfpejipbpjdlfabpgclhblkpaafo");

    public static void zip(String src, String dest) throws IOException {
        File s = new File(src);
        File d = new File(dest);
        if (!d.exists()) d.mkdirs();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(d, "安装说明书.zip")));
        ZipEntry entry = new ZipEntry("安装说明书.txt");
        zos.putNextEntry(entry);

        FileInputStream fis = new FileInputStream(s);
        int len;
        byte[] r = new byte[1024];
        while ((len = fis.read(r)) != -1) zos.write(r, 0, len);

        zos.closeEntry();
        zos.close();
    }

    // zip("tempfile3/aikflfpejipbpjdlfabpgclhblkpaafo/安装说明书.txt", "tempfile3/aikflfpejipbpjdlfabpgclhblkpaafo/");


    public static void zip(String src) throws IOException, InterruptedException {
        File s = new File(src);
        File dest = new File(s + ".zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        toZip(s, zos, s.getName());
        zos.close();
    }

    public static void toZip(File src, ZipOutputStream zos, String path) throws IOException {
        File[] files = src.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile()) {
                ZipEntry entry = new ZipEntry(path + "/" + file.getName());
                zos.putNextEntry(entry);
                FileInputStream fis = new FileInputStream(file);
                int len;
                byte[] r = new byte[1024];
                while ((len = fis.read(r)) != -1) zos.write(r, 0, len);
                fis.close();
                zos.closeEntry();
            } else toZip(file, zos, path + "/" + file.getName());
        }
    }
//    zip("tempfile3");

    public static void test20() throws IOException, InterruptedException {
        File src = new File("tempfile3.zip");
        File dest = new File("tempfile3(1).zip");
        FileUtils.copyFile(src, dest);
    }

    public static void test21() throws IOException, InterruptedException {
        File src = new File("tempfile3");
        File dest = new File("tempfile4");
        FileUtils.copyDirectory(src, dest);
    }
    //    test21();
    //    FileUtils.deleteDirectory(new File("tempfile4"));

    public static void test22() throws IOException, InterruptedException {
//        File file = FileUtil.file("tempfile", "4.txt");
//        System.out.println(file);
//
//        File touch = FileUtil.touch(file);
//        System.out.println(touch);
//        ArrayList<String> strList = new ArrayList<>();
//        Collections.addAll(strList, "1", "2", "3a");
//        System.out.println(strList);
//
//        File file2 = FileUtil.writeLines(strList, "D:\\桌面\\code\\javacodepractice\\date4.1\\tempfile\\4.txt", "utf-8", true);
//        System.out.println(file2.exists());
//        System.out.println(file2);
//        List<String> lines = Files.readAllLines(file2.toPath(), StandardCharsets.UTF_8);
//        System.out.println("File content: " + lines);

        List<String> list = FileUtil.readLines("tempfile/4.txt", "utf-8");
        System.out.println(list);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        test22();
    }
}
