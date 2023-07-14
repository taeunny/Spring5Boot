package taeeun.hello.boot.spring5boot.service;

import org.springframework.web.multipart.MultipartFile;
import taeeun.hello.boot.spring5boot.model.Pds;

import java.util.Map;

public interface PdsService {

   int newPds(Pds p);
    boolean newPdsAttach(MultipartFile attach, int pinfo);
}
