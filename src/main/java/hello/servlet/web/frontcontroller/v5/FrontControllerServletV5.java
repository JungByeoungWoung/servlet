package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5" , urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    //private Map<String , ControllerV4> controllerMap = new HashMap<>();
    //기존 코드와 차이가 있음
    //핸들러 매핑 정보를 넣는 map
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //여러 어댑터 중에 맞는 어댑터를 찾아오기 위한 핸들러 어댑터 리스트를 저장하는 리스트
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }
    //핸들러 매핑 정보를 map에 넣어주는 메서드
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //v4 핸들러(컨트롤러) 맵핑 정보 생성
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form",new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save",new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    //핸들러 어댑터 목록중 v3에 맞는 어댑터를 핸들러 어댑터 리스트에 추가해주는 메서드
   private void initHandlerAdapters() {
        //핸들러 어댑터 목록중 v3에 맞는 어댑터를 핸들러 어댑터 리스트에 넣는다.
        handlerAdapters.add(new ControllerV3HandlerAdapter());
       //핸들러 어댑터 목록중 v4에 맞는 어댑터를 핸들러 어댑터 리스트에 넣는다.
       handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //그림으로 보면 1. 핸들러 조회 과정 이다.
        Object handler = gethandler(request);
        if (handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //2. 핸들러 어댑터 목록에서 맞는 핸들러를 가져오는 과정
        //어댑터 목록(List<MyHandlerAdapter> handlerAdapters) 중에서 handler 어댑터와 맞으면 해당 어댑터를 가져옴
        //new ControllerV3HandlerAdapter() -> adapter에 저장됨
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //3. 가져온 handler를 핸들러 어댑터에 넘겨준 후에 handler를 호출 하고
        //해당 데이터 값을 modelview에 넣어서 반환 하는 과정
        ModelView mv = adapter.handle(request, response, handler);

        //4. viewresolver를 호출 해서 myview로 반환하는 과정
        //modelview에서 view의 논리적인 이름을 가져오고 난 후 viewresolver로 물리 이름으로 변환한다.
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        //5. render로 modelview,request,response를 myview로 넘겨준다.
        view.render(mv.getModel(), request,response);

    }

    private Object gethandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //object로 가져오는 이유는 핸들러맵핑맵에서 object 객체로 찾아와서 가져오기 때문이다.
        //알맞은 핸들러를 object 타입으로 반환함
        return handlerMappingMap.get(requestURI);
    }

    //상황 예시
    //1. 컨트롤러 v3(조회한 handler)가 전달됨
    //2. 핸들러 어댑터 목록에서 for문으로 맞는 핸들러 조회
    //3. handler 구현객체에 있는 process 메서드로 지원 가능한지 확인 가능하면 adapter에 저장
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //2. 핸들러 어댑터 목록에서 맞는 핸들러를 가져오는 과정
        //어댑터 목록(List<MyHandlerAdapter> handlerAdapters) 중에서 handler 어댑터와 맞으면 해당 어댑터를 가져옴
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        //핸들러 어댑터 목록에서 없을 경우
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler" + handler);
    }

    //viewResolver 역할 하는 메서드
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}


