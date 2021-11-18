package hh.advanced.trace.strategy;

import hh.advanced.trace.strategy.code.strategy.ContextV2;
import hh.advanced.trace.strategy.code.strategy.Strategy;
import hh.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hh.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2_anonymous_inner_class(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void strategyV3_lambda(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() ->log.info("비즈니스 로직1 실행"));
        contextV2.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
