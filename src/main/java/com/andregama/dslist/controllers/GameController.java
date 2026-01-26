package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.exceptions.ResourceNotFoundException;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;


    @GetMapping(value = "/{id}")
    public ApiResponse<GameDTO> findById(@PathVariable Long id) {
        try {
            return new ApiResponse<>(
                200,
                "Jogo carregado com sucesso",
                gameService.findById(id),
                null
            );
        } catch (ResourceNotFoundException e) {
            return new ApiResponse<>(
                404,
                e.getMessage(),
                null,
                null
            );
        }
    }
    

    @GetMapping
    public ApiResponse<List<GameMinDTO>> findAll() {
        return new ApiResponse<>(
            200,
            "Lista de jogos carregada com sucesso",
            gameService.findAll(),
            null
        );
    }
}
