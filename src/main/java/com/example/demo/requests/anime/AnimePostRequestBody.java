package com.example.demo.requests.anime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "The name of this anime can not be empty")
    private String name;
}
