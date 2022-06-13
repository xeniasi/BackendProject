package homework5.api;

import homework5.dto.GetCategoryResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CategoryService {

    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);

}
