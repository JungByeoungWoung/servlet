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
    private String viewName; //논리적인 이름을 저장
    private Map<String, Object> model = new HashMap<>();//모델 즉, 데이터들을 담는 역할(request객체의역할)

    //뷰의 이름만 가지는 생성자 생성
    public ModelView(String viewName){
        this.viewName = viewName;
    }
}
