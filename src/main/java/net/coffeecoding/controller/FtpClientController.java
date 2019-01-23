package net.coffeecoding.controller;

import net.coffeecoding.model.FileModel;
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
import org.springframework.web.multipart.MultipartFile;

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
    private List<FileModel> fileModels;

    private static String showFTPServerResponse(FTPClient ftpClient) {
        StringBuffer FTPServerResponse = new StringBuffer();
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String reply : replies) {
                FTPServerResponse.append(reply).append(" ");
            }
        }
        return FTPServerResponse.toString();
    }


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
    public String changeWorkingDirectory(@RequestParam("name") String name) throws IOException {
        this.ftpClient.changeWorkingDirectory(name);
        return "redirect:/demo";
    }

    @GetMapping("/back")
    public String changeToParentDirectory() throws IOException {
        this.ftpClient.changeToParentDirectory();
        return "redirect:/demo";
    }

    @GetMapping("/demo")
    public String getAllFiles(Model model) throws IOException {
        List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
        List<FileModel> ftpFileClients = new ArrayList<>();
        int id = 0;
        String type;
        for (FTPFile ftpFile : ftpFiles) {

            if (ftpFile.getType() == 1)
                type = "DIRECTORY";
            else
                type = "FILE";

            ftpFileClients.add(
                    new FileModel(id,
                            ftpFile.getName(),
                            type,
                            roundDouble2precision((double) ftpFile.getSize() / (1024 * 1024), 2) + " MB"));
            id++;
        }
        model.addAttribute("files", ftpFileClients);
        this.fileModels = ftpFileClients;
        model.addAttribute("serverName", serverName);
        return "ftp-form";
    }

    @GetMapping("/new-file")
    public String newFileGET(Model model) throws IOException {
        FileModel ftpFileClient = new FileModel();
        model.addAttribute("ftpFileClient", ftpFileClient);
        return "new-file-form";
    }

    @PostMapping("/new-file")
    public String newFilePOST(@RequestParam("fileName") String fileName,
                              @RequestParam("fileContent") String fileContent,
                              Model model) throws IOException {

        File firstLocalFile = new File("E:\\" + fileName + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(firstLocalFile));
        writer.write(fileContent);
        writer.close();

        String firstRemoteFile = firstLocalFile.getName();
        InputStream inputStream = new FileInputStream(firstLocalFile);

        if (ftpClient.storeFile(firstRemoteFile, inputStream)) {
            model.addAttribute("success", "File created successfully.");
        } else {
            model.addAttribute("error", "Unknown error.");
        }

        inputStream.close();
        return "new-file-form";
    }

    @GetMapping("/new-directory")
    public String newDirectoryGET() {
        return "new-directory-form";
    }

    @PostMapping("/new-directory")
    public String newDirectoryPOST(@RequestParam("dirName") String dirName, Model model) throws IOException {
        String dirToCreate = ftpClient.printWorkingDirectory() + "/" + dirName;
        boolean success = ftpClient.makeDirectory(dirToCreate);
        if (success) {
            model.addAttribute("success", "Successfully created directory: " + dirToCreate);
        } else {
            model.addAttribute("error", "Failed to create directory. Server's reply: " + showFTPServerResponse(ftpClient));
        }
        return "new-directory-form";
    }

    @GetMapping("/send-file")
    public String sendFileGET() {
        return "send-file-form";
    }

    @PostMapping("/send-file")
    public String sendFilePOST(@RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {

        if (multipartFile.getOriginalFilename().isEmpty()) {
            model.addAttribute("error", "Please select a valid file!");
        } else if (multipartFile.getSize() > 15728640) {
            model.addAttribute("error", "File can not be larger than 15 MB!");
        } else {
            File file = new File(multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            InputStream inputStream = new FileInputStream(file);

            if (ftpClient.storeFile(multipartFile.getOriginalFilename(), inputStream)) {
                model.addAttribute("success", "File uploaded successfully.");
            } else {
                model.addAttribute("error", "Unknown error.");
            }

            inputStream.close();
        }

        return "send-file-form";
    }

    @GetMapping("/login")
    public String loginGET() {
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

    @GetMapping("/delete-file/{id}")
    public String deleteFile(@PathVariable String id) throws IOException {

        String fileName = fileModels.get(Integer.parseInt(id)).getName();
        if (ftpClient.deleteFile(ftpClient.printWorkingDirectory() + "/" + fileName)) {
            System.out.println("File was deleted successfully.");
            /*model.addAttribute("success", "File was deleted successfully.");*/
        } else {

        }

        return "redirect:/demo";
    }

    @GetMapping("/delete-directory/{id}")
    public String deleteDirectory(@PathVariable String id) throws IOException {

        String fileName = fileModels.get(Integer.parseInt(id)).getName();
        if (ftpClient.removeDirectory(ftpClient.printWorkingDirectory() + "/" + fileName)) {
            System.out.println("Directory was deleted successfully.");
            /*model.addAttribute("success", "Directory was deleted successfully.");*/
        } else {

        }

        return "redirect:/demo";
    }


    @GetMapping("/logout")
    public String logout(Model model) throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
        model.addAttribute("logout", "You have been logged out.");
        return "ftp-form-login";
    }


    public static double roundDouble2precision(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}
