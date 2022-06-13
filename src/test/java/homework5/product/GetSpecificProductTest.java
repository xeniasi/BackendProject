package homework5.product;

import homework5.api.ProductService;
import homework5.dto.Product;
import homework5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetSpecificProductTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void getProductByIdPositiveTest() {
        Response<Product> response = productService.getProductById(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getProductByIdNegativeTest() {
        Response<Product> response = productService.getProductById(999999).execute();

        assertThat(response.code(), CoreMatchers.is(404));
    }

}
