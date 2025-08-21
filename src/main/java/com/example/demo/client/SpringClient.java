package com.example.demo.client;

import com.example.demo.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        //it will be removed
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(entity);

        // method get for object
        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(object);

        //Rest template get using exchange
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new  ParameterizedTypeReference<>() {});
        log.info(exchange.getBody());

        //Rest template get using exchange
        Anime samuraiChamploo = Anime.builder().name("samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("https://localhost/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChamploo, creatJsonHeader()),
                Anime.class);
        log.info("anime saved {} ", samuraiChamplooSaved);

        Anime animeToBeUpdated = samuraiChamplooSaved.getBody();
        assert animeToBeUpdated != null;
        animeToBeUpdated.setName("Samurai Champloo 2");

        //Rest Template Put using exchange
        ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("https://localhost/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, creatJsonHeader()),
                Void.class);
        log.info(samuraiChamplooUpdated);

        //Rest Template Delete using exchange
        ResponseEntity<Void> samuraiChamplooDeleted = new RestTemplate().exchange("https://localhost/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());
        log.info(samuraiChamplooDeleted);

    }

    private static HttpHeaders creatJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
