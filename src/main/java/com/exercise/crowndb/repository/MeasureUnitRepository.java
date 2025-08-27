package com.exercise.crowndb.repository;

import com.exercise.crowndb.model.Measureunit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureUnitRepository extends JpaRepository<Measureunit,Long> {
}
