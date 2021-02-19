package msc.ais.soleerp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 18/2/2021.
 */
public class User {

    private int id;
    private String username;
    private char[] password;
    private String email;
    private LocalDate createdDate;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.createdDate = builder.createdDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int id;
        private String username;
        private char[] password;
        private String email;
        private LocalDate createdDate;

        public Builder userId(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(char[] password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder createdDate(LocalDate date) {
            this.createdDate = date;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password=" + Arrays.toString(password) +
            ", email='" + email + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}