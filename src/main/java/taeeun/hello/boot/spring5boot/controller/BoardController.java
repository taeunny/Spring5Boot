package taeeun.hello.boot.spring5boot.controller;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taeeun.hello.boot.spring5boot.model.Board;
import taeeun.hello.boot.spring5boot.service.BoardService;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final BoardService bsrv;

    Logger logger = LogManager.getLogger(BoardController.class);

    @GetMapping("/list/{cpg}")
    public String list(Model m , @PathVariable Integer cpg) {
        logger.info("board/list 호출!!");

        m.addAttribute("bds", bsrv.readBoard(cpg));
        m.addAttribute("cpg", cpg);     // 현재 페이지
        m.addAttribute("cntpg",  bsrv.countBoard());     // 총 페이지수
        m.addAttribute("stpg",  ((cpg-1) / 10) * 10 + 1);     // 시작 페이지

        // 만일, 현재페이지가 총페이지수 보다 크다면
        // 1페이지로 강제 이동
        if (cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/board/list/1";

        return "/board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(Model m , @PathVariable String bno) {
        logger.info("board/view 호출!!");

        // 담는 변수는 단수니까 bd 로 작성
        m.addAttribute("bd", bsrv.readOneBoard(bno));

        return "/board/view";
    }

    @GetMapping("/write")
    public String write() {
        logger.info("board/write 호출!!");

        return "/board/write";
    }

   @PostMapping("/write")
    public String writeok(Board b) {
        logger.info("board/writeok 호출!!");
        String returnPage = "redirect:/board/fail";

        if(bsrv.saveBoard(b))
            returnPage = "redirect:/board/list/1";

        return returnPage;
    }

    @GetMapping("/find/{findtype}/{findkey}/{cpg}")
    public String find(Model m , @PathVariable Integer cpg ,
        @PathVariable String findtype , @PathVariable String findkey) {
        logger.info("board/find 호출!!");

        m.addAttribute("bds", bsrv.readFindBoard(cpg, findtype, findkey));
        m.addAttribute("cpg", cpg);
        m.addAttribute("cntpg",  bsrv.countFindBoard(findtype, findkey));
        m.addAttribute("stpg",  ((cpg-1) / 10) * 10 + 1);
        m.addAttribute("fkey",  findkey);   // 검색어
        m.addAttribute("ftype",  findtype);   // 검색종류

        // 만일, 현재페이지가 총페이지수 보다 크다면
        // 1페이지로 강제 이동
        if (cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/board/list/1";

        return "/board/list";
    }
}
