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
<<<<<<< HEAD
import java.io.*;
=======
import java.io.IOException;
>>>>>>> 7ac991745ad40f0161da123c6b9d6e03fffd7398
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class CreditController {

    @Autowired
    private FTPClient ftpClient;


    @PostConstruct
    public void init() throws IOException {
        ftpClient.changeWorkingDirectory("coffeecode.cba.pl");
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            System.out.println(ftpFile.getName());
        }


    }


    @PostMapping("/file")
    public String file(@RequestParam("name") String name) throws IOException, InterruptedException {

        String remoteFile1 = ftpClient.printWorkingDirectory() + "/" + name;
        File downloadFile1 = new File("D:/Downloads/" + name);
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
        outputStream1.close();

        if (success) {
            System.out.println("File #1 has been downloaded successfully.");
        }

<<<<<<< HEAD
        outputStream1.close();

        Thread.sleep(30000);


        return "redirect:/demo";

    }

    @PostMapping("/directory")
    public String directory(@RequestParam("name") String name) throws IOException {
        System.out.println(name);
        this.ftpClient.changeWorkingDirectory(name);
        return "redirect:/demo";

    }

=======
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

>>>>>>> 7ac991745ad40f0161da123c6b9d6e03fffd7398
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
