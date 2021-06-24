package com.example.Resume;

public class Comment {
    private String Author;
    private String Comment;
    public Comment(String author, String comment) {
        this.Author = author;
        this.Comment = comment;
    }
    public String getAuthor() {
        return this.Author;
    }
    public String getComment() {
        return this.Comment;
    }
}
