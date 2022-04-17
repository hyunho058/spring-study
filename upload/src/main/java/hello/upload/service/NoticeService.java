package hello.upload.service;

import hello.upload.controller.notice.request.NoticeCreateRequest;
import hello.upload.controller.notice.request.NoticeUpdateRequest;
import hello.upload.domain.file.File;
import hello.upload.domain.file.FileResponse;
import hello.upload.domain.item.UploadFile;
import hello.upload.domain.notice.Notice;
import hello.upload.file.FileStore;
import hello.upload.repository.FileRepository;
import hello.upload.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final FileRepository fileRepository;
    private final FileStore fileStore;

    @Transactional
    public Notice createNotice(NoticeCreateRequest request, List<MultipartFile> multipartFiles) throws IOException {
        Notice notice = noticeRepository.save(
                Notice.builder()
                        .title(request.getTitle())
                        .contents(request.getContents())
                        .noticeStartDate(request.getNoticeStartDate())
                        .noticeEndDate(request.getNoticeEndDate())
                        .build());
        if (multipartFiles != null) {
            List<UploadFile> attachFiles = fileStore.storeFiles(multipartFiles);
            log.info("file upload test storeImageFiles ================ {}", attachFiles);
            attachFiles.stream().map(file -> fileRepository.save(File.builder()
                            .uploadFileName(file.getUploadFileName())
                            .storeFileName(file.getStoreFileName())
                            .notice(notice)
                            .build()))
                    .collect(Collectors.toList());
        }
        return notice;
    }

    @Transactional
    public Notice updateNotice(NoticeUpdateRequest request, List<MultipartFile> multipartFiles) throws IOException {

        Notice notice = noticeRepository.findById(1L).orElseThrow();

        if (multipartFiles != null) {
            fileRepository.deleteAllByNotice(notice);
            List<UploadFile> attachFiles = fileStore.storeFiles(multipartFiles);
            List<File> files = attachFiles.stream().map(file -> fileRepository.save(File.builder()
                            .uploadFileName(file.getUploadFileName())
                            .storeFileName(file.getStoreFileName())
                            .notice(notice)
                            .build()))
                    .collect(Collectors.toList());
            notice.updateFiles(files);
        }
        return notice.updateNotice(request, 2);
    }

    public FileResponse downloadAttach(Long fileNo) throws MalformedURLException {
        File file = fileRepository.findById(fileNo).orElseThrow(IllegalArgumentException::new);

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(file.getStoreFileName()));
        String encodedUploadFileName = UriUtils.encode(file.getUploadFileName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return new FileResponse(resource, contentDisposition);
    }
}
