package com.quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Role.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Role {
    @Id
    private long id;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> roles = new HashSet<>();
}
