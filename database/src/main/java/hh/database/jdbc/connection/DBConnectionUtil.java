package hh.database.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hh.database.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    //DriverManager 는 라이브러리에 등록된 드라이버 목록을 자동으로 인식한다. 이드라이버들에게 순서대로 다음 정보를 넘겨서 connection을 획득 할 수 있는지 확인

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}",connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
