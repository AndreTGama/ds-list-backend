package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.response.ApiResponse;
import com.andregama.dslist.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

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
