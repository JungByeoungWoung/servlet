package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//json 으로 전달하기 위해 값을 저장할 json 객체
@Getter
@Setter
public class HelloData {
    private String username;
    private int age;
}
