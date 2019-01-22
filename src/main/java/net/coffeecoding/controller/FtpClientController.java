package net.coffeecoding.controller;

import net.coffeecoding.model.FtpFileClient;
import net.coffeecoding.model.FtpServerData;
import org.apache.commons.net.ftp.FTP;
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
    private String serverName;


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
        downloadFile.delete();
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
        model.addAttribute("serverName", serverName);
        return "ftp-form";
    }

    @GetMapping("/new-file")
    public String newFileGET(Model model) throws IOException {
        FtpFileClient ftpFileClient = new FtpFileClient();
        model.addAttribute("file", ftpFileClient);
        return "new-file-form";
    }

    @PostMapping("/new-file")
    public String newFilePOST(@ModelAttribute FtpFileClient ftpFileClient) throws IOException {

        return "new-file-form";
    }

    @GetMapping("/new-directory")
    public String newDirectoryGET(Model model) throws IOException {

        return "new-directory-form";
    }

    @PostMapping("/new-directory")
    public String newDirectoryPOST(Model model) throws IOException {

        return "new-directory-form";
    }

    @GetMapping("/send-file")
    public String sendFileGET(Model model) throws IOException {

        return "send-file-form";
    }

    @PostMapping("/send-file")
    public String sendFilePOST(Model model) throws IOException {

        return "send-file-form";
    }

    @GetMapping("/login")
    public String loginGET(Model model) throws IOException {
        return "ftp-form-login";
    }

    @GetMapping("/logout")
    public String logout( Model model) throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
        model.addAttribute("logout", "You have been logged out.");
        return "ftp-form-login";
    }

    @PostMapping("/authenticate")
    public String loginPOST(@RequestParam("serverName") String serverName,
                            @RequestParam("portNumber") String portNumber,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model) {

        FTPClient ftpClient = new FTPClient();
        boolean login = false;

        try {
            ftpClient.connect(serverName, Integer.parseInt(portNumber));
            login = ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            this.serverName = serverName;
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Unable to connect to the server - unknown host!");
            return "ftp-form-login";
        }

        if (login != false) {
            this.ftpClient = ftpClient;
            return "redirect:/demo";
        } else {
            model.addAttribute("error", "Unable to connect to the server - invalid username or password!");
            return "ftp-form-login";
        }


    }


}
