package hh.database.jdbc.service;

import hh.database.jdbc.domain.Member;
import hh.database.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false); //begin transaction

            bizLogic(connection, fromId, toId, money);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(connection);
        }
    }

    private void bizLogic(Connection connection, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(connection, fromId);
        Member toMember = memberRepository.findById(connection, toId);

        memberRepository.update(connection, fromId, fromMember.getMoney() - money);
        validateion(toMember);
        memberRepository.update(connection, toId, toMember.getMoney() + money);
    }

    private void release(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true); //true 인 상태로 connection pool에 반납을 해줘야 다음에 쓸때도 기본 상태 유지(커넥션 풀 고려)
                connection.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }

    private void validateion(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
