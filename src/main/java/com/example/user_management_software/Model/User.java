package com.example.user_management_software.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "( role='user' or role='admin')")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Cannot be null")
    @Size(min = 4,message = "Length more than4")
    private String name;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Cannot be null")
    @Size(min = 4,message = "Length more than4")
    private String username;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Cannot be null")
    private String password;
    @Column(columnDefinition = "varchar(30) unique")
    @Email
    @NotEmpty(message = "Cannot be null")
    private String email;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Cannot be null")
    @Pattern(regexp = "^(?i)(user|admin)$", message = "Role must be 'user' or 'admin'")
    private String role;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Cannot be null")
    private Integer age;


}
