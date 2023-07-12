package taeeun.hello.boot.spring5boot.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taeeun.hello.boot.spring5boot.model.Board;
import taeeun.hello.boot.spring5boot.mybatis.BoardMapper;

import java.util.List;

@Repository("bdao")
@RequiredArgsConstructor
public class BoardDAOImpl implements BoardDAO {

    // @Autowired 없이 DI구현
    final BoardMapper boardMapper;

    @Override
    public int insertBoard(Board b) {
        return boardMapper.insertBoard(b);
    }

    @Override
    public List<Board> selectBoard(int stnum) {

        return boardMapper.selectBoard(stnum);
    }

    @Override
    public Board selectOneBoard(String bno) {

        return boardMapper.selectOneBoard(bno);
    }

}
