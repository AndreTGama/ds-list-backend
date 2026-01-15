package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;


    @GetMapping(value = "/{id}")
    public ApiResponse<GameDTO> findById(@PathVariable Long id) {      
        return new ApiResponse<>(
            200,
            "Jogo carregado com sucesso",
            gameService.findById(id),
            null
        );
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
