package br.edu.famper.employeeregistration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Table(name = "tbl_employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "code")
    private long code;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "department")
    private String department;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "admissionDate")
    private Calendar admissionDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
