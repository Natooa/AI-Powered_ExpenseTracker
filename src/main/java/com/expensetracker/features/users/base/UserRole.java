package com.expensetracker.features.users.base;

import com.expensetracker.features.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserRole.UserRoleId.class)
@ToString(exclude = {"user", "role"})
public class UserRole {
//    @Id
//    @Column(name = "user_id")
//    private Long user;
//
//    @Id
//    @Column(name = "role_id")
//    private Long role;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private Users user;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "role_id", insertable = false, updatable = false)
//    private Role role;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserRole(Users user, Role role) {
        this.user = user;
        this.role = role;
    }

    public static class UserRoleId implements Serializable {
        private Long user;
        private Long role;

        public UserRoleId() {}

        public UserRoleId(Long user, Long role) {
            this.user = user;
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof UserRoleId that)) return false;
            return Objects.equals(user, that.user) && Objects.equals(role, that.role);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, role);
        }
    }
}
