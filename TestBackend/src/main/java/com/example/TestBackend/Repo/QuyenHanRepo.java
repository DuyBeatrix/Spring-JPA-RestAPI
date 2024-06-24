package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.QuyenHan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenHanRepo extends JpaRepository<QuyenHan, Integer>
{
    Page<QuyenHan> findAll(Pageable pageable);
}
