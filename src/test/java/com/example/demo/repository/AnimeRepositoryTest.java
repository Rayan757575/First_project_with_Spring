package com.example.demo.repository;

import com.example.demo.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime |Repository")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    //test for method post
    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull(); // verify if the object is not null

        Assertions.assertThat(animeSaved.getId()).isNotNull(); // verify if the object's id is not null

        // verify if the value that will be saved is equal the expected value
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    //test for method put
    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Updated Anime");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull(); // verify if the object is not null

        Assertions.assertThat(animeUpdated.getId()).isNotNull(); // verify if the object's id is not null

        // verify if the value that will be updated is equal the expected value
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }


    //test for method delete
    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    //test for method find by name
    @Test
    @DisplayName("Find by name returns list of anime when successful")
    void findByName_ReturnsListOfAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);
    }

    //test for when method find by name not found an object
    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenAnimeNotFound(){
        List<Anime> animes = this.animeRepository.findByName("x-x-x-x");

        Assertions.assertThat(animes).isEmpty();
    }


    //anime created to make the tests
    private Anime createAnime(){
        return Anime.builder().name("Hajime no Ipo").build();
    }
}