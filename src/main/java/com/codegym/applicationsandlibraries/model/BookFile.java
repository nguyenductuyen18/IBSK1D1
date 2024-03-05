package com.codegym.applicationsandlibraries.model;

import net.bytebuddy.pool.TypePool;
import org.springframework.web.multipart.MultipartFile;

public class BookFile {
    private int id;
    private String name;
    private MultipartFile image;
    private int numberBook;

    public BookFile() {
    }

    public BookFile(int id, String name, MultipartFile image, int numberBook) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.numberBook = numberBook;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(int numberBook) {
        this.numberBook = numberBook;
    }
}
