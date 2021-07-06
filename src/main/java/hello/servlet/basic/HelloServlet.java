package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //이 서블릿이 실행 되면 아래 해당 서비스 메소드가 실행 됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String name = request.getParameter("username");
        System.out.println("user name = " + name);

        //응답 헤더 부분에 contenttype,encoding 정보를 넣는다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //응답 바디에 넣을 값을 적어줌.
        response.getWriter().write("hello "+name);
    }
}
