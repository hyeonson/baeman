package project.hs.baeman.Adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import project.hs.baeman.Item.InfoItem;
import project.hs.baeman.R;

public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<InfoItem> mItems;

    public InfoRecyclerAdapter(Context context, ArrayList<InfoItem> items){
        this.context = context;
        mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_info_name.setText(mItems.get(position).getInfoName());
        holder.tv_info_content.setText(mItems.get(position).getInfoContent());

    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return mItems.size();
    }



    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_info_name;
        private TextView tv_info_content;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_info_name = (TextView)itemView.findViewById(R.id.tv_info_name);
            tv_info_content = (TextView)itemView.findViewById(R.id.tv_info_content);
        }
    }

}
