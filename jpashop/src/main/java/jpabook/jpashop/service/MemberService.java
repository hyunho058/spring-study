package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //일기 전용에서는 readOnly = true 를 해주면 트렌젝션이 최적화 해서 조회를 한다(성능 향상)
@RequiredArgsConstructor //final 이 있는 필드를 가지고 생성자를 만들어줌
public class MemberService {

//    @Autowired
//    private MemberRepository memberRepository; //필드 인젝션

    //Setter Injection - 테스트 코드를 할때 유연한 장점이 있다.
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //Constructor Injection
//    private final MemberRepository memberRepository;
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
//    @Transactional // default => readOnly = false 일기/쓰기 에서 true 를 하면 쓰기가 안된다.
//    public Long join(Member member) {
//        System.out.println("join() member : "+member.toString());
//        validateDuplicateMember(member); //중복회원 검증
//        memberRepository.save(member);
//        return member.getId();
//    }

    @Transactional // default => readOnly = false 일기/쓰기 에서 true 를 하면 쓰기가 안된다.
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증 memberRepository.save(member);
        memberRepository.save(member);
        return member.getId();
    }



    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); //member.getName() 를 유니크 제약조건을 달아야 한다 (방어코드)
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원이빈다.");
        }
        //조회된 맴버 수 가 0보다 크면 중복 아이디가 있음을 확인하는 로직이 더 최적화된 로직이다.
        //TODO : 조회된 맴버 수가 0 이상이면 예외 발생


        //해당 메서드는 두명의 유저가 동시에 "A" 라는 아이디로 회워가입을 하게되면 문제가 발생한다(A라는 회원이 두명이 된다)
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional  //Transaction 이 끝날때 commit 발생
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);   //영속상태
        member.setName(name);   //변경 감지
    }
}
