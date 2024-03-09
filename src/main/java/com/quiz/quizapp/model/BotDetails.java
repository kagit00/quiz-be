package com.quiz.quizapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bot_details")
public class BotDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "json")
    private JsonNode botDetailsAsJson;
}
