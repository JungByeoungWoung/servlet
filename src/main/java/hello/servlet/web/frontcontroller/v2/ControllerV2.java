package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //기존에는 void로 리턴 타입을 정했지만 이제 서블릿이 jsp로 반환하는 것을 Myview로 넘겨주기 때문에
    //Myview로 반환을 지정해주었다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
