package error_deal.practice;

import lombok.Data;

@Data
public class GirlFriend {
    private String name;
    private int age;

    public GirlFriend(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public GirlFriend() {
        this.name = "default";
        this.age = 18;
    }

    public GirlFriend(String s) {
        String name = s.split(",")[0].trim();
        try {
            if (name.length() > 10 || name.length() < 3) {
                System.out.println("name error");
                System.out.println("input again");
//                throw new RuntimeException("name error");
            }
            int age = Integer.parseInt(s.split(",")[1].trim());
            if (age < 0 || age > 30) {
//                throw new RuntimeException("age error");
                System.out.println("age error");
                System.out.println("input again");
            }
            this.name = name;
            this.age = age;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void show() {
        System.out.println("name: " + name + ", age: " + age);
    }
}
