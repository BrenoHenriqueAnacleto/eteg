package com.breno.backend.repositories;

import com.breno.backend.domain.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorRepository  extends JpaRepository<Cor, Long> {
}
