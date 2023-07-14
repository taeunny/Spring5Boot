package taeeun.hello.boot.spring5boot.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import taeeun.hello.boot.spring5boot.model.PdsAttach;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Component()  // 일반클래스는 Component로 관리
public class PdsUtils {

    // 첨부파일 저장위치
    @Value("${saveDir}") private String saveDir;

    Logger logger = LogManager.getLogger(PdsUtils.class);

    public PdsAttach processUpload(MultipartFile attach) {
        PdsAttach pa = new PdsAttach();

        // 업로드할 파일 정보 알아내기 - 첨부파일명
        pa.setFname(makeUUID() + attach.getOriginalFilename());

        // 업로드할 파일 정보 알아내기 - 확장자 추출
        // 파일명 : abc123.zip -> 확장자 : zip
        // 파일명.split(".")[1] -> 구조가 배열이니까 .을 기준으로 0은 앞쪽, 1은 뒷쪽
        //
        // 만약 파일명이 아래와 같다면? (복잡한 형태)
        // abc123.987xyz.zip -> 확장자 : zip
        // 파일명.substring(lastIndexOf(".")+1)
        int pos = pa.getFname().lastIndexOf(".") + 1;
        pa.setFtype(pa.getFname().substring(pos));


        // 업로드할 파일 정보 알아내기 - 파일 크기 (1GB = 1024MB)
        pa.setFsize(attach.getSize()/1024 +"");

        // 첨부파일을 지정한 위치에 저장
        String savepath = saveDir + pa.getFname();
        try {
            attach.transferTo((new File(savepath)));
        } catch (IOException e) {
            logger.error("첨부파일 처리중 오류발생!!");
            e.printStackTrace();
        }


        return pa;

    }

    private String makeUUID() {
        // LocalDate.now : 연월일 출력, LocalTime.now : 시분초 출력
        String uuid = LocalDate.now() + "" + LocalTime.now();

        // - : . 표시를 찾아서 제거한다는 의미!
        uuid.replace("-","")
                .replace(":","")
                .replace(".", "");

        return uuid;
    }

}
