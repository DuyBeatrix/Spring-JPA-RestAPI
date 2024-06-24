package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.QuyenHanDTO;
import com.example.TestBackend.Service.QuyenHanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuyenHanController
{
    @Autowired
    private QuyenHanService quyenHanService;

    @PostMapping("/themquyenhan")
    public void themQuyenHan(@RequestBody QuyenHanDTO quyenHanDTO)
    {
        quyenHanService.themQuyenHan(quyenHanDTO);
    }
    @PutMapping("/suaquyenhan")
    public void suaQuyenHan(@RequestParam int quyenHanID, @RequestBody QuyenHanDTO quyenHanDTO)
    {
        quyenHanService.suaQuyenHan(quyenHanID, quyenHanDTO);
    }
    @DeleteMapping("/xoaquyenhan")
    public void xoaQuyenHan(@RequestParam int quyenHanID)
    {
        quyenHanService.xoaQuyenHan(quyenHanID);
    }
    @GetMapping("/hienthiquyenhan")
    public ResponseEntity<?> hienthiquyenhan(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(quyenHanService.hienThiQuyenHan(page));
    }
}
