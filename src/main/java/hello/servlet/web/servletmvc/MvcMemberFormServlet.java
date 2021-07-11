package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//컨트롤러 역할
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //getRequestDispatcher(viewPath) -> 컨트롤러에서 view로 이동하기 위해 사용 파라미터 값으로 뷰의 경로 입력
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //서블릿에서 jsp 호출을 가능하게 함
        dispatcher.forward(request,response);

    }
}
