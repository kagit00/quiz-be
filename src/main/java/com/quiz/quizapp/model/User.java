package com.quiz.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.quizapp.validation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ValidUser
    private String username;
    @ValidFirstName
    @Column(name = "first_name")
    private String firstName;
    @ValidLastName
    @Column(name = "last_name")
    private String lastName;
    @ValidEmail
    private String email;
    private String password;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @ValidPhone
    private String phone;
    private String about;
    @Column(name = "profile_url")
    private String profileUrl;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> roles = new HashSet<>();
    private boolean enabled = true;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        this.roles.forEach(role -> authorities.add(new Authority(role.getRole().getRoleName())));
        return authorities;
    }
}
