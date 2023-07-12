package taeeun.hello.boot.spring5boot.dao;

import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;

public interface BoardDAO {

    // 게시글 쓰기
    int insertBoard(Board board);

    // 게시글 보기
    List<Board> selectBoard();

    // 몇번째 게시글을 볼건지
    Board selectOneBoard(String bno);

}
