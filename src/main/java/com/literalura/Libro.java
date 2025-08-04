package com.literalura;

import java.util.List;

public class Libro {
    private String title;
    private List<String> author_name;
    private Integer first_publish_year;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public Integer getFirst_publish_year() {
        return first_publish_year;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + title +
               "\nAutor(es): " + String.join(", ", author_name) +
               "\nAño de publicación: " + first_publish_year;
    }
}
