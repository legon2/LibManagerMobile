package fpoly.edu.libmanagermobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.libmanagermobile.fragment.LoaiSachFragment;
import fpoly.edu.libmanagermobile.R;
import fpoly.edu.libmanagermobile.model.LoaiSach;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach, tvTenLoaiSach;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item,null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null){
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoaiSach.setText("Mã loại: "+item.getMaLoai());
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoaiSach.setText("Tên loại: "+item.getTenLoai());
            imgDel = v.findViewById(R.id.imgDeleteLS);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaLoai()));

            }
        });

        return v;

    }
}
