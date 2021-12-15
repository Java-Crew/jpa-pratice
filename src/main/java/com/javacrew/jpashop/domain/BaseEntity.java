package com.javacrew.jpashop.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
