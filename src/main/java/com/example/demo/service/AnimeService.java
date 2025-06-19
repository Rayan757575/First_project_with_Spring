package com.example.demo.service;

import com.example.demo.domain.Anime;
import com.example.demo.repository.AnimeRepository;
import com.example.demo.requests.anime.AnimePostRequestBody;
import com.example.demo.requests.anime.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id){
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
         Anime anime = Anime.builder().name(animePostRequestBody.getName()).build();
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);
    }
}
