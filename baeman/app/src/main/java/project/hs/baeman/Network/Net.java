package project.hs.baeman.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Net {
    private static Net ourInstance = new Net();
    public static Net getInstance() {
        return ourInstance;
    }
    private Net() {
    }
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://bucoco.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService;

    public ApiService getApiService(){
        if(apiService == null){
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
