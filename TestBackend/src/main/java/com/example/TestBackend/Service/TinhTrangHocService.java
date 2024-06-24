package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.TinhTrangHocDTO;
import com.example.TestBackend.Entity.*;
import com.example.TestBackend.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TinhTrangHocService
{
    @Autowired
    TinhTrangHocRepo tinhTrangHocRepo;
    @Autowired
    private DangKyHocRepo dangKyHocRepo;

    public void themTinhTrangHoc(TinhTrangHocDTO tinhTrangHocDTO)
    {
        TinhTrangHoc tinhTrangHoc = new TinhTrangHoc();
        tinhTrangHoc.setTenTinhTrang(tinhTrangHocDTO.getTenTinhTrang());
        tinhTrangHocRepo.save(tinhTrangHoc);
    }

    public void suaTinhTrangHoc(int id, TinhTrangHocDTO tinhTrangHocDTO)
    {
        Optional<TinhTrangHoc> tinhTrangHocOpt = tinhTrangHocRepo.findById(id);
        if(tinhTrangHocOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID tinhtrang hoc");
        tinhTrangHocOpt.get().setTenTinhTrang(tinhTrangHocDTO.getTenTinhTrang());
        tinhTrangHocRepo.save(tinhTrangHocOpt.get());
    }

    public void xoaTinhTrangHoc(int id)
    {
        Optional<TinhTrangHoc> tinhTrangHocOpt = tinhTrangHocRepo.findById(id);
        if(tinhTrangHocOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID tinhtrang hoc");
        List<DangKyHoc> dangKyHocList = dangKyHocRepo.findAll();
        for(DangKyHoc dangKyHoc : dangKyHocList)
        {
            if(dangKyHoc.getTinhTrangHocID() == id) dangKyHocRepo.delete(dangKyHoc);
        }
        tinhTrangHocRepo.deleteById(id);
    }

    public List<TinhTrangHoc> hienThiTinhTrangHoc()
    {
        return tinhTrangHocRepo.findAll();
    }
}
