package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member("jbw", 20);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }
    @Test
    public void findAll(){
        //given
        Member member1 = new Member("jbw1", 20);
        Member member2 = new Member("jbw2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        //contains를 사용 하면 result 안에 해당 객체들이 있는지 확인 가능
        assertThat(result).contains(member1, member2);
    }
}
