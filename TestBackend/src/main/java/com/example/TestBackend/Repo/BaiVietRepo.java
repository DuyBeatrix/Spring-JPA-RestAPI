package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaiVietRepo extends JpaRepository<BaiViet, Integer> {
    Page<BaiViet> findAll(Pageable pageable);
}
