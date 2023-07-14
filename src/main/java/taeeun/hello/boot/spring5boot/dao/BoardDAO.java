package taeeun.hello.boot.spring5boot.dao;

import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    // 게시글 쓰기
    int insertBoard(Board board);

    // 게시글 보기
    List<Board> selectBoard(int stnum);

    // 몇번째 게시글을 볼건지
    Board selectOneBoard(String bno);

    int selectCountBoard();

    List<Board> selectFindBoard(Map<String, Object> params);

    int countFindBoard(Map<String, Object> params);

}
