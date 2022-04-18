package hh.database.repository;

import hh.database.domani.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

class MemberRepositoryV0Test {
    Logger log = (Logger) LoggerFactory.getLogger(MemberRepositoryV0Test.class);
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV2", 10000);
        repository.save(member);

        //find by id
        Member findMember = repository.findById(member.getMemberId());
        Assertions.assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
        log.info("findMember = {}", findMember);
    }
}