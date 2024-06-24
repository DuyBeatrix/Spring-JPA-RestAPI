package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.TinhTrangHocDTO;
import com.example.TestBackend.Entity.TinhTrangHoc;
import com.example.TestBackend.Service.TinhTrangHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TinhTrangHocController
{
    @Autowired
    private TinhTrangHocService tinhTrangHocService;

    @PostMapping("/themTinhTrangHoc")
    public void themTinhTrangHoc(@RequestBody TinhTrangHocDTO tinhTrangHocDTO)
    {
        tinhTrangHocService.themTinhTrangHoc(tinhTrangHocDTO);
    }
    @DeleteMapping("/xoaTinhTrangHoc")
    public void xoaTinhTrangHoc(@RequestParam int id)
    {
        tinhTrangHocService.xoaTinhTrangHoc(id);
    }
    @PutMapping("/suaTinhTrangHoc")
    public void suaTinhTrangHoc(@RequestParam int id, @RequestBody TinhTrangHocDTO tinhTrangHocDTO)
    {
        tinhTrangHocService.suaTinhTrangHoc(id, tinhTrangHocDTO);
    }
    @GetMapping("/hienThiTinhTrangHoc")
    public List<TinhTrangHoc> hienThiTinhTrangHoc()
    {
        return tinhTrangHocService.hienThiTinhTrangHoc();
    }
}
