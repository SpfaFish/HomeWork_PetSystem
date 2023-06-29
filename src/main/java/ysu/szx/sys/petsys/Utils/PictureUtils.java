package ysu.szx.sys.petsys.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class PictureUtils {
    public static byte[] resizeImage(byte[] imageBytes, int targetWidth, int targetHeight) throws IOException {
        // 将字节数组解析为 BufferedImage
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        // 创建目标大小的缩略图 BufferedImage
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        // 绘制原始图像到缩略图上
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        // 将缩略图转换为字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);

        // 返回压缩后的字节数组
        return outputStream.toByteArray();
    }
}
