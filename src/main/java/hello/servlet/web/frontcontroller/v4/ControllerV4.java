package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    //컨트롤러에서 view의 이름만 반환해줄 것이기 때문에 string 타입 선택
    public String process(Map<String, String> paramMap, Map<String, Object> model);
}
