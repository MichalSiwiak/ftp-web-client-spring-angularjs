package org.coffecode.controller;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.net.ftp.FTPClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class CreditController {

    @Autowired
    private FTPClient ftpClient;
    private String defaultDirectory;


    @GetMapping("/demo")
    public String getDemo() {
        return "ftp-form";
    }

    @PostConstruct
    public void init() throws IOException {
        defaultDirectory = ftpClient.printWorkingDirectory();


        FTPFile[] ftpFiles = ftpClient.listFiles();
        ftpClient.changeWorkingDirectory("coffeecode.cba.pl");
        ftpClient.changeWorkingDirectory("Imagination-Studios-Enterprise.zip");
        System.out.println(ftpClient.printWorkingDirectory());
        for (FTPFile ftpFile : ftpFiles) {
            System.out.println(ftpFile);
        }


    }


    @RequestMapping(value = "/demo/files", consumes = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> sendFileName(@RequestBody String fileName) throws IOException {
        System.out.println(fileName);
        ftpClient.changeWorkingDirectory(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/demo/files", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<FTPFile>> getAllFilesJSON() throws IOException {
        List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
        return new ResponseEntity<>(ftpFiles, HttpStatus.OK);
    }

}