package hh.advanced.trace.strategy;

import hh.advanced.trace.strategy.code.strategy.ContextV1;
import hh.advanced.trace.strategy.code.strategy.Strategy;
import hh.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hh.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비스니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        //비스지느 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비스니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        //비스지느 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV2() {
        //익명 내부클래스 사용
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV3_lambda() {
        //람다 사용
        ContextV1 context1 = new ContextV1(() ->
            log.info("비즈니스 로직1 실행")
        );
        context1.execute();

        ContextV1 context2 = new ContextV1(() ->
                log.info("비즈니스 로직2 실행")
        );
        context2.execute();
    }
}
