package taeeun.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import taeeun.hello.boot.spring5boot.dao.BoardDAOImpl;
import taeeun.hello.boot.spring5boot.model.Board;
import taeeun.hello.boot.spring5boot.service.BoardService;
import taeeun.hello.boot.spring5boot.service.BoardServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BoardServiceImpl.class, BoardDAOImpl.class})
public class BoardServiceUnitTest {

    // 객체 받아오기
    @Autowired private BoardService bsrv;

    @Test
    @DisplayName("BoardService read Test")
    void readBoard() {
        int cpg = 1;
        List<Board> results = bsrv.readBoard(cpg);

        // System.out.println(results);
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardService readOne Test")
    void readOneBoard() {
        String bno = "4472";

        Board result = bsrv.readOneBoard(bno);

        assertNotNull(result);
    }

}