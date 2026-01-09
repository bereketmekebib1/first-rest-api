package pl.edu.vistula.first_rest_api.product.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.vistula.first_rest_api.product.api.request.ProductRequest;
import pl.edu.vistula.first_rest_api.product.api.response.ProductResponse;
import pl.edu.vistula.first_rest_api.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // ✅ Constructor injection (NO Lombok, exactly like the PDF)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest productRequest) {

        ProductResponse response = productService.create(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable Long id) {

        ProductResponse response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest) {

        ProductResponse response = productService.update(id, productRequest);
        return ResponseEntity.ok(response);
    }

    // DELETE ✅ (THIS IS WHY YOU DID NOT SEE DELETE BEFORE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
