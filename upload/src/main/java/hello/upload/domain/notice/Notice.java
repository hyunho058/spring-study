package hello.upload.domain.notice;

import hello.upload.controller.notice.request.NoticeUpdateRequest;
import hello.upload.domain.file.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue
    private Long noticeNo;

    private String title;
    private String contents;
    private LocalDateTime noticeStartDate;
    private LocalDateTime noticeEndDate;
    private int views;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<File> files = new ArrayList<>();

    @Builder
    public Notice(String title,
                  String contents,
                  LocalDateTime noticeStartDate,
                  LocalDateTime noticeEndDate,
                  int views) {
        this.title = title;
        this.contents = contents;
        this.noticeStartDate = noticeStartDate;
        this.noticeEndDate = noticeEndDate;
        this.views = views;
    }

    public void addFiles(File file){
        files.add(file);
    }

    public Notice updateNotice(NoticeUpdateRequest request, int views){
        this.title = request.getTitle();
        this.contents = request.getContents();
        this.noticeStartDate = request.getNoticeStartDate();
        this.noticeEndDate = request.getNoticeEndDate();
        this.views = views;
        return this;
    }

    public void updateFiles(List<File> files){
        files.clear();
        files.addAll(files);
    }
}
