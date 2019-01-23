package net.coffeecoding.model;

public class FileModel {

    private int id;
    private String name;
    private String type;
    private String size;

    public FileModel(int id, String name, String type, String size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
    }
    public FileModel() { }


    @Override
    public String toString() {
        return "FileModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
