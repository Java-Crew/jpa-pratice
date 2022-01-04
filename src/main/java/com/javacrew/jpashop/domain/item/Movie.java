package com.javacrew.jpashop.domain.item;

import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Movie extends Item {

    private String director;

    private String actor;
}
