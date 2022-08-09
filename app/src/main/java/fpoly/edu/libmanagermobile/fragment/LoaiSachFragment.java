package fpoly.edu.libmanagermobile.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.edu.libmanagermobile.adapter.LoaiSachAdapter;
import fpoly.edu.libmanagermobile.R;
import fpoly.edu.libmanagermobile.dao.LoaiSachDAO;
import fpoly.edu.libmanagermobile.model.LoaiSach;

public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach> list;
    LoaiSachAdapter adapter;
    Dialog dialog;
    EditText edMaLoaiSach, edTenLoaiSach;
    Button btnSave, btnCancel;
    static LoaiSachDAO dao;
    LoaiSach item;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab);
        dao = new LoaiSachDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);

            }
        });
        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        capNhapLv();
        return v;
    }
    void capNhapLv(){
        list =(ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lvLoaiSach.setAdapter(adapter);

    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xoá loại sách");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dao.delete(Id);
                        Toast.makeText(getActivity(), "Đã xoá thành công", Toast.LENGTH_SHORT).show();
                        capNhapLv();
                        dialog.cancel();
                        
                    }
                });
        builder.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLoaiSach = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiSach = dialog.findViewById(R.id.edTenLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSaveLoaiSach);

        edMaLoaiSach.setEnabled(false);
        if (type != 0){
            edMaLoaiSach.setText(String.valueOf(item.getMaLoai()));
            edTenLoaiSach.setText(item.getTenLoai());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edTenLoaiSach.getText().toString());
                if (validate()>0) {
                    if (type == 0) {
                        //type = 0 (insert)
                        if (dao.insert(item)>0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //type = 1(update)
                        item.setMaLoai(Integer.parseInt(edMaLoaiSach.getText().toString()));
                        if (dao.update(item)>0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhapLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }
    public int validate(){
        int check = 1;
        if (edTenLoaiSach.getText().length() ==0 ){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
        }
    }
