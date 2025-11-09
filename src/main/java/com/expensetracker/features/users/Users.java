package com.expensetracker.features.users;

import com.expensetracker.features.users.base.Role;
import com.expensetracker.features.users.base.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@ToString(exclude = "userRoles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name",  nullable = false, length = 100)
    private String name;

    @Column(name = "surname",   nullable = false,  length = 100)
    private String surname;

    @Column(name = "password",   nullable = false,   length = 255)
    private String password;

    @Column(name = "email",   nullable = false,  unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "created_at",  nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public void addRole(Role role){
        UserRole userRole = new UserRole(this, role);
        userRoles.add(userRole);
    }

    @PrePersist
    public void timeOnCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public Users(String name, String surname, String password, String email, String username) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.username = username;
    }


}
