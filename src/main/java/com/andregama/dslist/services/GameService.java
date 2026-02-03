package com.andregama.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregama.dslist.dto.GameDTO;
import com.andregama.dslist.dto.GameMinDTO;
import com.andregama.dslist.entities.Game;
import com.andregama.dslist.exceptions.ResourceNotFoundException;
import com.andregama.dslist.projections.GameMinProjection;
import com.andregama.dslist.repositories.BelongToRepository;
import com.andregama.dslist.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BelongToRepository belongToRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com ID: " + id));
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional
    public GameMinDTO store(com.andregama.dslist.dto.Game.Request.StoreGameRequestDTO dto)
    {
        Game entity = new Game();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setScore(dto.getScore());
        entity.setImgUrl(dto.getImgUrl());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());

        Game game = gameRepository.save(entity);
        List<GameMinProjection> list = gameRepository.searchByList(dto.getBelongingListId());

        belongToRepository.createBelong(dto.getBelongingListId(), game.getId(), list.size());
        return new GameMinDTO(game);
    }
}
