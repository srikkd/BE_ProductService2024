package dev.naman.productservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
//    @Type(type = "uuid-char")
    private UUID uuid;
}
