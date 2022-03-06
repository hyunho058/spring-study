package hello.upload.repository;

import hello.upload.domain.file.File;
import hello.upload.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    void deleteAllByNotice(Notice notice);
}
