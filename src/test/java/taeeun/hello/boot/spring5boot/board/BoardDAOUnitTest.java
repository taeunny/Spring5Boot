package taeeun.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import taeeun.hello.boot.spring5boot.dao.BoardDAO;
import taeeun.hello.boot.spring5boot.dao.BoardDAOImpl;
import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BoardDAOImpl.class)
public class BoardDAOUnitTest {

    @Autowired private BoardDAO bdao;

    @Test
    @DisplayName("BoardDAO select Test")
    void selectBoard() {
      int cpg = 1;
      int stnum = (cpg-1) * 25;

      List<Board> results = bdao.selectBoard(stnum);

      // System.out.println(results);
      assertNotNull(results);


    }


}