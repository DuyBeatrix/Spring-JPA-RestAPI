package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.HocVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocVienRepo extends JpaRepository<HocVien, Integer>
{
    Page<HocVien> findAll(Pageable pageable);
    Page<HocVien> findByHoTenContainingIgnoreCase(String hoTen, Pageable pageable);
    HocVien findByEmail(String email);
}
