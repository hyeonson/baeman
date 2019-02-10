package project.hs.baeman.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import project.hs.baeman.Adpater.InfoRecyclerAdapter;
import project.hs.baeman.Item.InfoItem;
import project.hs.baeman.R;
import project.hs.baeman.RestDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_info_list;
    private ArrayList<InfoItem> mItems = new ArrayList<>();

    public static InfoFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_info, container, false);
            rv_info_list = (RecyclerView) view.findViewById(R.id.rv_info_list);
            mItems.add(new InfoItem("가게 이름", RestDetailActivity.restaurant_name));
            mItems.add(new InfoItem("최소주문금액", RestDetailActivity.restaurant_minimum + "원"));
            mItems.add(new InfoItem("주문 수", RestDetailActivity.restaurant_order_count + "개"));
            mItems.add(new InfoItem("결제 방법", RestDetailActivity.restaurant_payment));
            mItems.add(new InfoItem("가게 전화번호", RestDetailActivity.restaurant_phone));
            mItems.add(new InfoItem("가게 영업 시간", RestDetailActivity.restaurant_business));
            mItems.add(new InfoItem("가게 휴일", RestDetailActivity.restaurant_dayoff));
            mItems.add(new InfoItem("배달 가능 지역", RestDetailActivity.restaurant_del_location));
            mItems.add(new InfoItem("가게 위치", RestDetailActivity.restaurant_location));
            if(RestDetailActivity.restaurant_status == 1) mItems.add(new InfoItem("현재 영업 상태", "OPEN"));
            else mItems.add(new InfoItem("현재 영업 상태", "CLOSED"));
            rv_info_list.setHasFixedSize(true);
            adapter = new InfoRecyclerAdapter(getContext(), mItems);
            rv_info_list.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_info_list.setAdapter(adapter);


        }
        return view;
    }

}
