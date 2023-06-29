package ysu.szx.sys.petsys.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ysu.szx.sys.petsys.Dao.StatusDao;
import ysu.szx.sys.petsys.Pojo.Results;
import ysu.szx.sys.petsys.Utils.PictureUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/image")
public class PictureController{
    @Autowired
    private StatusDao statusDao;
    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        ClassPathResource imageFile = new ClassPathResource("/picture/" + name + ".png");
        byte[] imageBytes = Files.readAllBytes(Path.of(imageFile.getURI()));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
    @GetMapping("/avatar/{name}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable String name) throws IOException {
        ClassPathResource imageFile = new ClassPathResource("/picture/" + name + ".png");
        byte[] imageBytes = Files.readAllBytes(Path.of(imageFile.getURI()));
        byte[] resizedImageBytes = PictureUtils.resizeImage(imageBytes, 40, 40);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resizedImageBytes);
    }
    @PostMapping("/Upload")
    public Results UploadImage(HttpServletRequest request, MultipartFile file) throws Exception{
        Integer picId = statusDao.GetPictureID() + 1;
        statusDao.AddPictureId(1);
        try {
            String filename = picId.toString() + ".png";
            ClassPathResource resource = new ClassPathResource("/picture");
            File aimFile = new File(resource.getFile().getPath() + "/" + filename);
            file.transferTo(aimFile);
        }finally{
            try {
                request.getInputStream().close();
            } catch (IOException e) {
                // ignore exception
            }
        }
        return Results.Success(picId);
    }
}