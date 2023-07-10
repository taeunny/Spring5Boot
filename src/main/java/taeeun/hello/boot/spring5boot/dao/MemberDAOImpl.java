package taeeun.hello.boot.spring5boot.dao;

import lombok.RequiredArgsConstructor;
import taeeun.hello.boot.spring5boot.dao.MemberDAO;
import taeeun.hello.boot.spring5boot.model.Member;
import taeeun.hello.boot.spring5boot.model.Zipcode;
import taeeun.hello.boot.spring5boot.mybatis.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

    // mybatis를 사용하기 위해 필요한 객체 DI
    // 단, 생성자 주입 방식 사용!
    @Autowired
    final MemberMapper memberMapper;

    @Override
    public int insertMember(Member m) {

        // insert(insert관련맵핑, 매개변수)
        // sqlSession.insert("insertMember",m) -> 기존의 코드임 이렇게 사용하는 방식보다는 아래가 편리함
        // return sqlSession.insert("MemberMapper.insertMember",m);
        return memberMapper.insertMember(m); // 요즘은 인터페이스를 따로 만들어서 불러오는 방식을 사용 -> 가독성이 더 좋음
    }

    @Override
    public List<Member> selectMember() {

        return memberMapper.selectMember();
    }

    @Override
    public List<Zipcode> selectzip(String dong) {


        return memberMapper.findZipcode(dong);
    }

    @Override
    public int selectOneUserid(String uid) {
        return memberMapper.selectOneUserid(uid);
    }
}