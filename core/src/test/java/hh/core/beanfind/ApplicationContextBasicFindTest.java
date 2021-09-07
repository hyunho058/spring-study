package hh.core.beanfind;

import hh.core.AppConfig;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void fidnBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImp.class); //구현에 의존
        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 없음")
    void findBeanByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxxxxx",MemberService.class));
    }
}
