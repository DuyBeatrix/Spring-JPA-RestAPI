package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.LoaiKhoaHocDTO;
import com.example.TestBackend.Entity.LoaiKhoaHoc;
import com.example.TestBackend.Service.LoaiKhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LoaiKhoaHocController
{
    @Autowired
    private LoaiKhoaHocService loaiKhoaHocService;

    @PostMapping("/themLoaiKhoaHoc")
    public void themLoaiKhoaHoc(@RequestBody LoaiKhoaHocDTO loaiKhoaHocDTO)
    {
        loaiKhoaHocService.themLoaiKhoaHoc(loaiKhoaHocDTO);
    }
    @PutMapping("/suaLoaiKhoaHoc")
    public void suaLoaiKhoaHoc(@RequestParam int id, @RequestBody LoaiKhoaHocDTO loaiKhoaHocDTO)
    {
        loaiKhoaHocService.suaLoaiKhoaHoc(id, loaiKhoaHocDTO);
    }
    @DeleteMapping("/xoaLoaiKhoaHoc")
    public void xoaLoaiKhoaHoc(@RequestParam int id)
    {
        loaiKhoaHocService.xoaLoaiKhoaHoc(id);
    }
    @GetMapping("/hienThiLoaiKhoaHoc")
    public List<LoaiKhoaHoc> hienThiLoaiKhoaHoc()
    {
        return loaiKhoaHocService.hienThiLoaiKhoaHoc();
    }
}
