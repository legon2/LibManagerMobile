package fpoly.edu.libmanagermobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.libmanagermobile.database.DbHelper;
import fpoly.edu.libmanagermobile.model.LoaiSach;
import fpoly.edu.libmanagermobile.model.ThanhVien;
import fpoly.edu.libmanagermobile.model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;

    static final String TAG = "//=========";

    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO((context));

    }
    public void thanhVien(){
        ThanhVien tv = new ThanhVien(1,"Phạm Văn Hậu","2002");
        if (thanhVienDAO.insert(tv)>0){
            Log.i(TAG,"Thêm thành viên thành công");
        }else{
            Log.i(TAG,"Thêm thành viên thất bại");
        }
        Log.i(TAG,"==========================");
        Log.i(TAG,"Tổng số thành viên: "+thanhVienDAO.getAll().size());

        Log.i(TAG,"=========Sau khi sửa============");
        tv= new ThanhVien(0,"", "");
        thanhVienDAO.update(tv);
        Log.i(TAG,"Tổng số thành viên: "+thanhVienDAO.getAll().size());
        thanhVienDAO.delete("1");
        Log.i(TAG,"=========Sau khi xóa============");
        Log.i(TAG,"Tống số thành viên: "+thanhVienDAO.getAll().size());

    }
    public void thuThu(){
        ThuThu tt = new ThuThu("admin","Văn A","123");
        thuThuDAO.insert(tt);
        Log.i(TAG,"==========================");
        Log.i(TAG,"Tổng số thành viên: "+thuThuDAO.getAll().size());
        tt = new ThuThu("admin","Văn B","123");
        thuThuDAO.updatePass(tt);
        Log.i(TAG,"==========================");
        Log.i(TAG,"Tổng số thành viên: "+thuThuDAO.getAll().size());
        thuThuDAO.delete("0");
        Log.i(TAG,"===========sau khi Xóa==========");
        Log.i(TAG,"Tổng số thành viên: "+thuThuDAO.getAll().size());
        if (thuThuDAO.checkLogin("admin","123")>0){
            Log.i(TAG,"Đăng nhập thành công");

        }else {
            Log.i(TAG,"Đăng nhập không thành công");
        }

    }

}
