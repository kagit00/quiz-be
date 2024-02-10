package com.quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type User role.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    private Role role;
}
