<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>coffeecoding.net</title>
    <meta name="description"
          content="Free open source projects present different java solutions using spring, hibernate and other popular frameworks.">
    <meta name="keywords"
          content="java, spring, hibernate, apache, tomcat, coding, programmer, linux, google cloud platform, open source, bootstrap, mysql, java ideas">
    <!-- CSS dependencies -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="https://www.coffeecoding.net/resources/css/now-ui-kit.css" type="text/css">
    <link rel="stylesheet" href="https://www.coffeecoding.net/resources/css/style.css" type="text/css">
    <link rel="icon" href="resources/img/favicon.png">
    <!-- PAGE scripts -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
</head>

<body class="bg-light text-dark">

<div id="wrap">
    <div id="main" class="clear-top">
        <div class="collapse" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-md-7 py-4">
                        <h4>About</h4>
                        <p class="text-info">Free open source projects present different java solutions using spring,
                            hibernate
                            and other popular frameworks.</p>
                    </div>
                    <div class="col-md-3 offset-md-1 py-4">
                        <h4>Contact</h4>
                        <ul class="list-unstyled">
                            <li>
                                <a href="https://pl.linkedin.com/in/michalsiwiak" class="text-secondary"
                                   target="_blank">Follow on
                                    LinkedIn</a>
                            </li>
                            <li>
                                <a href="mailto:info@coffeecoding.net" target="_top" class="text-secondary">Email me</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar sticky-top navbar-dark bg-info">
            <div class="container d-flex justify-content-between">
                <a href="https://www.coffeecoding.net/" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-home fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class=""> HOME
                </text>
                </a>
                <a href="https://github.com/MichalSiwiak/ftp-web-client-spring-angularjs" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-git-square fa-fw d-inline-block lead fa-2x"></i>&nbsp;&nbsp;<text class="">SOURCE
                    CODE
                </text>
                </a>
                <a href="${pageContext.request.contextPath}/demo" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-desktop fa-2x fa-fw lead d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">DEMO VIEW
                </text>
                </a>
                <a href="https://coffeecoding.net/resources/img/cv_msiwiak.pdf" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-address-card fa-2x lead fa-fw d-inline-block"
                        aria-hidden="true"></i>&nbsp;&nbsp;<text class="">RESUME</text>
                </a>
                <a href="/contact" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-envelope fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">CONTACT
                </text>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"><span
                        class="navbar-toggler-icon"></span></button>
            </div>
        </div>
        <div class="text-center py-4 bg-secondary"
             style="  background-image: linear-gradient(to left, rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.9)); background-position: top left;  background-size: 100%;  background-repeat: repeat;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-0">
                        <h1 class="text-left text-primary">FTP CLIENT</h1>
                        <p class="lead text-left">Implementation of basic FTP client functionality using spring mvc and
                            apache
                            commons net.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h5>This application presents simple implementation of basic FTP client functionality using
                            spring mvc and
                            apache commons net.<br></h5>
                        <h5>The application supports operations:</h5>
                        <h5>
                            <ul>
                                <li>downloading files from the server</li>
                                <li>sending files to the server: a maximum content of 15MB</li>
                                <li>editing the name of a file or directory</li>
                                <li>deleting a file or directory</li>
                                <li>creating a new directory</li>
                                <li>creating a new text file with a maximum content of 15MB</li>
                            </ul>
                        </h5>
                        <h5><b>Back End: </b>Java, Spring, Apache Commons Net.</h5>
                        <h5><b>Front End: </b>HTML, CSS, JSP.</h5>
                        <h5>Logging into the server is done by entering the server name port (default port is 21)
                            username and
                            password. The application does not save sensitive user data.</h5>
                        <h5>File operations are handled by the client's HTTP requests and their implementation is
                            included in
                            controller class.</h5>
                        <h5>To run application: git clone
                            https://github.com/MichalSiwiak/ftp-web-client-spring-angularjs.git,
                            upload and run application using tomcat application server or similar.</h5>
                        <h5>Demo View: <a href="https://www.coffeecoding.net/ftp/login">https://www.coffeecoding.net/ftp/login</a>
                        </h5>
                        <h5>Controller class:</h5>
                    </div>
                </div>
                <pre>
                    <code class="java">
@Controller
public class FtpController {

    @Autowired
    private Client client;
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
        boolean login;

        try {
            ftpClient.connect(serverName, Integer.parseInt(portNumber));
            login = ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            this.serverName = serverName;
        } catch (IOException e) {
            model.addAttribute("error", "Unknown host - name or service not known");
            return "ftp-form-login";
        }



