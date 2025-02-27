package com.example.writeagain_be.domain;
import lombok.*;
import java.util.Date;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class BlogPost {
    private String title;
    private String link;
    private String content;
    private Date publishedDate;
}
