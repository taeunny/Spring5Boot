package taeeun.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import taeeun.hello.boot.spring5boot.model.Board;
import taeeun.hello.boot.spring5boot.model.Member;
import taeeun.hello.boot.spring5boot.mybatis.BoardMapper;
import taeeun.hello.boot.spring5boot.mybatis.MemberMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperUnitTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("BoardMapper select Test")
    void selectBoard() {
        List<Board> results = boardMapper.selectBoard();

        System.out.println(results);
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardMapper selectOne Test")
    void selectOneBoard() {
        String bno = "4472";

        Board result = boardMapper.selectOneBoard(bno);

        assertNotNull(result);
    }

    @Test
    @DisplayName("BoardMapper insert Test")
    @Transactional
    void insertBoard() {
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("테스트");
        b.setContents("테스트");
        b.setIpaddr("127.0.0.1");

        int result = boardMapper.insertBoard(b);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper update Test")
    @Transactional
    void updateBoard() {
        String bno = "4481";

        int result = boardMapper.updateViewBoard(bno);

        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper countPage Test")
    void countPage() {
        int result = boardMapper.selectCountBoard();

        assertNotNull(result);
    }
}












