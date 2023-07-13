package taeeun.hello.boot.spring5boot.service;

import taeeun.hello.boot.spring5boot.model.Board;

import java.util.List;

public interface BoardService {

    boolean saveBoard(Board b);

    List<Board> readBoard(Integer cpg);

    Board readOneBoard(String bno);

    int countBoard();

}
