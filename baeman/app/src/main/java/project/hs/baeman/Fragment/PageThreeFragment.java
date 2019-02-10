package project.hs.baeman.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.hs.baeman.Adpater.RestaurantRecyclerAdapter;
import project.hs.baeman.Data.Restaurant;
import project.hs.baeman.Item.RestaurantItem;
import project.hs.baeman.Network.ApiService;
import project.hs.baeman.R;
import project.hs.baeman.Response.ResRestList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageThreeFragment extends Fragment {

    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    private ArrayList<RestaurantItem> mItems = new ArrayList<>();

    public static PageThreeFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageThreeFragment fragment = new PageThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_page_one, container, false);

            rv_restaurant_list = (RecyclerView) view.findViewById(R.id.rv_restaurant_list);
            showList(37.505623, 126.7088113, "japanese");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private void showList(double latitude, double longitude, String categry)
    {

        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResRestList> res = apiService.showRestList(latitude, longitude, categry);
        res.enqueue(new Callback<ResRestList>() {
            @Override
            public void onResponse(Call<ResRestList> call, Response<ResRestList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        List<Restaurant> restList = response.body().getData();
                        for(Restaurant rest : restList){
                            mItems.add(new RestaurantItem(rest.getRestaurantNumber(), rest.getRestaurantName(), rest.getMainMenu(), "https://hanamsport.or.kr/www/images/contents/thum_detail.jpg", rest.getStatus()));
                            rv_restaurant_list.setHasFixedSize(true);
                            adapter = new RestaurantRecyclerAdapter(getContext(), mItems);
                            rv_restaurant_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_restaurant_list.setAdapter(adapter);
                            //adapter.notifyDataSetChanged();
                        }
                    }
                    else
                    {
                        Toast.makeText(getContext(), "조회 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResRestList> call, Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }
}
