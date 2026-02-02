package com.andregama.dslist.dto.GameList.Response;

import com.andregama.dslist.entities.GameList;

public class GameListResponseDTO {

    private Long id;
    private String name;

    public GameListResponseDTO() {
    }

    public GameListResponseDTO(GameList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
