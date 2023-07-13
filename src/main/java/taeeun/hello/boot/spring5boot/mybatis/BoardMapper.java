package taeeun.hello.boot.spring5boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<Board> selectBoard();

    int insertBoard(Board b);

    Board selectOneBoard(String bno);

    List<Board> selectBoard(int stnum);

    int updateViewBoard(String bno);

    int selectCountBoard();

    List<Board> selectFindBoard(Map<String, Object> params);

}

