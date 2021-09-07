package hh.core;

import hh.core.member.Grade;
import hh.core.member.Member;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImp();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    //AppConfig.class 를
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//getBean(name, type)

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+memberA.getName());
        System.out.println("find member = "+findMember.getName());
    }
}
