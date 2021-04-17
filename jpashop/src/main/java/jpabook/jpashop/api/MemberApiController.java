package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // Create member ---------------------------------------------------------------------------------------------------
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



    // Update member ---------------------------------------------------------------------------------------------------
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    @AllArgsConstructor
    private class UpdateMemberResponse {     //DTO
        private Long id;
        private String name;
    }
    @Data
    private class UpdateMemberRequest {
        private String name;
    }

    // Search Member List ----------------------------------------------------------------------------------------------
    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }
    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class  Result<T>{
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{     //DTO
        private String name;
        private Address address;
    }
}
