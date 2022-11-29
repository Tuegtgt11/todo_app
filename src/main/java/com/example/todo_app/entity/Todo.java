package com.example.todo_app.entity;

import com.example.todo_app.entity.myenum.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "todo")
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "text")
    @Lob
    private String detail;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
