package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.LoaiBaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiBaiVietRepo extends JpaRepository<LoaiBaiViet, Integer>
{
    Page<LoaiBaiViet> findAll(Pageable pageable);
}
