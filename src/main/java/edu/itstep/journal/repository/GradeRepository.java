package edu.itstep.journal.repository;

import edu.itstep.journal.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
