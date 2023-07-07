package taeeun.hello.boot.spring5boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import taeeun.hello.boot.spring5boot.dao.MemberDAO;
import taeeun.hello.boot.spring5boot.dao.MemberDAOImpl;
import taeeun.hello.boot.spring5boot.model.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(MemberDAOImpl.class)

public class MemberDAOUnitTest {

    @Autowired
    private MemberDAO mdao;

    @Test
    @DisplayName("MemberDAO insert Test")
    void insertMember() {

        Member m = new Member(null, "","","",
                "","","","", "",null);


        int result = mdao.insertMember(m);
        System.out.println(result);
        assertEquals(result, 1);    // alt + enter -> 5.9.2
    }


}












