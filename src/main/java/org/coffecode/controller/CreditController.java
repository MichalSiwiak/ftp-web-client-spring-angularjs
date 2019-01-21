package org.coffecode.controller;

import org.apache.commons.net.ftp.FTPFile;
import org.coffecode.model.FTPFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.net.ftp.FTPClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class CreditController {

    @Autowired
    private FTPClient ftpClient;


    @PostConstruct
    public void init() throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            System.out.println(ftpFile);
        }

        ftpClient.changeWorkingDirectory("coffeecode.cba.pl");
    }


    @GetMapping("/file")
    public String file(@RequestParam("name") String name) {
        System.out.println(name);
        return "ftp-form";

    }

    @GetMapping("/directory")
    public String directory(@RequestParam("name") String name) {
        System.out.println(name);
        return "ftp-form";

    }

    @GetMapping("/demo")
    public String getAllFiles(Model model) throws IOException {
        List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
        List<FTPFileClient> ftpFileClients = new ArrayList<>();
        int id = 0;
        for (FTPFile ftpFile : ftpFiles) {
            ftpFileClients.add(
                    new FTPFileClient(id, false, ftpFile.getName(), ftpFile.getType(), ftpFile.getSize(), ftpFile.getTimestamp()));
        }
        model.addAttribute("files", ftpFileClients);
        return "ftp-form";
    }


}
