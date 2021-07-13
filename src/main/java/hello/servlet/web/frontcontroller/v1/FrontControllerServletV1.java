package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v1/* : v1 하위에 있는 모든 폴더들을 요청할때 현재 이 서블릿을 무조건 호출 하게 된다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //key는 url, value는 ControllerV1의 구현 객체로 하여
    //url에 맞는 해당 구현 객체를 꺼내 온다.
    //맵핑 정보를 의미
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    //서블릿이 생성 될때 미리 맵핑 정보를 담아둘 것
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members",new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        //uri로 요청이들어온 것을 requestURI에 담는다.
        String requestURI = request.getRequestURI();

        //요청을 한 uri를 맵에 저장한 곳에서 찾고 해당 객체를 반환
        ControllerV1 controllerV1 = controllerMap.get(requestURI);
        if(controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //구현 객체에서 오버라이드 된 process 메서드가 실행 된다.
        controllerV1.process(request,response);
    }
}
