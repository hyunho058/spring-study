package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
<<<<<<< HEAD
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){  //Member Entity 를 외부에 노출하는건 안좋은 API 이다.
=======
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){    //Entity( ex)Member )를 외부에 노출하는 것은 좋지 않다.
>>>>>>> 73468f0dfd397168f4767a9446eef11350882930
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
<<<<<<< HEAD
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){  //API 스팩이 바뀌지 않는다.(DTO)
        Member member = new Member();
        member.setName(request.getName());

=======
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){  //API 스팩이 바뀌지 않는 장점이 있다 (DTO)
        Member member = new Member();
        member.setName(request.getName());
>>>>>>> 73468f0dfd397168f4767a9446eef11350882930
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @Data
<<<<<<< HEAD
    static class CreateMemberResponse{  //DTO
=======
    static class CreateMemberResponse{
>>>>>>> 73468f0dfd397168f4767a9446eef11350882930
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
<<<<<<< HEAD

    @Data
    static class CreateMemberRequest{
        private String name;

    }

=======
    @Data
    static class CreateMemberRequest{   //DTO
        private String name;
    }
>>>>>>> 73468f0dfd397168f4767a9446eef11350882930
}
