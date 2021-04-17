package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){    //Entity( ex)Member )를 외부에 노출하는 것은 좋지 않다.
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){  //API 스팩이 바뀌지 않는 장점이 있다 (DTO)
        Member member = new Member();
        member.setName(request.getName());

        //khh
        Address address = new Address(request.getCity(), request.getStreet(), request.getZipcode());
        member.setAddress(address);

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }


    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
    @Data
    static class CreateMemberRequest{   //DTO
        private String name;
        //khh
        private String city;
        private String street;
        private String zipcode;
    }

    @Data
    @AllArgsConstructor
    private class UpdateMemberResponse {     //DTO
        private Long id;
        private String name;

//        public UpdateMemberResponse(Long id, String name) {
//            this.id = id;
//            this.name = name;
//        }
    }
    @Data
    private class UpdateMemberRequest {
        private String name;
    }
}
