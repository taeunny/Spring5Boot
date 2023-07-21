package taeeun.hello.boot.spring5boot.mybatis;

import org.apache.ibatis.annotations.Mapper;
import taeeun.hello.boot.spring5boot.model.*;

import java.util.List;

@Mapper
public interface GalleryMapper {
    int insertGallery(Gallery g);

    int insertGalAttach(GalAttach ga);

    List<Gallery> selectGallery(int stnum);

    Gallery selectOneGallery(String bno);

    int selectCountGallery();

    GalAttach selectOneGalAttach(String gno);

    int lastGalGno();


    //int insertPdsComment(PdsComment pc);

    //List<PdsComment> selectPdsComment(String pno);

//    int updateViewPds(String bno);
//
//    List<Pds> selectFindPds(Map<String, Object> params);
//
//    int countFindPds(Map<String, Object> params);

}

