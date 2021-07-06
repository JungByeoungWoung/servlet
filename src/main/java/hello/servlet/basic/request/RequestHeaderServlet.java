package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requsetHeaderServlet",urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http 요청 메시지에 있는 가장 첫라인을 불러오는 메서드
        printStartLine(request);
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("---REQUEST-LINE-start---");

        System.out.println("requset.getMethod() = " + request.getMethod());//GET
        System.out.println("request.getProtocol() = " + request.getProtocol());//HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme());//http

        //http://localhost:8080/requset-header
        System.out.println("request.getRequestURL() = "+ request.getRequestURL());
        System.out.println("request.getRequestURI() = "+ request.getRequestURI());
        System.out.println("request.getQueryString() = "+ request.getQueryString());
        System.out.println("request.isSecure() = "+ request.isSecure());//https 사용 유무
        System.out.println("----REQUEST-LINE - end ---");
        System.out.println();
    }
}
