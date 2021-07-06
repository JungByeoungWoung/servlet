package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//body에 직접 문자열을 입력해서 서버에 넘겨주는 서블릿
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        //requset.getInputStream() -> 메시지 body 내용을 바이트 코드로 얻을 수 있음
        ServletInputStream inputStream = requset.getInputStream();
        //바이트 코드로 받은 body 내용을 String 값으로 변환 해줘야한다 이때,StreamUtils라는 스프링 기능을 사용하여 바꿔줌
        //바이트 코드<->문자열로 바꿀 때는 어떤 인코딩으로 해줄 것인지 꼭 명시 ex)StandardCharsets.UTF_8
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("message = " + messageBody);
        response.getWriter().write("ok");
    }
}
