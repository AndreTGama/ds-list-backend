package com.andregama.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregama.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    
}
