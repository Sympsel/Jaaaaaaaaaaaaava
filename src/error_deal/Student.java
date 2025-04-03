package error_deal;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String s) {

        this.id = Integer.parseInt(s.split(",")[0].trim());
        this.name = s.split(",")[1].trim();
        this.age = Integer.parseInt(s.split(",")[2].trim());
    }

    public Student() {
    }

    public void setAge(int age) {
        if (age < 0 || age > 30) throw new RuntimeException("年龄不合法");
        else this.age = age;
    }

    public void show() {
        System.out.println("id:" + this.id + ",name:" + this.name + ",age:" + this.age);
    }
}
