package hh.advanced.app.v5;

import hh.advanced.trace.callback.TraceCallback;
import hh.advanced.trace.callback.TraceTemplate;
import hh.advanced.trace.logtrace.LogTrace;
import hh.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    //config에서 bean으로 등록해도 되지만 아래처럼 하면 테스트 코드작성할때 LogTrace를 객체로 생성하기 편하다
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
//        TraceTemplate template = new TraceTemplate(trace);
        return traceTemplate.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
