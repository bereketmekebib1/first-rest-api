package pl.edu.vistula.first_rest_api.product.support.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }
}
