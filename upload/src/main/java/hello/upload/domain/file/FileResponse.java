package hello.upload.domain.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.UrlResource;

@Getter
@NoArgsConstructor
public class FileResponse {
    private UrlResource resource;
    private String contentDisposition;

    public FileResponse(UrlResource resource, String contentDisposition){
        this.resource = resource;
        this.contentDisposition = contentDisposition;
    }
}
