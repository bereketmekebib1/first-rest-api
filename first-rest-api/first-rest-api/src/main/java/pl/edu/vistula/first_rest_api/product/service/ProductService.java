package pl.edu.vistula.first_rest_api.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.first_rest_api.product.api.request.ProductRequest;
import pl.edu.vistula.first_rest_api.product.api.response.ProductResponse;
import pl.edu.vistula.first_rest_api.product.domain.Product;
import pl.edu.vistula.first_rest_api.product.repository.ProductRepository;
import pl.edu.vistula.first_rest_api.product.support.ProductMapper;
import pl.edu.vistula.first_rest_api.product.support.exception.ProductNotFoundException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // CREATE
    public ProductResponse create(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductResponse(savedProduct);
    }

    // FIND BY ID
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toProductResponse(product);
    }

    // FIND ALL
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    // UPDATE
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productRequest.getName());

        Product updatedProduct = productRepository.save(product);
        return productMapper.toProductResponse(updatedProduct);
    }

    // DELETE  ✅ THIS IS STEP 3 YOU WERE MISSING
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }
}
