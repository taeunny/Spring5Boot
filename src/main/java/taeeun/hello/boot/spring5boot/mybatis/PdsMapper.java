package taeeun.hello.boot.spring5boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import taeeun.hello.boot.spring5boot.model.Pds;
import taeeun.hello.boot.spring5boot.model.PdsAttach;
import taeeun.hello.boot.spring5boot.model.PdsComment;

import java.util.List;
import java.util.Map;

@Mapper
public interface PdsMapper {

    int insertPds(Pds b);

    int lastPdsPno();

    int insertPdsAttach(PdsAttach pa);

    List<Pds> selectPds(int stnum);

    Pds selectOnePds(String bno);

    int selectCountPds();

    PdsAttach selectOnePdsAttach(String pno);

    int insertPdsComment(PdsComment pc);

    List<PdsComment> selectPdsComment(String pno);

//    int updateViewPds(String bno);
//
//    List<Pds> selectFindPds(Map<String, Object> params);
//
//    int countFindPds(Map<String, Object> params);

}

