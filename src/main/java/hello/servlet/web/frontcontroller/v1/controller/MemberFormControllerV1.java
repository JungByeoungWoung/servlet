package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//회원 등록 폼 컨트롤러
public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //컨트롤러에서 알맞은 뷰로 이동할 수 있게 해주는 역할
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //해당 뷰로 request 객체와 response 객체를 넘겨줌
        dispatcher.forward(request,response);
    }
}
