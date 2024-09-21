package com.fiap.hackathon.external.orm;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@DynamoDBTable(tableName = "Doctor")
public class DoctorORM {

    private Long id;
    private Boolean isActive;
    private String name;
    private LocalDate birthday;
    private String cpf;
    private String email;
    private String password;
    private String contactNumber;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updateTimestamp;
    private String crm;
    private String medicalSpecialty;
}