package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.ChuDe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuDeRepo extends JpaRepository<ChuDe, Integer>
{
    Page<ChuDe> findAll(Pageable pageable);
}
