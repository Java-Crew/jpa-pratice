package com.javacrew.jpashop.domain.item;

import com.javacrew.jpashop.domain.common.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
