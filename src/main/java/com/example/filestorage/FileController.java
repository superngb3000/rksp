package com.example.filestorage;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String addFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.addFile(file.getOriginalFilename(), file);
        return "redirect:/files";
    }

    @GetMapping
    public String getAll(Model model){
        List<File> files = fileService.getAll();
        model.addAttribute("files", files);
        return "files";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public void downloadFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        File file = fileService.getFile(id);
        FileCopyUtils.copy(file.getStream(), response.getOutputStream());
    }

    @PostMapping("/delete/{id}")
    public String addFile(@PathVariable String id) {
        fileService.deleteFile(id);
        return "redirect:/files";
    }
}