package com.example.TestBackend.Controller;

import com.example.TestBackend.Service.DangKyHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DangKyHocController
{
    @Autowired
    private DangKyHocService dangKyHocService;

    @PutMapping("/capnhatdangkyhoc")
    public void capNhatDangKyHoc(@RequestParam int hocVienID, @RequestParam int khoaHocID)
    {
        dangKyHocService.capNhatDangKyHoc(hocVienID, khoaHocID);
    }
    @DeleteMapping("/xoadangkyhoc")
    public void xoaDangKyHoc(@RequestParam int hocVienID)
    {
        dangKyHocService.xoaDangKyHoc(hocVienID);
    }
    @GetMapping("/hienthidangkyhoc")
    public ResponseEntity<?> hienThiDangKyHoc(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(dangKyHocService.hienThiDangKyHoc(page));
    }
}
