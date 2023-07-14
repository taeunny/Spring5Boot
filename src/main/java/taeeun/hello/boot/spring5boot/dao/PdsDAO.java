package taeeun.hello.boot.spring5boot.dao;

import taeeun.hello.boot.spring5boot.model.Pds;
import taeeun.hello.boot.spring5boot.model.PdsAttach;

public interface PdsDAO {

    int insertPds(Pds p);


    int insertPdsAttach(PdsAttach pa);
}
