package com.andregama.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregama.dslist.dto.GameListDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.dto.ReplacementDTO;
import com.andregama.dslist.dto.GameList.Request.PutGameListRequestDTO;
import com.andregama.dslist.dto.GameList.Request.StoreGameListRequestDTO;
import com.andregama.dslist.responses.ApiResponse;
import com.andregama.dslist.services.GameListService;
import com.andregama.dslist.services.GameService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping
    public ApiResponse<GameListDTO> post(@RequestBody StoreGameListRequestDTO entity) {
        try {
            GameListDTO dto = gameListService.save(entity);
            return new ApiResponse<>(
                    201,
                    "Lista salva com sucesso",
                    dto,
                    null);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(
                    400,
                    e.getMessage(),
                    null,
                    null);
        } catch (Exception e) {
            return new ApiResponse<>(
                    500,
                    e.getMessage(),
                    null,
                    null);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<GameListDTO> put(@PathVariable Long id, @RequestBody PutGameListRequestDTO dto) {
        try {
            GameListDTO updatedDto = gameListService.update(id, dto);
            return new ApiResponse<>(
                    200,
                    "Lista atualizada com sucesso",
                    updatedDto,
                    null);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(
                    400,
                    e.getMessage(),
                    null,
                    null);
        } catch (Exception e) {
            return new ApiResponse<>(
                    500,
                    e.getMessage(),
                    null,
                    null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        try {
            gameListService.delete(id);
            return new ApiResponse<>(
                    200,
                    "Lista deletada com sucesso",
                    null,
                    null);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(
                    400,
                    e.getMessage(),
                    null,
                    null);
        } catch (Exception e) {
            return new ApiResponse<>(
                    500,
                    e.getMessage(),
                    null,
                    null);
        }
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
                    null);
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
                    null);
        }
    }
}