package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameListDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.dto.ReplacementDTO;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameListService;
import com.andregama.dslist.services.GameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public ApiResponse<List<GameListDTO>> findAll() {
        return new ApiResponse<>(
                200,
                "Lista de listas carregada com sucesso",
                gameListService.findAll(),
                null);
    }

    @GetMapping(value = "/{listId}/games")
    public ApiResponse<List<GameMinDTO>> findByList(@PathVariable Long listId) {
        try {
            return new ApiResponse<>(
                200,
                "Lista de jogos carregada com sucesso",
                gameService.findByList(listId),
                null);
        } catch (Exception e) {
            return new ApiResponse<>(
                500,
                e.getMessage(),
                null,
                null
            );
        }
        
    }

    @PostMapping(value = "/{listId}/replacement")
    public ApiResponse<String> move(@PathVariable Long listId, @RequestBody ReplacementDTO request) {
        try {
            gameListService.move(listId, request.getSourceIndex(), request.getDestinationIndex());
            return new ApiResponse<>(
                200,
                "Movimentação realizada com sucesso",
                null,
                null);
        } catch (Exception e) {
            return new ApiResponse<>(
                500,
                e.getMessage(),
                null,
                null
            );
        }
    }

}