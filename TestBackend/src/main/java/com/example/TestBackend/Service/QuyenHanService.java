package com.example.TestBackend.Service;

import com.example.TestBackend.DTO.QuyenHanDTO;
import com.example.TestBackend.Entity.BaiViet;
import com.example.TestBackend.Entity.QuyenHan;
import com.example.TestBackend.Entity.TaiKhoan;
import com.example.TestBackend.Repo.BaiVietRepo;
import com.example.TestBackend.Repo.QuyenHanRepo;
import com.example.TestBackend.Repo.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuyenHanService
{
    @Autowired
    QuyenHanRepo quyenHanRepo;
    @Autowired
    TaiKhoanRepo taiKhoanRepo;
    @Autowired
    BaiVietRepo baiVietRepo;

    public void themQuyenHan(QuyenHanDTO quyenHanDTO)
    {
        QuyenHan quyenHan = new QuyenHan();
        quyenHan.setTenQuyenHan(quyenHanDTO.getTenQuyenHan());
        quyenHanRepo.save(quyenHan);
    }

    public void suaQuyenHan(int id, QuyenHanDTO quyenHanDTO)
    {
        Optional<QuyenHan> quyenHanOpt = quyenHanRepo.findById(id);
        if(quyenHanOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID quyen han");
        quyenHanOpt.get().setTenQuyenHan(quyenHanDTO.getTenQuyenHan());
        quyenHanRepo.save(quyenHanOpt.get());
    }

    public void xoaQuyenHan(int id)
    {
        Optional<QuyenHan> quyenHanOpt = quyenHanRepo.findById(id);
        if(quyenHanOpt.isEmpty()) throw new RuntimeException("Khong tim thay ID quyen han");
        List<TaiKhoan> list = taiKhoanRepo.findAll();
        for (TaiKhoan tk : list) {
            if(tk.getQuyenHanID() == id)
            {
                List<BaiViet> baiVietList = baiVietRepo.findAll();
                for(BaiViet bv : baiVietList) {
                    if(bv.getTaiKhoanID() == tk.getTaiKhoanID()) baiVietRepo.delete(bv);
                }
                taiKhoanRepo.delete(tk);
            }
        }
        quyenHanRepo.deleteById(id);
    }

    public Page<QuyenHan> hienThiQuyenHan(Pageable pageable)
    {
        return quyenHanRepo.findAll(pageable);
    }
}
