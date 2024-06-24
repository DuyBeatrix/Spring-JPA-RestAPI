package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.DangKyHocDTO;
import com.example.TestBackend.DTO.HocVienDTO;
import com.example.TestBackend.Entity.*;
import com.example.TestBackend.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class HocVienService
{
    @Autowired
    HocVienRepo hocVienRepo;
    @Autowired
    KhoaHocRepo khoaHocRepo;
    @Autowired
    TinhTrangHocRepo tinhTrangHocRepo;
    @Autowired
    TaiKhoanRepo taiKhoanRepo;
    @Autowired
    DangKyHocRepo dangKyHocRepo;

    public void setHocVien(HocVien hocVien, HocVienDTO hocVienDTO)
    {
        hocVien.setHinhAnh(hocVienDTO.getHinhAnh());
        hocVien.setHoTen(formatHoTen(hocVienDTO.getHoTen()));
        hocVien.setNgaySinh(hocVienDTO.getNgaySinh());
        hocVien.setSDT(hocVienDTO.getPhone());
        hocVien.setEmail(hocVienDTO.getEmail());
        hocVien.setTinhThanh(hocVienDTO.getTinhThanh());
        hocVien.setQuanHuyen(hocVienDTO.getQuanHuyen());
        hocVien.setPhuongXa(hocVienDTO.getPhuongXa());
        hocVien.setSonha(hocVienDTO.getSonha());
    }

    // when std register -> add std + register
    public void themHocVien(HocVienDTO hocVienDTO)
    {
        HocVien hocVien = new HocVien();

        List<HocVien> list = hocVienRepo.findAll();
        for(HocVien h : list) {
            if(Objects.equals(hocVienDTO.getPhone(), h.getSDT()) || Objects.equals(hocVienDTO.getEmail(), h.getEmail()))
                throw new RuntimeException("SDT hoac Email da ton tai");
        }
        setHocVien(hocVien, hocVienDTO);
        hocVienRepo.save(hocVien);

        if(hocVienDTO.getDangKyHocSet() != null)
        {
            Set<DangKyHoc> dangKyHocSet = new HashSet<DangKyHoc>();
            for(DangKyHocDTO dangKyHocDTO : hocVienDTO.getDangKyHocSet())
            {
                DangKyHoc dangKyHoc = new DangKyHoc();

                Optional<KhoaHoc> khoaHocOpt = khoaHocRepo.findById(dangKyHocDTO.getKhoaHocID());
                if(khoaHocOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID khoa hoc");
                dangKyHoc.setKhoaHoc(khoaHocOpt.get());
                Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepo.findById(dangKyHocDTO.getTaiKhoanID());
                if(taiKhoanOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID tai khoan");
                dangKyHoc.setTaiKhoan(taiKhoanOpt.get());
                dangKyHoc.setHocVien(hocVien);
                TinhTrangHoc tinhTrangHoc = tinhTrangHocRepo.findById(1).get();
                dangKyHoc.setTinhTrangHoc(tinhTrangHoc);

                dangKyHoc.setNgayBatDau(dangKyHocDTO.getNgayBatDau());
                dangKyHoc.setNgayDangKy(LocalDate.now());
                dangKyHoc.setNgayKetThuc(dangKyHoc.getNgayBatDau().plusDays(khoaHocOpt.get().getThoiGianHoc()));
                dangKyHocSet.add(dangKyHoc);
                dangKyHocRepo.save(dangKyHoc);

                // Update course's quantity when add std
                khoaHocOpt.get().setSoHocVien(dangKyHocRepo.countDangKyHocByKhoaHocIDAndNotTinhTrangHocID(dangKyHocDTO.getKhoaHocID(), 4));
                khoaHocRepo.save(khoaHocOpt.get());
            }
            hocVien.setDangKyHocSet(dangKyHocSet);
        }
    }

    public void suaHocVien(int id, HocVienDTO hocVienDTO)
    {
        Optional<HocVien> hocVienOpt = hocVienRepo.findById(id);
        if(hocVienOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID hoc vien");

        List<HocVien> list = hocVienRepo.findAll();
        for(HocVien h : list) {
            if((Objects.equals(hocVienDTO.getPhone(), h.getSDT()) || Objects.equals(hocVienDTO.getEmail(), h.getEmail())) && h.getHocVienID()!=id)
                throw new RuntimeException("SDT hoac Email da ton tai");
        }

        setHocVien(hocVienOpt.get(), hocVienDTO);
        hocVienRepo.save(hocVienOpt.get());
    }

    public void xoaHocVien(int id)
    {
        Optional<HocVien> hocVienOpt = hocVienRepo.findById(id);
        if(hocVienOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID hoc vien");
        List<DangKyHoc> dangKyHocList = dangKyHocRepo.findAll();
        for(DangKyHoc dangKyHoc : dangKyHocList)
        {
            if(dangKyHoc.getHocVienID() == id) dangKyHocRepo.delete(dangKyHoc);
        }
        hocVienRepo.deleteById(id);
    }

    public Page<HocVien> hienThiHocVien(Pageable pageable) {
        return hocVienRepo.findAll(pageable);
    }

    public Page<HocVien> timKiemHocVienTheoTen(String hoTen, Pageable pageable) {
        Page<HocVien> list = hocVienRepo.findByHoTenContainingIgnoreCase(hoTen, pageable);
        if(list.isEmpty()) throw new RuntimeException("Khong tim ten hoc vien");
        return list;
    }

    public HocVien timKiemHocVienTheoEmail(String email) {
        HocVien hv = hocVienRepo.findByEmail(email);
        if(hv == null) throw new RuntimeException("Khong tim email hoc vien");
        return hv;
    }

    private String formatHoTen(String hoTen)
    {
        String normalizedHoTen = hoTen.trim().replaceAll("\\s+", " ");

        String[] words = normalizedHoTen.split(" ");

        StringBuilder formattedHoTen = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedHoTen.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return formattedHoTen.toString().trim();
    }
}
