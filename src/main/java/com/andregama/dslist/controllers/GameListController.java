package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameListDTO;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameListService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;


    @GetMapping
    public ApiResponse<List<GameListDTO>> findAll() {
        return new ApiResponse<>(
            200,
            "Lista de listas carregada com sucesso",
            gameListService.findAll(),
            null
        );
    }
}
