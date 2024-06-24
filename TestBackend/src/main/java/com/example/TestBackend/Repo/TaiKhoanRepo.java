package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, Integer>
{
    TaiKhoan findByTaiKhoan(String taiKhoan);
    Page<TaiKhoan> findAll(Pageable pageable);
}
