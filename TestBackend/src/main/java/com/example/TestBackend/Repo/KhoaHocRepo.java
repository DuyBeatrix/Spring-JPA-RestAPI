package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepo extends JpaRepository<KhoaHoc, Integer>
{
    Page<KhoaHoc> findAll(Pageable pageable);
    Page<KhoaHoc> findByTenKhoaHocContainingIgnoreCase(String tenKhoaHoc, Pageable pageable);
}
