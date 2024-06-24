package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.KhoaHocDTO;
import com.example.TestBackend.DTO.LoaiKhoaHocDTO;
import com.example.TestBackend.Entity.KhoaHoc;
import com.example.TestBackend.Entity.LoaiKhoaHoc;
import com.example.TestBackend.Repo.KhoaHocRepo;
import com.example.TestBackend.Repo.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LoaiKhoaHocService
{
    @Autowired
    LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    KhoaHocRepo khoaHocRepo;

    public void themLoaiKhoaHoc(LoaiKhoaHocDTO loaiKhoaHocDTO)
    {
        LoaiKhoaHoc loaiKhoaHoc = new LoaiKhoaHoc();
        loaiKhoaHoc.setTenLoai(loaiKhoaHocDTO.getTenLoai());

        // TH: Add Cate + course
        if(loaiKhoaHocDTO.getKhoaHocSet() != null)
        {
            Set<KhoaHoc> khoaHocSet = new HashSet<>();
            for(KhoaHocDTO khoaHocDTO : loaiKhoaHocDTO.getKhoaHocSet())
            {
                KhoaHoc khoaHoc = new KhoaHoc();

                khoaHoc.setTenKhoaHoc(khoaHocDTO.getTenKhoaHoc());
                khoaHoc.setThoiGianHoc(khoaHocDTO.getThoiGianHoc());
                khoaHoc.setGioiThieu(khoaHocDTO.getGioiThieu());
                khoaHoc.setNoiDung(khoaHocDTO.getGioiThieu());
                khoaHoc.setHocPhi(khoaHocDTO.getHocPhi());
                khoaHoc.setSoLuongMon(khoaHocDTO.getSoLuongMon());
                khoaHoc.setHinhAnh(khoaHocDTO.getHinhAnh());
                khoaHoc.setLoaiKhoaHoc(loaiKhoaHoc);

                khoaHocSet.add(khoaHoc);
                khoaHocRepo.save(khoaHoc);
            }
            loaiKhoaHoc.setKhoaHocSet(khoaHocSet);
        }
        loaiKhoaHocRepo.save(loaiKhoaHoc);
    }

    public void suaLoaiKhoaHoc(int id, LoaiKhoaHocDTO loaiKhoaHocDTO)
    {
        Optional<LoaiKhoaHoc> loaiKhoaHocOpt = loaiKhoaHocRepo.findById(id);
        if(loaiKhoaHocOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID loai khoa hoc");
        }
        loaiKhoaHocOpt.get().setTenLoai(loaiKhoaHocDTO.getTenLoai());
        loaiKhoaHocRepo.save(loaiKhoaHocOpt.get());
    }

    public void xoaLoaiKhoaHoc(int id)
    {
        Optional<LoaiKhoaHoc> loaiKhoaHocOpt = loaiKhoaHocRepo.findById(id);
        if(loaiKhoaHocOpt.isEmpty()) {
            throw new RuntimeException("Khong tim thay ID loai khoa hoc");
        }
        List<KhoaHoc> khoaHocList = khoaHocRepo.findAll();
        for (KhoaHoc khoaHoc : khoaHocList) {
            if(khoaHoc.getLoaiKhoaHocID() == id) khoaHocRepo.delete(khoaHoc);
        }
        loaiKhoaHocRepo.deleteById(id);
    }

    public List<LoaiKhoaHoc> hienThiLoaiKhoaHoc()
    {
        return loaiKhoaHocRepo.findAll();
    }
}

