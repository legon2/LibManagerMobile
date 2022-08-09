package fpoly.edu.libmanagermobile.database;

public class Data_SQLite {
    public static final String INSERT_THU_THU =
            "INSERT INTO ThuThu (maTT, hoTen, matKhau) values \n" +
                    "('admin' , 'Lương Ngọc Hải' , 'admin'),\n" +
                    "('minhdat', 'Nguyễn Minh Đạt' , 'minhdat'),\n" +
                    "('ngocanh' , 'Lê Ngọc Anh' , 'ngocanh')";
    public static final String INSERT_THANH_VIEN =
            "INSERT INTO ThanhVien (hoTen, namSinh) values \n" +
                    "('Đỗ Ngọc Hội', '2002'),\n" +
                    "('Lương Mỹ Quyên', '2007'),\n" +
                    "('Trần Hoàng Trang', '2001'),\n" +
                    "('Trần Tuấn Công', '2002'),\n" +
                    "('Lý Ngọc Sang', '2002'),\n" +
                    "('Nguyễn Đình Thi', '2002'),\n" +
                    "('Trần Quốc Thông', '2002'),\n" +
                    "('Trần Ngọc Trà', '2002'),\n" +
                    "('Đoàn Hữu Đoan', '2002'),\n" +
                    "('Hoàng Minh Phú', '2002'),\n" +
                    "('Lê Gia Bảo', '2002'),\n" +
                    "('Lê Văn Minh Ngọc', '2002'),\n" +
                    "('Dương Quang Nhân Lực', '2002'),\n" +
                    "('Đinh Viết Phong', '2002'),\n" +
                    "('Nguyễn Thái Quý', '2002'),\n" +
                    "('Trần Văn Danh', '2002'),\n" +
                    "('Trần Tiến Đạt', '2002'),\n" +
                    "('Nguyễn Hưng', '2002'),\n" +
                    "('Huỳnh Quang Trường', '2002'),\n" +
                    "('Nguyễn Hoàng Hưng', '2002'),\n" +
                    "('Trần Quốc Phương', '2002'),\n" +
                    "('Nguyễn Văn Nhật', '2002'),\n" +
                    "('Hồ Nhật Đức', '2002'),\n" +
                    "('Phan Minh Nhân', '2002'),\n" +
                    "('Nguyễn Chí Cường', '2002'),\n" +
                    "('Nguyễn Nam Hiếu', '2002'),\n" +
                    "('Nguyễn Phan Nhật Nguyên', '2002'),\n" +
                    "('Nguyễn Thanh Tâm', '2002'),\n" +
                    "('Hoàng Hồng Phúc', '2002'),\n" +
                    "('Chế Văn Linh', '2002'),\n" +
                    "('Nguyễn Hồng Nam', '2002'),\n" +
                    "('Nguyễn Lương Hoàng Vĩ', '2002'),\n" +
                    "('Đào Duy Hận', '2002'),\n" +
                    "('Trần Đình Toàn', '2002'),\n" +
                    "('Đoàn Văn Lộc', '2002'),\n" +
                    "('Lê Quang Cao Nguyên', '2002'),\n" +
                    "('Châu Minh Hiếu', '2002'),\n" +
                    "('Lý Quang Cường', '2002'),\n" +
                    "('Nguyễn Văn Tấn', '2002'),\n" +
                    "('Nguyễn Thái Luật', '2002'),\n" +
                    "('Lê Thị Thanh Trúc', '2002'),\n" +
                    "('Trần Thị Ngọc Hương', '2002')\n";
    public static final String INSERT_LOAI_SACH =
            "INSERT INTO LoaiSach (tenLoai) values\n" +
                    "('Lập trình Android'),\n" +
                    "('Lập trình Java'),\n" +
                    "('Lập trình Web'),\n" +
                    "('Lập trình IOS'),\n" +
                    "('Lập trình C'),\n" +
                    "('Lập trình Game')";
    public static final String INSERT_SACH =
            "INSERT INTO Sach(tenSach, giaThue, maLoai) values \n" +
                    "('Lập trình Java cơ bản','15000','2'),\n" +
                    "('Lập trình C++','15000','5'),\n" +
                    "('Lập trình Swift cơ bản','15000','4'),\n" +
                    "('Lập trình Swift nâng cao','15000','4'),\n" +
                    "('Lập trình Java nâng cao','20000','2'),\n" +
                    "('Lập trình Android cơ bản','10000','1'),\n" +
                    "('Lập trình Android nâng cao','10000','1'),\n" +
                    "('Làm quen với HTML CSS','20000','3'),\n" +
                    "('Tìm hiểu về HTML5 , CSS3','21000','3'),\n" +
                    "('Lập trình game 2D','19000','6')";
    public static final String INSERT_PHIEU_MUON =
            "INSERT INTO PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) values\n" +
                    "('admin','1','1','30000','2021/10/10',0),\n" +
                    "('admin','2','1','30000','2021/10/11',0),\n" +
                    "('admin','3','1','30000','2021/10/12',1),\n" +
                    "('admin','4','1','30000','2021/10/13',0),\n" +
                    "('admin','5','1','30000','2021/10/13',1),\n" +
                    "('admin','6','1','30000','2021/10/10',0),\n" +
                    "('admin','7','1','30000','2021/10/10',0),\n" +
                    "('admin','8','1','30000','2021/10/10',0),\n" +
                    "('minhdat','3','2','30000','2021/10/14',1)";
}