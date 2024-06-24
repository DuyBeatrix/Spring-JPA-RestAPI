package com.example.TestBackend.Controller;

import com.example.TestBackend.DTO.BaiVietDTO;
import com.example.TestBackend.Service.BaiVietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaiVietController
{
    @Autowired
    private BaiVietService baiVietService;

    @PostMapping("/thembaiviet")
    public void themBaiViet(@RequestBody @Valid BaiVietDTO baiVietDTO)
    {
        baiVietService.themBaiViet(baiVietDTO);
    }
    @PutMapping("/suabaiviet")
    public void suBaiViet(@RequestParam int id, @RequestBody @Valid BaiVietDTO baiVietDTO)
    {
        baiVietService.suaBaiViet(id, baiVietDTO);
    }
    @DeleteMapping("/xoabaiviet")
    public void xoaBaiViet(@RequestParam int id)
    {
        baiVietService.xoaBaiViet(id);
    }
    @GetMapping("/hienthibaiviet")
    public ResponseEntity<?> hienThiBaiViet(Integer pageVar, Integer size)
    {
        if(pageVar == null) pageVar = 1;
        if(size == null) size = 1;
        Pageable page = PageRequest.of(pageVar, size);
        return ResponseEntity.ok(baiVietService.hienThiBaiViet(page));
    }
}
