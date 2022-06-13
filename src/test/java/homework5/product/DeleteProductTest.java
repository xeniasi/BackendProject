package homework5.product;

import com.github.javafaker.Faker;
import homework5.api.ProductService;
import homework5.dto.Product;
import homework5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @SneakyThrows
    @BeforeEach
    void createProduct() {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteProductPositiveTest() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteProductNegativeTest() {
        Response<ResponseBody> response = productService.deleteProduct(999999).execute();
        assertThat(response.code(), CoreMatchers.is(500));
    }
}
