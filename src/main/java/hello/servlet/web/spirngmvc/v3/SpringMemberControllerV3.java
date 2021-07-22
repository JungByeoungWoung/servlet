package hello.servlet.web.spirngmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    //@RequestMapping(value = "/new-form",method = RequestMethod.GET)
    @GetMapping(value = "/new-form")
    public String newForm(){
        return "new-form";
    }
    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping(value = "/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
            //HttpServletRequest request,HttpServletResponse response를
            //스프링 어노테이션에서  @RequestParam으로 받으면
            //타입에 맞게 다 변환 시켜서 전달해준다.
    ){
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
