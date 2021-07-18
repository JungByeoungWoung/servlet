package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//핸들러 어댑터 목록에서 있으면 frontcontroller가 꺼내 올 수 있게 하는
//어댑터 목록
public interface MyHandlerAdapter {
    //컨트롤러(핸들러)가 넘어왔을 때 해당 컨트롤러 타입에 맞게 지원 여부를 알 수 있게 해주는 역할
    boolean supports(Object handler);
    //handle : 맞는 컨트롤러를 호출 한 뒤 ModelView(데이터 저장하는 것)로 반환 해준다.
    ModelView handle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException,IOException;
}
