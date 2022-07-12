package com.alkemy.disney.disney.repositories;

import com.alkemy.disney.disney.entities.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {



}
