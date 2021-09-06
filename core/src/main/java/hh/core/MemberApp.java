package hh.core;

import hh.core.member.Grade;
import hh.core.member.Member;
import hh.core.member.MemberService;
import hh.core.member.MemberServiceImp;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

//        MemberService memberService = new MemberServiceImp();
        MemberService memberService = appConfig.memberService();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+memberA.getName());
        System.out.println("find member = "+findMember.getName());
    }
}
