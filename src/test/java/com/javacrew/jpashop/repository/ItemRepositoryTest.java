package com.javacrew.jpashop.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.javacrew.jpashop.domain.item.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void 아이템_생성_테스트() {
        Album actual = Album.builder()
            .name("앨범")
            .artist("남상우")
            .price(10000)
            .stockQuantity(10)
            .etc("etc")
            .build();
        Album expected = itemRepository.save(actual);
        assertAll(
            () -> assertThat(expected.getName()).isEqualTo(actual.getName()),
            () -> assertThat(expected.getArtist()).contains(actual.getArtist()),
            () -> assertThat(expected.getPrice()).isEqualTo(actual.getPrice()),
            () -> assertThat(expected.getStockQuantity()).isEqualTo(actual.getStockQuantity()),
            () -> assertThat(expected.getEtc()).isEqualTo(actual.getEtc())
        );
    }
}
