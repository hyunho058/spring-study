package hello.upload.controller.notice;

import hello.upload.controller.notice.request.NoticeCreateRequest;
import hello.upload.controller.notice.request.NoticeUpdateRequest;
import hello.upload.controller.notice.response.NoticeResponse;
import hello.upload.domain.file.FileResponse;
import hello.upload.domain.notice.Notice;
import hello.upload.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 추가", description = "공지사항을 추가")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/api/notices", consumes = {"multipart/form-data"})
    public NoticeResponse createNotice(NoticeCreateRequest request, @Nullable @RequestParam("attachments") List<MultipartFile> attachments) throws IOException {
        Notice notice = noticeService.createNotice(request, attachments);
        return new NoticeResponse(notice);
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/api/notices/update", consumes = {"multipart/form-data"})
    public NoticeResponse updateNotice(NoticeUpdateRequest request, @Nullable @RequestParam("attachments") List<MultipartFile> attachments) throws IOException {
        Notice notice = noticeService.updateNotice(request, attachments);
        return new NoticeResponse(notice);
    }

    @GetMapping("/api/attach/{fileNo}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileNo) throws MalformedURLException {
        FileResponse fileResponse = noticeService.downloadAttach(fileNo);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileResponse.getContentDisposition())
                .body(fileResponse.getResource());
    }

}
