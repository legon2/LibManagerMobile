package fpoly.edu.libmanagermobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import fpoly.edu.libmanagermobile.fragment.AddUserFragment;
import fpoly.edu.libmanagermobile.fragment.ChangePassFragment;
import fpoly.edu.libmanagermobile.fragment.DoanhThuFragment;
import fpoly.edu.libmanagermobile.fragment.LoaiSachFragment;
import fpoly.edu.libmanagermobile.fragment.SachFragment;
import fpoly.edu.libmanagermobile.fragment.ThanhVienFragment;
import fpoly.edu.libmanagermobile.fragment.PhieuMuonFragment;
import fpoly.edu.libmanagermobile.fragment.TopFragment;
import fpoly.edu.libmanagermobile.dao.ThuThuDAO;
import fpoly.edu.libmanagermobile.model.ThuThu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);

        toolbar = findViewById(R.id.toolbar1);

        //set toolbar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab =getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,phieuMuonFragment).commit();

        NavigationView nv = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvtUser);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        edUser.setText("Xin chào "+username+" !");

        if (user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.sub_Adduser).setVisible(true);
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,phieuMuonFragment).commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,loaiSachFragment).commit();

                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,sachFragment).commit();

                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.flContent,thanhVienFragment)
                                .commit();

                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê nhiều nhất");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,topFragment).commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Thống kê doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doanhThuFragment).commit();
                        break;
                    case R.id.sub_Adduser:
                        setTitle("Thêm người dùng");
                        AddUserFragment addUserFragment = new AddUserFragment();
                        manager.beginTransaction().replace(R.id.flContent,addUserFragment)
                                .commit();

                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction().replace(R.id.flContent,changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();

                        break;

                }
                drawer.closeDrawers();

                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }


}