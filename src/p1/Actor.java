package p1;

import lombok.Data;

@Data
public class Actor {
    private String name;
    private int age;

    Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Actor() {
    }
}
