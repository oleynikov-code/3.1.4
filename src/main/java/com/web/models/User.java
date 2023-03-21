package com.web.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name = "name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column(name = "email")
private String email;

public User() {}

public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
}

public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getName() {
    return firstName;
}

public void setName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email);
}

@Override
public int hashCode() {
    return Objects.hash(id, firstName, lastName, email);
}

@Override
public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
}
}
