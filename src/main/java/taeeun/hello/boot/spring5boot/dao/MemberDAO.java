package taeeun.hello.boot.spring5boot.dao;


import com.fasterxml.jackson.core.JsonProcessingException;
import taeeun.hello.boot.spring5boot.model.Member;
import taeeun.hello.boot.spring5boot.model.Zipcode;

import java.util.List;

public interface MemberDAO {
    int insertMember(Member m);


    List<Member> selectMember();

    List<Zipcode> selectzip(String dong);

    int selectOneUserid(String uid);

    Member selectOneMember(Member m);

}
