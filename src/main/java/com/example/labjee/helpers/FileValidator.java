package com.example.labjee.helpers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.example.labjee.config.Constants.MAX_SIZE_LIMIT;
// Tydzień 9 - 9.7
public class FileValidator {
    public static boolean isFileValidated(Model m, @RequestParam MultipartFile file) {
        boolean validated = true;

        if (!file.isEmpty()) {
            if (file.getSize() > MAX_SIZE_LIMIT) {
                m.addAttribute("imageSize", "");

                validated = false;
            }

            if (!(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg"))) {
                m.addAttribute("imageExtension", "");

                validated = false;
            }
        }
        return validated;
    }
}
