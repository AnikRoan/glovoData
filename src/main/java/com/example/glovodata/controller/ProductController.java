package com.example.glovodata.controller;

import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.ProductServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductServise productServise;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping()
    public ApiResponse<List<ProductDto>> getAll() {
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();
        List<ProductDto> productDtos = productServise.getProducts();
        if (!CollectionUtils.isEmpty(productDtos)) {
            response.setSuccess(true);
            response.setData(productDtos);
        } else {
            response.setSuccess(false);
            response.setMessage("products not found");
        }
        return response;
    }


    @GetMapping("/{id}")
    public ApiResponse<ProductDto> get(@PathVariable Integer id) {
        ApiResponse<ProductDto> response = new ApiResponse<>();
        ProductDto productDto = productServise.getProductById(id);
        if (productDto != null) {
            response.setSuccess(true);
            response.setData(productDto);
        } else {
            response.setSuccess(false);
            response.setMessage("product not found");
        }
        return response;
    }

    @PostMapping("/update/{id}")
    public ApiResponse<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductDto dto) {
        this.productServise.update(id, dto);
        ApiResponse<ProductDto> updateDto = new ApiResponse<>();
        updateDto.setSuccess(true);
        updateDto.setMessage("product updated successfully");
        updateDto.setData(dto);

        return updateDto;
    }


    @PostMapping("/create")
    public ApiResponse<ProductDto> createProduct(@RequestBody ProductDto dto) {
        productServise.save(dto);
        ApiResponse<ProductDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(dto);
        response.setMessage("create product");

        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Integer id) {
        ProductDto productDto = this.productServise.getProductById(id);
        ApiResponse<String> response = new ApiResponse<>();
        if (productDto != null) {
            this.productServise.delete(id);
            response.setSuccess(true);
            response.setMessage("product deleted");
        } else {
            response.setSuccess(false);
            response.setMessage("product notFound");
        }
        return response;
    }

}
