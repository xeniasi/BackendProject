package homework5.category;

import homework5.api.CategoryService;
import homework5.dto.GetCategoryResponse;
import homework5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {

    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(999999).execute();

        assertThat(response.code(), CoreMatchers.is(404));
    }

}
