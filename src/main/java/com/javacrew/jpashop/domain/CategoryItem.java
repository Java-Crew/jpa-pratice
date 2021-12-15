package com.javacrew.jpashop.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.javacrew.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CategoryItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public void changeCategory(Category category) {
        if (Objects.nonNull(this.category)) {
            this.category.getCategoryItems().remove(this);
        }
        this.category = category;
        if (Objects.nonNull(this.category) && !this.category.getCategoryItems().contains(this)) {
            this.category.addCategoryItem(this);
        }
    }

    public void changeItem(Item item) {
        if (Objects.nonNull(this.item)) {
            this.item.getCategoryItems().remove(this);
        }
        this.item = item;
        if (Objects.nonNull(this.item) && !this.item.getCategoryItems().contains(this)) {
            this.item.addCategoryItem(this);
        }
    }
}
