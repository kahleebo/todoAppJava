package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class TodoItem {
    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Integer priority;

    @Override
    public String toString() {
        return id + ", " + title + ", " + description + ", " + completed + ", " + priority;
    }
}
