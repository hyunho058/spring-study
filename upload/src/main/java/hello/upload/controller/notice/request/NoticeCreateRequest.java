package hello.upload.controller.notice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Schema(description = "공지사항 등록 request")
public class NoticeCreateRequest {
    @NotNull
    @Schema(description = "공지사항 제목", example = "공지사항입니다", required = true)
    private final String title;

    @NotNull
    @Schema(description = "공지사항 내용", example = "내용입니다", required = true)
    private final String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "공지 시작날짜 (yyyy-MM-dd HH:mm:ss)", example = "2022-03-04 11:00:00", required = true)
    LocalDateTime noticeStartDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "공지 종료날짜 yyyy-MM-dd HH:mm:ss)", example = "2022-04-04 11:00:00", required = true)
    LocalDateTime noticeEndDate;

    @Builder
    public NoticeCreateRequest(String title,
                               String contents,
                               LocalDateTime noticeStartDate,
                               LocalDateTime noticeEndDate) {
        this.title = title;
        this.contents = contents;
        this.noticeStartDate = noticeStartDate;
        this.noticeEndDate = noticeEndDate;
    }
}
