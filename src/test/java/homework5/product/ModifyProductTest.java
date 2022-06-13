package homework5.product;

import com.github.javafaker.Faker;
import homework5.api.ProductService;
import homework5.dto.Product;
import homework5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class ModifyProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000))
                .withId(1);
    }

    @SneakyThrows
    @Test
    void modifyProductPositiveTest() {
        Response<Product> response = productService.modifyProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
