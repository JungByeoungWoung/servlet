package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//회원 저장 로직을 수행하는 서블릿(컨트롤러)
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //model에 저장할 데이터를 저장시켜줘야함
        //.setAttribute("member",member) -> member라는 이름으로 member 객체를 저장
        request.setAttribute("member",member);
        //서블릿(컨트롤러)에서 보여줄 뷰로 이동하기 위한 뷰 경로 작성
        String viewPath = "/WEB-INF/views/save-result.jsp";
        //뷰 경로로 서블릿(컨트롤러)에서 생성한 request와 response 객체를 넘겨줌
       RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }
}
