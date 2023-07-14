package taeeun.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import taeeun.hello.boot.spring5boot.dao.BoardDAOImpl;
import taeeun.hello.boot.spring5boot.model.Board;
import taeeun.hello.boot.spring5boot.service.BoardService;
import taeeun.hello.boot.spring5boot.service.BoardServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    @DisplayName("BoardService save Test")
    @Transactional
    void saveBoard() {
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("테스트");
        b.setContents("테스트");
        b.setIpaddr("127.0.0.1");

        boolean result = bsrv.saveBoard(b);
        assertEquals(result,true);
    }

    @Test
    @DisplayName("BoardService countPage Test")
    void countPage() {
        int result = bsrv.countBoard();

        assertNotNull(result);
    }

    @Test
    @DisplayName("BoardService findBoard Test")
    void findBoard() {
        String ftype = "title";
        String fkey = "생제르망";
        int cpg = 1;

        List<Board> results = bsrv.readFindBoard(cpg, ftype,fkey);

        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardService countFindBoard Test")
    void countFindBoard() {
        String ftype = "title";
        String fkey = "생제르망";

       int result = bsrv.countFindBoard(ftype,fkey);

        assertNotNull(result);
    }

}