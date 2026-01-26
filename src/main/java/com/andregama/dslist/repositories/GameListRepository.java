package com.andregama.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregama.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    
}
