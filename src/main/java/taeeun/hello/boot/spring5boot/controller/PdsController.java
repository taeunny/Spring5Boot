package taeeun.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import taeeun.hello.boot.spring5boot.model.Pds;
import taeeun.hello.boot.spring5boot.service.PdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.util.Map;

@Controller
@RequestMapping("/pds")
@RequiredArgsConstructor
public class PdsController {

    Logger logger = LogManager.getLogger(PdsController.class);

    final PdsService psrv;

    @GetMapping("/list/{cpg}")
    public String list(Model m , @PathVariable Integer cpg) {
        logger.info("pds/list 호출!!");

        m.addAttribute("pds", psrv.readPds(cpg));
        m.addAttribute("cpg", cpg);     // 현재 페이지
        m.addAttribute("cntpg",  psrv.countPds());     // 총 페이지수
        m.addAttribute("stpg",  ((cpg-1) / 10) * 10 + 1);     // 시작 페이지

        if (cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/pds/list/1";

        return "/pds/list";
    }

    @GetMapping("/write")
    public String write() {
        logger.info("pds/write 호출!!");

        return "/pds/write";
    }
    @PostMapping("/write")
    public String writeok(Pds p, MultipartFile attach) {
        logger.info("pds/writeok 호출!!");

        String returnPage = "redirect:/pds/fail";

        // 작성한 게시글을 먼저 디비에 저장하고 글번호를 알아냄
        int pno = psrv.newPds(p);

        // 알아낸 글번호를 이용해서 첨부파일 처리 (디비저장, 업로드)
        if (!attach.isEmpty()) {     // 첨부파일이 존재한다면
            psrv.newPdsAttach(attach, pno);
            returnPage = "redirect:/pds/list/1";
        }
        return returnPage;
    }

    @GetMapping("/view/{pno}")
    public String view(Model m , @PathVariable String pno) {
        logger.info("pds/view 호출!!");

        m.addAttribute("p", psrv.readOnePds(pno));

        return "/pds/view";
    }

    @GetMapping("/down/{pno}")
    public ResponseEntity<Resource> down(@PathVariable String pno) {
        logger.info("pds/down 호출!!");

        // 업로드한 파일에 대한 파일명 알아냄
        String fname = psrv.readOnePdsAttach(pno);

        // 알아낸 파일명 이용해서 헤더와 리소스 객체 생성
        Map<String, Object> objs = psrv.getHeaderResource(fname);


        return ResponseEntity.ok()
                .headers((HttpHeaders)objs.get("header"))
                .body((UrlResource)objs.get("resource"));
    }
}



