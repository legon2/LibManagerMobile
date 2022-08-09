package fpoly.edu.libmanagermobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.edu.libmanagermobile.R;
import fpoly.edu.libmanagermobile.dao.ThuThuDAO;
import fpoly.edu.libmanagermobile.model.ThuThu;


public class AddUserFragment extends Fragment {
    EditText edUser, edHoTen , edPass, edRePass;
    Button btnSave, btnCancel;

    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        edUser = v.findViewById(R.id.edUser);
        edHoTen = v.findViewById(R.id.edHoTen);
        edPass = v.findViewById(R.id.edPass);
        edRePass = v.findViewById(R.id.edRePass);
        btnSave = v.findViewById(R.id.btnSavelUser);
        btnCancel = v.findViewById(R.id.btnCancellUser);
        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edUser.getText().toString());
                thuThu.setHoTen(edHoTen.getText().toString());
                thuThu.setMatKhau(edPass.getText().toString());
                if (validate()>0){
                    if (dao.insert(thuThu)>0){
                        Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    private int validate(){
        int check = 1;
        if (edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 ||
        edRePass.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}