package hh.advanced.trace.hellotrace;

import hh.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Tes2 {

    @Test
    void begin_end(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status1);
        trace.end(status2);
    }

    @Test
    void begin_exception(){
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello exception1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello exception2");
        trace.exception(status1, new IllegalStateException());
        trace.exception(status2, new IllegalStateException());
    }

}