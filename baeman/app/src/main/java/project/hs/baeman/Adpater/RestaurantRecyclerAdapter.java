package project.hs.baeman.Adpater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import project.hs.baeman.Item.RestaurantItem;
import project.hs.baeman.R;
import project.hs.baeman.RestDetailActivity;
import project.hs.baeman.Util.RadiusImageView;


public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<RestaurantItem> mItems;

    public RestaurantRecyclerAdapter(Context context, ArrayList<RestaurantItem> items){
        this.context = context;
        mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_restaurant_name.setText(mItems.get(position).getRestaurant());
        holder.tv_restaurant_menu.setText(mItems.get(position).getMenu());
        Glide.with(context).load(mItems.get(position).getImgUrl()).into(holder.riv_restaurant_img);
        if(mItems.get(position).getStatus() == 1){
            holder.tv_status.setBackgroundResource(R.drawable.border_with_main_color);
            holder.tv_status.setTextColor(Color.WHITE);
        }
        else{
            holder.tv_status.setText("영업아님");
            holder.tv_status.setBackgroundResource(R.drawable.border_with_soso_color);
            holder.tv_status.setTextColor(Color.DKGRAY);
        }

        holder.layout_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.tv_restaurant_name.getText().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, RestDetailActivity.class);
                intent.putExtra("restaurantNumber", mItems.get(position).getNumber());
                context.startActivity(intent);
            }
        });
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout_restaurant;
        private RadiusImageView riv_restaurant_img;
        private TextView tv_restaurant_name;
        private TextView tv_restaurant_menu;
        private TextView tv_status;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_restaurant = (LinearLayout) itemView.findViewById(R.id.layout_restaurant);
            riv_restaurant_img = (RadiusImageView) itemView.findViewById(R.id.riv_restaurant_img);
            tv_restaurant_name = (TextView) itemView.findViewById(R.id.tv_restaurant_name);
            tv_restaurant_menu = (TextView) itemView.findViewById(R.id.tv_restaurant_menu);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }

}
