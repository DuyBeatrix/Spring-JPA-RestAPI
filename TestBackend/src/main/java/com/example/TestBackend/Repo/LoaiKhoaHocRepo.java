package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.LoaiKhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiKhoaHocRepo extends JpaRepository<LoaiKhoaHoc, Integer> {
}
