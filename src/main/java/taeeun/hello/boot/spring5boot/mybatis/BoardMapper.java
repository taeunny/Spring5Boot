package taeeun.hello.boot.spring5boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insertBoard(Board b);
    List<Board> selectBoard();

    Board selectOneBoard(String bno);

    List<Board> selectBoard(int stnum);
}

