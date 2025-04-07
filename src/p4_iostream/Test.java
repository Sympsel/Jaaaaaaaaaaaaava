package p4_iostream;

import java.io.*;
import java.util.Arrays;

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
                copyDir(file.getAbsolutePath(), String.valueOf(new File(destDir,file.getName())));
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
        while((b = fis.read()) != -1) fos.write(b ^ 123);


        fos.close();
        fis.close();
    }

    public static void unlockedFile(String lockedFile, String unlockedFile) throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(lockedFile);
        FileOutputStream fos = new FileOutputStream(unlockedFile);

        int b;
        while((b = fis.read()) != -1) fos.write(b ^ 123);

        fos.close();
        fis.close();
    }
//    lockedFile("D:/桌面/小桃qq头像.jpg","tempfile/小桃qq头像.jpg");
//    unlockedFile("tempfile/小桃qq头像.jpg","tempfile2/小桃qq头像.jpg");

    public static void test8(final String src) throws IOException, InterruptedException {
        FileReader fr = new FileReader(src);
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1) sb.append((char)ch);
        fr.close();
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        test8("tempfile2/copy_reName.bat");
    }
}
