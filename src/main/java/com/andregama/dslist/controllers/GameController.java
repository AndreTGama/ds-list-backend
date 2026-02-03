package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.dto.Game.Request.StoreGameRequestDTO;
import com.andregama.dslist.exceptions.ResourceNotFoundException;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        try {
            return new ApiResponse<>(
                200,
                "Lista de jogos carregada com sucesso",
                gameService.findAll(),
                null
            );
        } catch (Exception e) {
            return new ApiResponse<>(
                500,
                "Erro ao carregar a lista de jogos",
                null,
                null
            );
        }
    }

    @PostMapping()
    public ApiResponse<GameMinDTO> store(@RequestBody StoreGameRequestDTO entity) {
        try {
            GameMinDTO gameMinDTO = gameService.store(entity);
            return new ApiResponse<>(
                200,
                "Jogo salvo com sucesso",
                gameMinDTO,
                null
            );
        } catch (Exception e) {
            return new ApiResponse<>(
                500,
                "Erro ao salvar o jogo",
                null,
                e.getMessage()
            );
        }
    }
    
}
