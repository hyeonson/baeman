package project.hs.baeman.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import project.hs.baeman.Adpater.MenuRecyclerAdapter;
import project.hs.baeman.Data.Menu;
import project.hs.baeman.Item.MenuItem;
import project.hs.baeman.R;
import project.hs.baeman.RestDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_menu_list;
    private ArrayList<MenuItem> mItems = new ArrayList<>();

    public static MenuFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_menu, container, false);
            rv_menu_list = (RecyclerView) view.findViewById(R.id.rv_menu_list);
            Log.e("레스토랑 이름", RestDetailActivity.restaurant_name);
            for(Menu menu_one : RestDetailActivity.restaurant_menu_list)
            {
                mItems.add(new MenuItem(menu_one.getMenuNumber(), menu_one.getMenuName(), menu_one.getPrice(), "https://hanamsport.or.kr/www/images/contents/thum_detail.jpg"));
            }
            rv_menu_list.setHasFixedSize(true);
            adapter = new MenuRecyclerAdapter(getContext(), mItems);
            //rv_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_menu_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            /*
            rv_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()){
                @Override
                public boolean canScrollHorizontally() {
                    //return super.canScrollHorizontally();
                    return false;
                }

                @Override
                public boolean canScrollVertically() {
                    //return super.canScrollVertically();
                    return true;
                }
            });
            */
            rv_menu_list.setAdapter(adapter);
            //setListViewHeightBasedOnChildren(rv_menu_list);
            //adapter.notifyDataSetChanged();
        }
        return view;
    }

}
