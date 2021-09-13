package com.example.demo.entity;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="emp")
public class Employee {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
}