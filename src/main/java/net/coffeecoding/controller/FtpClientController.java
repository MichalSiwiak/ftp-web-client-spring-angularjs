package net.coffeecoding.controller;

import net.coffeecoding.model.FtpFileClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.net.ftp.FTPClient;
import javax.annotation.PostConstruct;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class FtpClientController {

    @Autowired
    private FTPClient ftpClient;


    @PostConstruct
    public void init() throws IOException {
        ftpClient.changeWorkingDirectory("coffeecode.cba.pl");
    }

    @PostMapping("/file")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("name") String fileName) {

        String remoteFile = null;
        File downloadFile = null;
        InputStreamResource resource = null;

        try {
            remoteFile = ftpClient.printWorkingDirectory() + "/" + fileName;
            downloadFile = new File("/tmp/" + fileName);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
            resource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                /*.contentLength(remoteFile.length())*/
                .body(resource);
    }

    @PostMapping("/directory")
    public String directory(@RequestParam("name") String name) throws IOException {
        this.ftpClient.changeWorkingDirectory(name);
        return "redirect:/demo";
    }

    @GetMapping("/back")
    public String back() throws IOException {
        this.ftpClient.changeToParentDirectory();
        return "redirect:/demo";
    }

    @GetMapping("/demo")
    public String getAllFiles(Model model) throws IOException {
        List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
        List<FtpFileClient> ftpFileClients = new ArrayList<>();
        int id = 0;
        for (FTPFile ftpFile : ftpFiles) {


            ftpFileClients.add(
                    new FtpFileClient(id, false, ftpFile.getName(), ftpFile.getType(), ftpFile.getSize(), ftpFile.getTimestamp()));
        }
        model.addAttribute("files", ftpFileClients);
        return "ftp-form";
    }


}
