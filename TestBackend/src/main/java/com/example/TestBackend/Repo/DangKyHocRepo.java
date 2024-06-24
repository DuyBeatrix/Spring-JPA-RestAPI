package com.example.TestBackend.Repo;

import com.example.TestBackend.Entity.DangKyHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyHocRepo extends JpaRepository<DangKyHoc, Integer> {
    @Query("SELECT COUNT(d) FROM DangKyHoc d WHERE d.khoaHocID = :khoaHocID AND d.tinhTrangHocID <> :tinhTrangHocID")
    int countDangKyHocByKhoaHocIDAndNotTinhTrangHocID(@Param("khoaHocID") int khoaHocID, @Param("tinhTrangHocID") int tinhTrangHocID);
    DangKyHoc findByKhoaHocIDAndHocVienID(int khoaHocID, int hocVienID);
    Page<DangKyHoc> findAll(Pageable pageable);
}
