package com.example.demo.service.videoServices;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static com.example.demo.service.UtilsService.removeFileExt;

@Service
public class FrameGrabberService {
    public long generatePreviewPictures(Path filePath, MultipartFile filePreview) throws IOException {
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(filePath.toString())) {
            Java2DFrameConverter converter = new Java2DFrameConverter();

            g.start();
            BufferedImage image;

            if(filePreview==null) {
                for (int i = 0; i < 50; i++) {
                    image = converter.convert(g.grabKeyFrame());
                    if (image != null) {
                        File file = Path.of( removeFileExt(filePath.toString()) + ".jpeg").toFile();
                        ImageIO.write(image, "jpeg", file);
                        break;
                    }
                }
            } else {
                image = ImageIO.read(filePreview.getInputStream());
                File file = Path.of( removeFileExt(filePath.toString()) + ".jpeg").toFile();
                ImageIO.write(image, "jpeg", file);
            }

            long lengthInTime = g.getLengthInTime();
            g.stop();
            return lengthInTime;
        }
    }
}
