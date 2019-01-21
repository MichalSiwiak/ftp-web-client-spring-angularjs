package net.coffeecoding.model;

import java.util.Calendar;

public class FtpFileClient {

    private int id;
    private boolean checked;
    private String name;
    private int type;
    private long size;
    private Calendar modificationDate;

    public FtpFileClient(int id, boolean checked, String name, int type, long size, Calendar modificationDate) {
        this.id = id;
        this.checked = checked;
        this.name = name;
        this.type = type;
        this.size = size;
        this.modificationDate = modificationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Calendar getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "FTPFile{" +
                "id=" + id +
                ", checked=" + checked +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
