package taeeun.hello.boot.spring5boot.dao;

import taeeun.hello.boot.spring5boot.model.Pds;
import taeeun.hello.boot.spring5boot.model.PdsAttach;

import java.util.List;

public interface PdsDAO {

    int insertPds(Pds p);


    int insertPdsAttach(PdsAttach pa);

    List<Pds> selectPds(int stnum);

    int selectCountPds();

    Pds selectOnePds(String pno);

    PdsAttach selectOnePdsAttach(String pno);
}
