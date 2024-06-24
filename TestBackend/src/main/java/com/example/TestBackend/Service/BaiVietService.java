package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.BaiVietDTO;
import com.example.TestBackend.Entity.*;
import com.example.TestBackend.Repo.BaiVietRepo;
import com.example.TestBackend.Repo.ChuDeRepo;
import com.example.TestBackend.Repo.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BaiVietService
{
    @Autowired
    BaiVietRepo baiVietRepo;
    @Autowired
    ChuDeRepo chuDeRepo;
    @Autowired
    TaiKhoanRepo taiKhoanRepo;

    private void setBaiViet(BaiViet baiViet, BaiVietDTO baiVietDTO)
    {
        baiViet.setTenBaiViet(baiVietDTO.getTenBaiViet());
        baiViet.setTenTacGia(baiVietDTO.getTenTacGia());
        baiViet.setNoiDung(baiVietDTO.getNoiDung());
        baiViet.setNoiDUngNgan(baiVietDTO.getNoiDUngNgan());
        baiViet.setHinhAnh(baiVietDTO.getHinhAnh());
        ChuDe chuDe = chuDeRepo.findById(baiVietDTO.getChuDeID()).get();
        baiViet.setChuDe(chuDe);
        TaiKhoan taiKhoan = taiKhoanRepo.findById(baiVietDTO.getTaiKhoanID()).get();
        baiViet.setTaiKhoan(taiKhoan);
    }

    public void themBaiViet(BaiVietDTO baiVietDTO)
    {
        BaiViet baiViet = new BaiViet();
        baiViet.setThoiGianTao(LocalDate.now());
        setBaiViet(baiViet, baiVietDTO);
        baiVietRepo.save(baiViet);
    }

    public void suaBaiViet(int id, BaiVietDTO baiVietDTO)
    {
        Optional<BaiViet> baiVietOpt = baiVietRepo.findById(id);
        if(baiVietOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID bai viet");
        BaiViet baiViet = baiVietRepo.findById(id).get();
        setBaiViet(baiViet, baiVietDTO);
        baiVietRepo.save(baiViet);
    }

    public void xoaBaiViet(int id)
    {
        Optional<BaiViet> baiVietOpt = baiVietRepo.findById(id);
        if(baiVietOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID bai viet");
        baiVietRepo.deleteById(id);
    }

    public Page<BaiViet> hienThiBaiViet(Pageable pageable)
    {
        return baiVietRepo.findAll(pageable);
    }
}
