package com.web.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name = "name")
private String firstName;

@Column(name = "last_name")
private String lastName;

@Column(name = "age")
private byte age;

@Column(name = "email")
private String email;

@Size(min = 1, message = "Не меньше 5 знаков")
private String password;

@Transient
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_roles",
        joinColumns = @JoinColumn (name = "user_id"),
        inverseJoinColumns = @JoinColumn (name = "role_id"))
private Set<Role> roles;

public User() {}

public User(String firstName, String lastName, String email, byte age, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.password = password;
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

public byte getAge() {
    return age;
}

public void setAge(byte age) {
    this.age = age;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public void setPassword(String password) {
    this.password = password;
}

public Set<Role> getRoles() {
    return roles;
}

public void setRoles(Set<Role> roles) {
    this.roles = roles;
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
            ", age='" + age + '\'' +
            ", email='" + email + '\'' +
            '}';
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

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
    public boolean isEnabled() {
        return true;
    }
}
