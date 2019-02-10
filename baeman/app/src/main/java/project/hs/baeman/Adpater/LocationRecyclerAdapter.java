package project.hs.baeman.Adpater;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.hs.baeman.R;

import static android.content.Context.MODE_PRIVATE;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<String> mItems;

    public LocationRecyclerAdapter(Context context, ArrayList<String> items){
        this.context = context;
        mItems = items;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_location.setText(mItems.get(position));

        holder.layout_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "주소 설정 완료", Toast.LENGTH_LONG).show();

                SharedPreferences pref = context.getSharedPreferences("address", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("data", holder.tv_location.getText().toString());
                editor.commit();

                ((Activity)context).finish();
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
        private RelativeLayout layout_location;
        private TextView tv_location;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_location = (RelativeLayout)itemView.findViewById(R.id.layout_location);
            tv_location = (TextView)itemView.findViewById(R.id.tv_location);
        }
    }
}
