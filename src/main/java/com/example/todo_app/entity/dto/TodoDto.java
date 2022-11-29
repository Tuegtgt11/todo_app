package com.example.todo_app.entity.dto;

import com.example.todo_app.entity.myenum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "text")
    private String title;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
