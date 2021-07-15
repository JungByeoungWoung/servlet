package hello.servlet.web.frontcontroller;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//요청 경로에 맞는 jsp 화면 즉 view로 넘겨주는 객체
public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //컨트롤러에서 jsp로 이동해주기 위한 메서드드
   public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //모델에 있는 데이터를 다 꺼내줌
        modelToReqeustAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    private void modelToReqeustAttribute(Map<String, Object> model, HttpServletRequest request) {
        //render메서드를 사용하면 모델에 있는 값을 다 꺼내서 request.setAttribute로 다 담는다
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
