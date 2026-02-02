package com.andregama.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregama.dslist.dto.GameListDTO;
import com.andregama.dslist.dto.GameList.Request.PutGameListRequestDTO;
import com.andregama.dslist.dto.GameList.Request.StoreGameListRequestDTO;
import com.andregama.dslist.entities.GameList;
import com.andregama.dslist.projections.GameMinProjection;
import com.andregama.dslist.repositories.GameListRepository;
import com.andregama.dslist.repositories.GameRepository;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);
        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

    @Transactional
    public GameListDTO save(StoreGameListRequestDTO dto) {
        GameList existList = gameListRepository.findByName(dto.getName());
        if (existList != null) {
            throw new IllegalArgumentException("Este nome de lista já existe.");
        }

        GameList entity = new GameList();
        entity.setName(dto.getName());

        GameList savedGameList = gameListRepository.save(entity);

        return new GameListDTO(savedGameList);
    }

    @Transactional
    public GameListDTO update(Long id, PutGameListRequestDTO dto) {
        GameList existList = gameListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista não encontrada."));
        GameList listWithSameName = gameListRepository.findByName(dto.getName());
        if (listWithSameName != null && !listWithSameName.getId().equals(id)) {
            throw new IllegalArgumentException("Este nome de lista já existe.");
        }

        existList.setName(dto.getName());
        GameList updatedGameList = gameListRepository.save(existList);

        return new GameListDTO(updatedGameList);
    }

    @Transactional
    public void delete(Long id) {
        GameList existList = gameListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Lista não encontrada."));
        gameListRepository.delete(existList);
    }
}
