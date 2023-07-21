package taeeun.hello.boot.spring5boot.dao;

import taeeun.hello.boot.spring5boot.model.GalAttach;
import taeeun.hello.boot.spring5boot.model.Gallery;

import java.util.List;

public interface GalleryDAO {

    List<Gallery> selectGallery(int stnum);

    int insertGalAttach(GalAttach ga);

    int insertGallery(Gallery g);
}

