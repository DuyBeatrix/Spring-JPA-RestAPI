package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.KhoaHocDTO;
import com.example.TestBackend.Entity.DangKyHoc;
import com.example.TestBackend.Entity.KhoaHoc;
import com.example.TestBackend.Entity.LoaiKhoaHoc;
import com.example.TestBackend.Repo.DangKyHocRepo;
import com.example.TestBackend.Repo.KhoaHocRepo;
import com.example.TestBackend.Repo.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocService
{
    @Autowired
    KhoaHocRepo khoaHocRepo;
    @Autowired
    LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    private DangKyHocRepo dangKyHocRepo;

    public void setKhoaHoc(KhoaHoc khoaHoc, KhoaHocDTO khoaHocDTO)
    {
        khoaHoc.setTenKhoaHoc(khoaHocDTO.getTenKhoaHoc());
        khoaHoc.setThoiGianHoc(khoaHocDTO.getThoiGianHoc());
        khoaHoc.setGioiThieu(khoaHocDTO.getGioiThieu());
        khoaHoc.setNoiDung(khoaHocDTO.getGioiThieu());
        khoaHoc.setHocPhi(khoaHocDTO.getHocPhi());
        khoaHoc.setSoLuongMon(khoaHocDTO.getSoLuongMon());
        khoaHoc.setHinhAnh(khoaHocDTO.getHinhAnh());
        LoaiKhoaHoc loaiKhoaHoc = loaiKhoaHocRepo.findById(khoaHocDTO.getLoaiKhoaHocID()).get();
        khoaHoc.setLoaiKhoaHoc(loaiKhoaHoc);
    }

    public void themKhoaHoc(KhoaHocDTO khoaHocDTO)
    {
        KhoaHoc khoaHoc = new KhoaHoc();
        khoaHoc.setSoHocVien(0);
        setKhoaHoc(khoaHoc, khoaHocDTO);
        khoaHocRepo.save(khoaHoc);
    }

    public void xoaKhoaHoc(int id)
    {
        Optional<KhoaHoc> khoaHocOpt = khoaHocRepo.findById(id);
        if(khoaHocOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID khoa hoc");
        }
        List<DangKyHoc> dangKyHocList = dangKyHocRepo.findAll();
        for (DangKyHoc dangKyHoc : dangKyHocList) {
            if(dangKyHoc.getKhoaHocID() == id) dangKyHocRepo.delete(dangKyHoc);
        }
        khoaHocRepo.deleteById(id);
    }

    public void suaKhoaHoc(int id, KhoaHocDTO khoaHocDTO)
    {
        Optional<KhoaHoc> khoaHocOpt = khoaHocRepo.findById(id);
        if(khoaHocOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID khoa hoc");
        }
        khoaHocOpt.get().setSoHocVien(dangKyHocRepo.countDangKyHocByKhoaHocIDAndNotTinhTrangHocID(id, 4));
        setKhoaHoc(khoaHocOpt.get(), khoaHocDTO);
        khoaHocRepo.save(khoaHocOpt.get());
    }

    public Page<KhoaHoc> hienThiKhoaHoc(Pageable pageable) {
        return khoaHocRepo.findAll(pageable);
    }

    public Page<KhoaHoc> timKiemKhoaHoc(String tenKhoaHoc, Pageable pageable) {
        Page<KhoaHoc> list = khoaHocRepo.findByTenKhoaHocContainingIgnoreCase(tenKhoaHoc,pageable);
        if(list.isEmpty()) throw new RuntimeException("Khong tim khoa hoc");
        return list;
    }
}
