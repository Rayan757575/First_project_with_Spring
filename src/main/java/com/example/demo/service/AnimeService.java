package com.example.demo.service;

import com.example.demo.domain.Anime;
import com.example.demo.repository.AnimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {
    private final static List<Anime> animes;

    static {
        animes= List.of(new Anime(1L, "Boku no hero"), new Anime(2L, "Berseker"));
    }

    public List<Anime> listAll(){
        return animes;
    }

    public Anime findById(Long id){
        return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst().
                orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 1000000));
        animes.add(anime);
        return anime;
    }

    public void delete(Long id) {
        animes.remove(findById(id));
    }

    public void replace(Anime anime) {
        delete(anime.getId());
        animes.add(anime);
    }
}
