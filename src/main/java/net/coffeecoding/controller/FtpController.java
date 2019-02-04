package net.coffeecoding.controller;

import net.coffeecoding.model.FileModel;
import org.apache.commons.io.FilenameUtils;
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

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class FtpController {

    @Autowired
    private FTPClient ftpClient;
    private String serverName;
    private List<FileModel> fileModels;


    @GetMapping("/error")
    public String showErrorPage() {
        return "error-page";
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

    @GetMapping("/demo")
    public String getAllFiles(Model model) {

        if (ftpClient.isConnected()) {

            List<FTPFile> ftpFiles = null;
            try {
                ftpFiles = Arrays.asList(ftpClient.listFiles());
            } catch (IOException e) {
            }
            List<FileModel> ftpFileClients = new ArrayList<>();
            int id = 0;
            String type;
            for (FTPFile ftpFile : ftpFiles) {

                if (ftpFile.getType() == 1)
                    type = "DIRECTORY";
                else
                    type = "FILE";

                if (!(ftpFile.getName().equals(".") || ftpFile.getName().equals(".."))) {
                    ftpFileClients.add(
                            new FileModel(id,
                                    ftpFile.getName(),
                                    type,
                                    roundDouble2precision((double) ftpFile.getSize() / (1024 * 1024), 2) + " MB"));
                    id++;
                }
            }

            model.addAttribute("files", ftpFileClients);
            this.fileModels = ftpFileClients;
            model.addAttribute("serverName", serverName);

            return "ftp-form";
        } else {
            return "redirect:/login";
        }


    }

    @PostMapping("/file")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("name") String fileName) {

        String remoteFile = null;
        File downloadFile = null;
        InputStreamResource resource = null;

        try {
            remoteFile = ftpClient.printWorkingDirectory() + "/" + fileName;
            downloadFile = new File("tmp/" + fileName);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            ftpClient.retrieveFile(remoteFile, outputStream);
            outputStream.close();
            resource = new InputStreamResource(new FileInputStream(downloadFile));
        } catch (IOException e) {
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
    public String changeWorkingDirectory(@RequestParam("name") String name) {
        try {
            this.ftpClient.changeWorkingDirectory(name);
        } catch (IOException e) {
        }
        return "redirect:/demo";
    }

    @GetMapping("/back")
    public String changeToParentDirectory() {
        if (ftpClient.isConnected()) {
            try {
                this.ftpClient.changeToParentDirectory();
            } catch (IOException e) {
            }
            return "redirect:/demo";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/new-file")
    public String newFileGET(Model model) {
        if (ftpClient.isConnected()) {
            FileModel ftpFileClient = new FileModel();
            model.addAttribute("ftpFileClient", ftpFileClient);
            return "new-file-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/new-file")
    public String newFilePOST(@RequestParam("fileName") String fileName,
                              @RequestParam("fileContent") String fileContent,
                              Model model) {
        File firstLocalFile;
        try {
            firstLocalFile = new File("tmp/" + fileName + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(firstLocalFile));
            writer.write(fileContent);
            writer.close();

            String firstRemoteFile = firstLocalFile.getName();
            InputStream inputStream = new FileInputStream(firstLocalFile);

            if (ftpClient.storeFile(firstRemoteFile, inputStream)) {
                model.addAttribute("success", "File created successfully.");
            } else {
                model.addAttribute("error", "Cannot send a file to this directory - permission denied.");
            }
            inputStream.close();
        } catch (IOException e) {
            model.addAttribute("error", "Cannot send a file to this directory - permission denied.");
            return "new-file-form";
        }
        firstLocalFile.delete();
        return "new-file-form";
    }

    @GetMapping("/new-directory")
    public String newDirectoryGET() {
        if (ftpClient.isConnected()) {
            return "new-directory-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/new-directory")
    public String newDirectoryPOST(@RequestParam("dirName") String dirName, Model model) {


        String dirToCreate = null;
        boolean success = false;
        try {
            dirToCreate = ftpClient.printWorkingDirectory() + "/" + dirName;
            success = ftpClient.makeDirectory(dirToCreate);
        } catch (IOException e) {
            return "new-directory-form";
        }
        System.out.println();
        if (success) {
            model.addAttribute("success", "Successfully created directory: " + dirToCreate);
        } else {
            model.addAttribute("error", "Failed to create directory. Server's reply: " + showFTPServerResponse(ftpClient));
        }
        return "new-directory-form";
    }

    @GetMapping("/send-file")
    public String sendFileGET() {
        if (ftpClient.isConnected()) {
            return "send-file-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/send-file")
    public String sendFilePOST(@RequestParam("file") MultipartFile multipartFile, Model model) {
        File file = null;
        try {
            if (multipartFile.getOriginalFilename().isEmpty()) {
                model.addAttribute("error", "Please select a valid file!");
            } else if (multipartFile.getSize() > 15728640) {
                model.addAttribute("error", "File can not be larger than 15 MB!");
            } else {
                file = new File("tmp/" + multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);
                InputStream inputStream = new FileInputStream(file);

                if (ftpClient.storeFile(multipartFile.getOriginalFilename(), inputStream)) {
                    model.addAttribute("success", "File " + multipartFile.getOriginalFilename() + " uploaded successfully.");
                } else {
                    model.addAttribute("error", "Cannot send a file to this directory - permission denied.");
                }

                inputStream.close();
            }
        } catch (IOException e) {
            return "send-file-form";
        }
        file.delete();
        return "send-file-form";
    }


    @GetMapping("/delete-file/{id}")
    public String deleteFile(@PathVariable String id, Model model) {

        if (ftpClient.isConnected()) {
            String fileName = fileModels.get(Integer.parseInt(id)).getName();
            boolean delete = false;

            try {
                delete = ftpClient.deleteFile(ftpClient.printWorkingDirectory() + "/" + fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Failed to remove: " + fileName + ".");
            }
            if (delete) {
                model.addAttribute("success", fileName + " was successfully deleted");
            } else {
                model.addAttribute("error", "Failed to remove: " + fileName + ".");
            }
        } else {
            return "redirect:/login";
        }


        return "redirect:/demo";
    }

    @GetMapping("/delete-directory/{id}")
    public String deleteDirectory(@PathVariable String id, Model model) {


        if (ftpClient.isConnected()) {

            String fileName = fileModels.get(Integer.parseInt(id)).getName();
            boolean removed = removeDirectory(ftpClient, fileName, "");
            if (removed) {
                model.addAttribute("success", fileName + " was successfully deleted");
            } else {
                model.addAttribute("error", "Failed to remove: " + fileName + ".");
            }

            return "redirect:/demo";
        } else {
            return "redirect:/login";
        }
    }

    public static boolean removeDirectory(FTPClient ftpClient, String parentDir,
                                          String currentDir) {
        boolean removed = false;

        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        try {
            FTPFile[] subFiles = ftpClient.listFiles(dirToList);

            if (subFiles != null && subFiles.length > 0) {
                for (FTPFile aFile : subFiles) {
                    String currentFileName = aFile.getName();
                    if (currentFileName.equals(".") || currentFileName.equals("..")) {
                        // skip parent directory and the directory itself
                        continue;
                    }
                    String filePath = parentDir + "/" + currentDir + "/"
                            + currentFileName;
                    if (currentDir.equals("")) {
                        filePath = parentDir + "/" + currentFileName;
                    }

                    if (aFile.isDirectory()) {
                        // remove the sub directory
                        removeDirectory(ftpClient, dirToList, currentFileName);
                    } else {
                        // delete the file
                        boolean deleted = ftpClient.deleteFile(filePath);
                        if (deleted) {
                            System.out.println("DELETED the file: " + filePath);
                        } else {
                            System.out.println("CANNOT delete the file: "
                                    + filePath);
                        }
                    }
                }

                // finally, remove the directory itself
                removed = ftpClient.removeDirectory(dirToList);
                if (removed) {
                    System.out.println("REMOVED the directory: " + dirToList);
                } else {
                    System.out.println("CANNOT remove the directory: " + dirToList);
                }
            }
        } catch (IOException e) {
        }

        return removed;
    }

    @GetMapping("/rename-file")
    public String renameFileGET(@RequestParam("id") String id, Model model) {

        if (ftpClient.isConnected()) {
            FileModel fileModel = fileModels.get(Integer.parseInt(id));
            model.addAttribute("fileModel", fileModel);

            return "change-name-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/rename-file") //zrobić inaczej bez parametru - pobrać starą nazwe z listy po id
    public String renameFilePOST(@RequestParam("id") String id,
                                 @RequestParam("name") String name,
                                 Model model) {

        String newDir = name;
        String oldDir = fileModels.get(Integer.parseInt(id)).getName();
        String extension = FilenameUtils.getExtension(oldDir);
        boolean rename;

        try {
            if (fileModels.get(Integer.parseInt(id)).getType().equals("DIRECTORY")) {
                rename = ftpClient.rename(oldDir, newDir);
            } else {
                rename = ftpClient.rename(oldDir, newDir + "." + extension);
            }
        } catch (IOException e) {
            return "redirect:/demo";
        }

        if (rename) {
            model.addAttribute("success", oldDir + " was successfully renamed to: " + newDir + ".");
        } else {
            model.addAttribute("error", "Failed to rename: " + oldDir + ".");
        }


        return "redirect:/demo";
    }


    @GetMapping("/logout")
    public String logout(Model model) {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            return "ftp-form-login";
        }
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

}