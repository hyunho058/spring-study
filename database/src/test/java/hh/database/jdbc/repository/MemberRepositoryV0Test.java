package hh.database.jdbc.repository;

import hh.database.domani.Member;
import hh.database.repository.MemberRepositoryV0;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.NoSuchElementException;

class MemberRepositoryV0Test {
    Logger log = (Logger) LoggerFactory.getLogger(MemberRepositoryV0Test.class);
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV4", 10000);
        repository.save(member);

        //find by id
        Member findMember = repository.findById(member.getMemberId());
        Assertions.assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
        log.info("findMember = {}", findMember);

        //update
        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        Assertions.assertThatThrownBy(
                        () -> repository.findById(member.getMemberId())
                )
                .isInstanceOf(NoSuchElementException.class);
    }
}