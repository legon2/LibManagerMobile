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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.edu.libmanagermobile.adapter.PhieuMuonAdapter;
import fpoly.edu.libmanagermobile.adapter.SachSpinnerAdapter;
import fpoly.edu.libmanagermobile.adapter.ThanhVienSpinnerAdapter;
import fpoly.edu.libmanagermobile.R;
import fpoly.edu.libmanagermobile.dao.PhieuMuonDAO;
import fpoly.edu.libmanagermobile.dao.SachDAO;
import fpoly.edu.libmanagermobile.dao.ThanhVienDAO;
import fpoly.edu.libmanagermobile.model.PhieuMuon;
import fpoly.edu.libmanagermobile.model.Sach;
import fpoly.edu.libmanagermobile.model.ThanhVien;


public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    int maThanhVien;

    SachSpinnerAdapter  sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV , positionSach;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fab);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        //nh???n v?? gi???
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1); //update
                    return false;
            }
        });
        capNhatLv();
        return v;
    }
    void capNhatLv(){
        list =(ArrayList<PhieuMuon>) dao.getAll();
        adapter=new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        //set ngay thue
        tvNgay.setText("Ng??y thu??: "+sdf.format(new Date()));
        edMaPM.setEnabled(false);

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                //Toast.makeText(context,"Ch???n:"+listThanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        //l???y m?? loais??ch
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Ti???n thu??: "+tienThue+" VN??");
         //       Toast.makeText(context,"Ch???n:"+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //edit set data l??n form
        if (type != 0){
            edMaPM.setText(String.valueOf(item.getMaPM()));

            for (int i = 0; i < listThanhVien.size(); i++)
                if (item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);

                for (int i = 0 ; i < listSach.size(); i++)
                    if (item.getMaSach() == (listSach.get(i).getMaSach())){
                        positionSach = i;
                    }
                spSach.setSelection(positionSach);

                    tvNgay.setText("Ng??y thu??: "+sdf.format(item.getNgay()));
                    tvTienThue.setText("Ti???n thu??: "+item.getTienThue()+" VN??");
                    if (item.getTraSach() == 1){
                        chkTraSach.setChecked(true);
                    }else {
                        chkTraSach.setChecked(false);
                    }
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
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else {
                    item.setTraSach(0);
                }
                if (type == 0){
                    // type = 0 (insert)
                    if (dao.insert(item) > 0){
                        Toast.makeText(context,"Th??m th??nh c??ng",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Th??m th???t b???i",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //type = 1(update)
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if (dao.update(item) > 0){
                        Toast.makeText(context,"S???a th??nh c??ng",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"S???a th???t b???i",Toast.LENGTH_SHORT).show();
                    }

                }
                capNhatLv();
                dialog.dismiss();
            }

        });

        dialog.show();
    }
    public void xoa(String Id){
        //s??? d???ng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xo?? phi???u m?????n");
        builder.setMessage("B???n c?? mu???n x??a kh??ng");
        builder.setCancelable(true);

        builder.setPositiveButton("C??",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //g???i function Delete
                        dao.delete(Id);
                        Toast.makeText(getActivity(), "???? xo?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                        //c???p nh???t listview
                        capNhatLv();
                        dialog.cancel();

                    }
                });
        builder.setNegativeButton("Kh??ng",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        builder.show();
    }

}