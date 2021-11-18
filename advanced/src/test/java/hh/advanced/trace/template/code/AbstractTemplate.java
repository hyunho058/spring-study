package hh.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    //변하지 않는 부분
    public void execute(){
        long startTime = System.currentTimeMillis();
        //비스니스 로직 실행
//        log.info("비즈니스 로직 1 실행");   //해당 부분을  수정해야함
        call(); //상속
        //비스지느 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }

    protected abstract void call(); //변하는 부분을 자식클래스로 만들어 해결
}
