package net.coffeecoding.config;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.logging.Logger;

@Configuration
@PropertySource({"classpath:ftp.properties"})
public class FtpClientConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public FTPClient ftpClient() {

        FTPClient ftpClient = new FTPClient();

        try {
            logger.info("ftp.server=" + env.getProperty("ftp.server"));
            logger.info("ftp.port=" + env.getProperty("ftp.port"));
            ftpClient.connect(env.getProperty("ftp.server"), Integer.parseInt(env.getProperty("ftp.port")));
            logger.info("ftp.user=" + env.getProperty("ftp.user"));
            boolean login = ftpClient.login(env.getProperty("ftp.user"), env.getProperty("ftp.password"));
            System.out.println(login);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ftpClient;
    }

}