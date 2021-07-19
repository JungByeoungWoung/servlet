package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form",new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //request 요청이 왔을 때 요청하는 값들을 저장하는 map
        Map<String, String> paramMap = createParamMap(request);
        //request 요청이 왔을 때 model도 넘겨줘야하기 때문에 model 생성
        Map<String, Object> model = new HashMap<>();

        //컨트롤러에서 처리한 process에서 paramMap과 model을 받은 후 viewName으로 저장
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        view.render(model, request, response);

    }
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
    private Map<String,String> createParamMap(HttpServletRequest request){
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));
        return paramMap;
    }
}
