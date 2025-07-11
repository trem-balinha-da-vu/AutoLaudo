package com.sistema.autolaudo.repository.selodigital;

import com.sistema.autolaudo.model.selodigital.SeloDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeloDigitalRepository extends JpaRepository<SeloDigital, Long> { }
