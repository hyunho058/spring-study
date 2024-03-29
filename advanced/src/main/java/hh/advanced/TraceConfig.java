package hh.advanced;

import hh.advanced.trace.callback.TraceTemplate;
import hh.advanced.trace.logtrace.LogTrace;
import hh.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

//    @Bean
//    public TraceTemplate traceTemplate(LogTrace logTrace){
//        return new TraceTemplate(logTrace);
//    }
}
