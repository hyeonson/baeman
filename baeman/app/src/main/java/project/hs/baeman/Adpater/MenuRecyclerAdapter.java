package project.hs.baeman.Adpater;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import project.hs.baeman.Item.MenuItem;
import project.hs.baeman.R;

import static android.content.Context.MODE_PRIVATE;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<MenuItem> mItems;

    public MenuRecyclerAdapter(Context context, ArrayList<MenuItem> items){
        this.context = context;
        mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_menu_name.setText(mItems.get(position).getMenuName());
        holder.tv_menu_price.setText(mItems.get(position).getPrice() + "원");
        Glide.with(context).load(mItems.get(position).getImgUrl()).into(holder.iv_menu_img);

        holder.layout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "장바구니 등록 완료!", Toast.LENGTH_LONG).show();
                SharedPreferences pref = context.getSharedPreferences("bucket", MODE_PRIVATE);
                int list_size = pref.getInt("list_size", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("list_" + list_size + "_name", mItems.get(position).getMenuName());
                editor.putInt("list_" + list_size + "_price", mItems.get(position).getPrice());
                editor.putInt("list_size", list_size + 1);
                editor.commit();
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
        private LinearLayout layout_menu;
        private ImageView iv_menu_img;
        private TextView tv_menu_name;
        private TextView tv_menu_price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_menu = (LinearLayout)itemView.findViewById(R.id.layout_menu);
            iv_menu_img = (ImageView) itemView.findViewById(R.id.iv_menu_img);
            tv_menu_name = (TextView)itemView.findViewById(R.id.tv_menu_name);
            tv_menu_price = (TextView)itemView.findViewById(R.id.tv_menu_price);
        }
    }
}
