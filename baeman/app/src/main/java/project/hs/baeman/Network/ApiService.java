package project.hs.baeman.Network;

import project.hs.baeman.Request.ReqLogin;
import project.hs.baeman.Request.ReqSignup;
import project.hs.baeman.Request.ReqValidate;
import project.hs.baeman.Request.ReqValidate2;
import project.hs.baeman.Response.DefaultRes;
import project.hs.baeman.Response.ResDetail;
import project.hs.baeman.Response.ResLogin;
import project.hs.baeman.Response.ResRestList;
import project.hs.baeman.Response.ResValidate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    static final String BASEURL  = "http://bucoco.kr/";

    @POST("login")
    Call<ResLogin> login(@Body ReqLogin reqLogin);

    @POST("users/auth")
    Call<ResValidate> validate1(@Body ReqValidate reqValidate);

    @POST("users/phone")
    Call<DefaultRes> validate2(@Body ReqValidate2 reqValidate2);

    @POST("users")
    Call<DefaultRes> signup(@Body ReqSignup reqSignup);

    @GET("restaurants")
    Call<ResRestList> showRestList(@Query("latitude") double latitude,
                                   @Query("longitude") double longitude,
                                   @Query("category") String category);

    @GET("restaurants/{restaurantNumber}")
    Call<ResDetail> showRestDetail(@Path("restaurantNumber") int restaurantNumber);



    //POST 메소드를 통한 http rest api통신
    //Call<받아올 데이터의 형태> 메소드명 (@Header("정의하고 싶은 속성 이름") 자료형 변수명,
    //
    //@Query("변수 이름") 자료형 변수명);
    //
    //
    //
    //출처: http://www.feelteller.com/6 [김정헌의 안드로이드 코딩 여행기]
    /*
    @POST("signup_test")
    Call<User> signupAttachment(@Query("user_id") String user_id,
                                        @Query("user_pw") String user_pw,
                                        @Query("user_name") String user_name,
                                        @Query("user_age") int user_age,
                                        @Query("user_saying") String user_saying,
                                        @Query("user_major") String user_major,
                                        @Query("user_sex") String user_sex,
                                        @Query("user_grade") int user_grade
                                        );
    */
    /*
    @POST("signup_test")
    Call<Res_join> join(@Body User user); //보낼때는 Req로 보내고 받을 때는 Res로 받음.

    @POST("login")
    Call<Res_img> login(@Body Req_login req_login);

    @POST("numberSetting")
    Call<Res_number> numberSetting(@Body Req_number req_number);

    @POST("showProfile")
    Call<User> showProfile(@Body Req_number req_number);

    @POST("allProfile")
    Call<List<User>> allProfile();

    @POST("likeYou")
    Call<Res_img> likeYou(@Body Req_likeyou req_likeyou);

    @POST("likeYou2")
    Call<Res_img> likeYou2(@Body Req_likeyou req_likeyou);

    @POST("likeYouList")
    Call<Res_string> likeYouList(@Body Req_number req_number);
    //Call<Res_string> likeYouList(@Body Req_number req_number);

    @POST("likeYouList2")
    Call<User> likeYouList2(@Body Req_number req_number);

    @POST("matchedList")
    Call<Res_string> matchedList(@Body Req_number req_number);

    @POST("matchedList2")
    Call<User> matchedList2(@Body Req_number req_number);

    @POST("getlm")
    Call<Res_lm> getlm(@Body Req_number req_number);

    @Multipart
    @POST("imgUpload/{userID}")
    Call<Res_img> uploadAttachment(@Part MultipartBody.Part file, @Path("userID") String userID);//@Part("description") RequestBody description);@Path("filename") String filename);//, @Part("name") RequestBody description);
    //Call<JsonObject> getSuccess (@Query("getSuc);
    */
}
