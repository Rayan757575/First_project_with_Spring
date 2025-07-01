package com.example.demo.mapper.anime;

import com.example.demo.domain.Anime;
import com.example.demo.requests.anime.AnimePostRequestBody;
import com.example.demo.requests.anime.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public abstract class AnimeMapper {
    public static AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
