package taeeun.hello.boot.spring5boot.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/join")
public class JoinController {

    Logger logger = LogManager.getLogger(JoinController.class);
    @GetMapping("/agree")
    public String list() {
        logger.info("join/agree 호출!!");

        return "join/agree";

    }

}
