package net.coffeecoding.controller;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Client {

    private FTPClient ftpClient;

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ftpClient=" + ftpClient +
                '}';
    }
}
