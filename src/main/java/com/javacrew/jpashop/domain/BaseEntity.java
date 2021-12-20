package com.javacrew.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
