package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

//request.serAttribute를 하면서 모델에 저장했는데 이 역할을 대신 해주는
//모델 객체 생성
@Getter
@Setter
public class ModelView {
    private String viewName; //뷰의 논리적인 이름을 저장
    private Map<String, Object> model = new HashMap<>(); //setAttribute를 할때 넣을 값들 ex)"members",member

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