        if (login != false) {
            this.client.setFtpClient(ftpClient);
            return "redirect:/demo";
        } else {
            model.addAttribute("error", "Unable to connect to the server - invalid username or password!");
            return "ftp-form-login";
        }
    }

    @GetMapping("/demo")
    public String getAllFiles(Model model) {

        if (client.getFtpClient() != null) {

            List<FTPFile> ftpFiles = null;
            try {
                ftpFiles = Arrays.asList(client.getFtpClient().listFiles());
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
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("name") String fileName, HttpServletResponse response) {
        String remoteFile = null;
        File downloadFile = null;
        InputStreamResource resource = null;
        if (client.getFtpClient() != null) {
            try {
                remoteFile = client.getFtpClient().printWorkingDirectory() + "/" + fileName;
                downloadFile = new File("tmp/" + fileName);
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
                client.getFtpClient().retrieveFile(remoteFile, outputStream);
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
        } else {
            String message = "The session has expired.<a href='https://www.coffeecoding.net/ftp/login'><br>Log-in again</a>";
            InputStream inputStream = new ByteArrayInputStream(message.getBytes());
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(inputStreamResource);
        }
    }

    @PostMapping("/directory")
    public String changeWorkingDirectory(@RequestParam("name") String name) {
        if (client.getFtpClient() != null) {
            try {
                this.client.getFtpClient().changeWorkingDirectory(name);
            } catch (IOException e) {
            }
            return "redirect:/demo";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/back")
    public String changeToParentDirectory() {
        if (client.getFtpClient() != null) {
            try {
                this.client.getFtpClient().changeToParentDirectory();
            } catch (IOException e) {
            }
            return "redirect:/demo";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/new-file")
    public String newFileGET(Model model) {
        if (client.getFtpClient() != null) {
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
        if (client.getFtpClient() != null) {
            File firstLocalFile;
            try {
                firstLocalFile = new File("tmp/" + fileName + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(firstLocalFile));
                writer.write(fileContent);
                writer.close();

                String firstRemoteFile = firstLocalFile.getName();
                InputStream inputStream = new FileInputStream(firstLocalFile);

                if (client.getFtpClient().storeFile(firstRemoteFile, inputStream)) {
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
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/new-directory")
    public String newDirectoryGET() {
        if (client.getFtpClient() != null) {
            return "new-directory-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/new-directory")
    public String newDirectoryPOST(@RequestParam("dirName") String dirName, Model model) {

        if (client.getFtpClient() != null) {
            String dirToCreate = null;
            boolean success = false;
            try {
                dirToCreate = client.getFtpClient().printWorkingDirectory() + "/" + dirName;
                success = client.getFtpClient().makeDirectory(dirToCreate);
            } catch (IOException e) {
                return "new-directory-form";
            }

            if (success) {
                model.addAttribute("success", "Successfully created directory: " + dirToCreate);
            } else {
                model.addAttribute("error", "Failed to create directory. Server's reply: " + showFTPServerResponse(client.getFtpClient()));
            }
            return "new-directory-form";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/send-file")
    public String sendFileGET() {
        if (client.getFtpClient() != null) {
            return "send-file-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/send-file")
    public String sendFilePOST(@RequestParam("file") MultipartFile multipartFile, Model model) {
        File file = null;
        if (client.getFtpClient() != null) {
            try {
                if (multipartFile.getOriginalFilename().isEmpty()) {
                    model.addAttribute("error", "Please select a valid file!");
                } else if (multipartFile.getSize() > 15728640) {
                    model.addAttribute("error", "File can not be larger than 15 MB!");
                } else {
                    file = new File("tmp/" + multipartFile.getOriginalFilename());
                    multipartFile.transferTo(file);
                    InputStream inputStream = new FileInputStream(file);

                    if (client.getFtpClient().storeFile(multipartFile.getOriginalFilename(), inputStream)) {
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
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/delete-file/{id}")
    public String deleteFile(@PathVariable String id, Model model) {

        if (client.getFtpClient() != null) {
            String fileName = fileModels.get(Integer.parseInt(id)).getName();
            boolean delete = false;

            try {
                delete = client.getFtpClient().deleteFile(client.getFtpClient().printWorkingDirectory() + "/" + fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Failed to remove: " + fileName + ".");
            }
            if (delete) {
                model.addAttribute("success", fileName + " was successfully deleted");
            } else {
                model.addAttribute("error", "Failed to remove: " + fileName + ".");
            }


            return "redirect:/demo";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/delete-directory/{id}")
    public String deleteDirectory(@PathVariable String id, Model model) {


        if (client.getFtpClient() != null) {

            String fileName = fileModels.get(Integer.parseInt(id)).getName();
            boolean removed = removeDirectory(client.getFtpClient(), fileName, "");
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

        if (client.getFtpClient() != null) {
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
        if (client.getFtpClient() != null) {
            String newDir = name;
            String oldDir = fileModels.get(Integer.parseInt(id)).getName();
            String extension = FilenameUtils.getExtension(oldDir);
            boolean rename;

            try {
                if (fileModels.get(Integer.parseInt(id)).getType().equals("DIRECTORY")) {
                    rename = client.getFtpClient().rename(oldDir, newDir);
                } else {
                    rename = client.getFtpClient().rename(oldDir, newDir + "." + extension);
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
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(Model model) {
        if (client.getFtpClient() != null) {
            try {
                client.getFtpClient().logout();
                client.getFtpClient().disconnect();
            } catch (IOException e) {
                return "ftp-form-login";
            }
            model.addAttribute("logout", "You have been logged out.");
            return "ftp-form-login";
        } else {
            return "redirect:/login";
        }
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
                    </code>
                </pre>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.11.0/highlight.min.js"></script>
                <script>
                    hljs.initHighlightingOnLoad();
                </script>
            </div>
        </div>
    </div>
</div>
<footer class="footer bg-dark text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>© Copyright 2018 coffeecoding.net - All rights reserved.<br>Contact: info@coffeecoding.net<br>Warsaw PL<br><a
                href="https://www.coffeecoding.net/">Visit the homepage</a>
        </p>
    </div>
</footer>
</body>

</html>