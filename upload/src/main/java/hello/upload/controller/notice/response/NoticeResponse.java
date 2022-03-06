package hello.upload.controller.notice.response;

import hello.upload.domain.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoticeResponse {
    private final Long noticeNo;

    public NoticeResponse(Notice notice) {
        this(
                notice.getNoticeNo()
        );
    }
}
