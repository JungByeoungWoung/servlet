package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    //서블릿 서비스와 같은 인터페이스를 만듬
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
