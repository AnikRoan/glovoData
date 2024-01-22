package com.example.glovodata.controller;

import com.example.glovodata.dto.OrderDto;
import com.example.glovodata.dto.ProductDto;
import com.example.glovodata.respons.ApiResponse;
import com.example.glovodata.servise.ProductServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
@Slf4j
public class ProductController {
    private final ProductServise productServise;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("An error occurred: "+e.getMessage(), e);
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
    public ResponseEntity<ProductDto> get(@PathVariable("id") Integer id) {
        ProductDto productDto = productServise.getProductById(id);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        }
        log.info("Product with id {} not found", id);

        return (ResponseEntity<ProductDto>) ResponseEntity.notFound();
    }

    @PostMapping("/update/{id}")
    public ApiResponse<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductDto dto) {
        this.productServise.update(id, dto);
        ApiResponse<ProductDto> updateDto = new ApiResponse<>();
        updateDto.setSuccess(true);
        updateDto.setMessage("product updated successfully");
        updateDto.setData(dto);

        log.info("Product updated successfully: {}",id);

        return updateDto;
    }


    @PostMapping("/create")
    public void createProduct(@RequestBody ProductDto dto) {
        productServise.save(dto);
        log.info("Product created successfully: {}",dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        ProductDto productDto = this.productServise.getProductById(id);

        if (productDto != null) {
            this.productServise.delete(id);

            log.info("Product with id {} deleted successfully", id);

           return ResponseEntity.ok("product delete");
        } else {

            log.info("Product with id {} not found ", id);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        }

    }

}
