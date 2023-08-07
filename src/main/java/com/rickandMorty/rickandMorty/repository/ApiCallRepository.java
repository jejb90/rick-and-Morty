package com.rickandMorty.rickandMorty.repository;

import com.rickandMorty.rickandMorty.entities.ApiCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCallRepository extends JpaRepository<ApiCall, Long> {
}
