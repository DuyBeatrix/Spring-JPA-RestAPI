package com.example.TestBackend.Service;

import com.example.TestBackend.Entity.DangKyHoc;
import com.example.TestBackend.Entity.KhoaHoc;
import com.example.TestBackend.Repo.DangKyHocRepo;
import com.example.TestBackend.Repo.KhoaHocRepo;
import com.example.TestBackend.Repo.TinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DangKyHocService
{
    @Autowired
    DangKyHocRepo dangKyHocRepo;
    @Autowired
    KhoaHocRepo khoaHocRepo;
    @Autowired
    TinhTrangHocRepo tinhTrangHocRepo;

    public void capNhatDangKyHoc(int hocVienID, int khoaHocID)
    {
        DangKyHoc dangKyHoc = dangKyHocRepo.findByKhoaHocIDAndHocVienID(khoaHocID, hocVienID);
        if (dangKyHoc == null) throw new RuntimeException("Khong tim thay don dang ky");

        if(dangKyHoc.getNgayKetThuc().isBefore(LocalDate.now())) {
            dangKyHoc.setTinhTrangHoc(tinhTrangHocRepo.findById(3).get());
        }
        else if(dangKyHoc.getNgayKetThuc().isAfter(LocalDate.now())) {
            dangKyHoc.setTinhTrangHoc(tinhTrangHocRepo.findById(2).get());
        }
        else
        {
            dangKyHoc.setTinhTrangHoc(tinhTrangHocRepo.findById(4).get());

            KhoaHoc khoaHoc = khoaHocRepo.findById(khoaHocID).get();
            khoaHoc.setSoHocVien(dangKyHocRepo.countDangKyHocByKhoaHocIDAndNotTinhTrangHocID(khoaHocID, 4));
            khoaHocRepo.save(khoaHoc);
        }
        dangKyHocRepo.save(dangKyHoc);
    }

    public void xoaDangKyHoc(int id)
    {
        Optional<DangKyHoc> dangKyHocOpt = dangKyHocRepo.findById(id);
        if(dangKyHocOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID don dang ky");
        KhoaHoc khoaHoc = khoaHocRepo.findById(dangKyHocOpt.get().getKhoaHocID()).get();
        khoaHoc.setSoHocVien(dangKyHocRepo.countDangKyHocByKhoaHocIDAndNotTinhTrangHocID(dangKyHocOpt.get().getKhoaHocID(), 4));
        khoaHocRepo.save(khoaHoc);
    }

    public Page<DangKyHoc> hienThiDangKyHoc(Pageable pageable)
    {
        return dangKyHocRepo.findAll(pageable);
    }
}
