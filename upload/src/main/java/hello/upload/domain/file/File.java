package hello.upload.domain.file;

import hello.upload.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Long fileNo;

    private String storeFileName;

    private String uploadFileName;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Builder
    public File(String storeFileName,
                String uploadFileName,
                String filePath,
                Notice notice) {
        this.storeFileName = storeFileName;
        this.uploadFileName = uploadFileName;
        this.filePath = filePath;
        this.notice = notice;
    }
}
