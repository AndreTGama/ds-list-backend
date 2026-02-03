package com.andregama.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.andregama.dslist.entities.Belonging;
import com.andregama.dslist.entities.BelongingPK;

public interface BelongToRepository extends JpaRepository<Belonging, BelongingPK> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO  tb_belonging (list_id, game_id, position) VALUES (:listId, :gameId, :position)")
    void createBelong(Long listId, Long gameId, Integer position);
}