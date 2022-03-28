package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityConstants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

@Service
public class UtilsService {

    public static final Logger LOG = LoggerFactory.getLogger(UtilsService.class);

    private final UserRepository userRepository;

    @Autowired
    public UtilsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email: " + email));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with id: " + id));
    }

    public BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) {
        try {
            int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
            BufferedImage ret = img;
            BufferedImage scratchImage = null;
            Graphics2D g2 = null;

            int w = img.getWidth();
            int h = img.getHeight();

            int prevW = w;
            int prevH = h;

            do {
                if (w > targetWidth) {
                    w /= 2;
                    w = (w < targetWidth) ? targetWidth : w;
                }
                if (h > targetHeight) {
                    h /= 2;
                    h = (h < targetHeight) ? targetHeight : h;
                }
                if (scratchImage == null) {
                    scratchImage = new BufferedImage(w, h, type);
                    g2 = scratchImage.createGraphics();
                }

                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

                prevW = w;
                prevH = h;
                ret = scratchImage;
            } while (w != targetWidth || h != targetHeight);

            if (g2 != null) {
                g2.dispose();
            }
            if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
                scratchImage = new BufferedImage(targetWidth, targetHeight, type);
                g2 = scratchImage.createGraphics();
                g2.drawImage(ret, 0, 0, null);
                g2.dispose();
                ret = scratchImage;
            }
            return ret;

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private File multipartFileToFile(MultipartFile multiFile) {

        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = null;
        try {

            file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // After operating the above files, you need to delete the temporary files generated in the root directory
            File f = new File(file.toURI());
            f.delete();
        }
        return  file;
    }

    public static int binSearch_r (int[] data, int value, int from, int to) {
        if (from <= to) {
            int middle = (from+to)/2;

            if (data[middle] > value) {
                return binSearch_r(data, value, from, middle - 1);
            } else if (data[middle] < value) {
                return binSearch_r(data, value, middle+1, to);
            }
            return middle;
        }
        return -1;
    }

    public static String removeFileExt(String fileName) {
        int extIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, extIndex);
    }

    public void deleteDir(File file) {
        //функция удаления папки, сначало удаляем все файлы из папки (при их налмчии), а потом и саму папку
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    public String getTokenFromString(String bearToken) {
        // get JWTToken from head of request, that we have from frontend to server
        //String bearToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // space from TOKEN_PREFIX
            //return bearToken.split(" ")[1];
            //a = a.replace("{bbbbbb}", "");
            return bearToken.replace(SecurityConstants.TOKEN_PREFIX,"");
        }
        return null;
    }

    public boolean validateToken(String token) {
        // if token is ok return true? if some error occured return false
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex
        ) {
            ex.printStackTrace();
            return false;
        }
    }
}
