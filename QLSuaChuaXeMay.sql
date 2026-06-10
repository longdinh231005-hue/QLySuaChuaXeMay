CREATE DATABASE IF NOT EXISTS QLSuaChuaXeMay CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE QLSuaChuaXeMay;

-- ========================================================
-- I. TẠO CẤU TRÚC BẢNG (TABLES)
-- ========================================================

-- 1. Bảng Khách Hàng
CREATE TABLE KhachHang (
    MaKH INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15) UNIQUE NOT NULL,
    DiaChi VARCHAR(255),
    TrangThai ENUM('HoatDong', 'NgungHoatDong') DEFAULT 'HoatDong'
);

-- 2. Bảng Xe Máy
CREATE TABLE XeMay (
    BienSo VARCHAR(20) PRIMARY KEY,
    MaKH INT NOT NULL,
    HangXe VARCHAR(50),
    DongXe VARCHAR(50),
    SoKhung VARCHAR(50),
    SoMay VARCHAR(50),
    TrangThai ENUM('HoatDong', 'NgungHoatDong') DEFAULT 'HoatDong',
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH) ON DELETE CASCADE
);

-- 3. Bảng Nhân Viên
CREATE TABLE NhanVien (
    MaNV INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    MatKhau VARCHAR(255) NOT NULL,
    VaiTro ENUM('NhanVienThuNgan', 'KyThuatVien', 'TruongNhom', 'ThuKho', 'QuanLy') NOT NULL
);

-- 4. Bảng Phụ Tùng
CREATE TABLE PhuTung (
    MaPT INT AUTO_INCREMENT PRIMARY KEY,
    TenPT VARCHAR(255) NOT NULL,
    DonViTinh VARCHAR(50),
    SoLuongTon INT DEFAULT 0,
    DonGia DECIMAL(10, 2) NOT NULL,
    TonKhoToiThieu INT DEFAULT 5,
    TrangThai ENUM('HoatDong', 'NgungSuDung') DEFAULT 'HoatDong'
);

-- 5. Bảng Phiếu Sửa Chữa
CREATE TABLE PhieuSuaChua (
    MaPhieu INT AUTO_INCREMENT PRIMARY KEY,
    BienSo VARCHAR(20) NOT NULL,
    MaNV_TiepNhan INT NOT NULL,
    MaNV_KyThuat INT DEFAULT NULL,
    NgayTiepNhan DATETIME DEFAULT CURRENT_TIMESTAMP,
    MoTaLoi TEXT,
    TrangThai ENUM('ChuaSua', 'DangSua', 'DaSuaXong', 'DaThanhToan') DEFAULT 'ChuaSua',
    FOREIGN KEY (BienSo) REFERENCES XeMay(BienSo),
    FOREIGN KEY (MaNV_TiepNhan) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaNV_KyThuat) REFERENCES NhanVien(MaNV)
);

-- 6. Bảng Chi Tiết Sửa Chữa
CREATE TABLE ChiTietSuaChua (
    MaPhieu INT NOT NULL,
    MaPT INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGiaThucTe DECIMAL(10, 2) NOT NULL,
    TienCong DECIMAL(10, 2) DEFAULT 0,
    PRIMARY KEY (MaPhieu, MaPT),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuSuaChua(MaPhieu) ON DELETE CASCADE,
    FOREIGN KEY (MaPT) REFERENCES PhuTung(MaPT)
);

-- 7. Bảng Xuất Kho
CREATE TABLE XuatKho (
    MaPhieuXuat INT AUTO_INCREMENT PRIMARY KEY,
    MaPhieuSuaChua INT NOT NULL,
    MaNV_ThuKho INT NOT NULL,
    NgayXuat DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (MaPhieuSuaChua) REFERENCES PhieuSuaChua(MaPhieu),
    FOREIGN KEY (MaNV_ThuKho) REFERENCES NhanVien(MaNV)
);

-- 8. Bảng Hóa Đơn
CREATE TABLE HoaDon (
    MaHD INT AUTO_INCREMENT PRIMARY KEY,
    MaPhieu INT NOT NULL UNIQUE,
    MaNV_ThuNgan INT NOT NULL,
    NgayThanhToan DATETIME DEFAULT CURRENT_TIMESTAMP,
    TongTienPhuTung DECIMAL(10, 2) DEFAULT 0,
    TongTienCong DECIMAL(10, 2) DEFAULT 0,
    ThanhTien DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (MaPhieu) REFERENCES PhieuSuaChua(MaPhieu),
    FOREIGN KEY (MaNV_ThuNgan) REFERENCES NhanVien(MaNV)
);

-- 1. Insert 7 Nhân viên (Thêm thợ và thu ngân để dữ liệu đa dạng)
INSERT INTO NhanVien (HoTen, SoDienThoai, Email, MatKhau, VaiTro) VALUES 
('Đinh Thái Viết Long', '0911111222', 'longdinh@gmail.com', 'admin123', 'QuanLy'),          -- ID 1
('Lê Quang Duy', '0922222333', 'duyle@gmail.com', '123456', 'NhanVienThuNgan'),       -- ID 2
('Võ Anh Khoa', '0933333444', 'khoavo@gmail.com', '123456', 'TruongNhom'),            -- ID 3
('Lê Huỳnh Gia Bảo', '0944444555', 'baole@gmail.com', '123456', 'ThuKho'),            -- ID 4
('Nguyễn Văn Thợ', '0955555666', 'tho1@gmail.com', '123456', 'KyThuatVien'),          -- ID 5
('Trần Văn Máy', '0966666777', 'maytran@gmail.com', '123456', 'KyThuatVien'),         -- ID 6 (Thợ mới)
('Phạm Thị Thu', '0977777888', 'thupham@gmail.com', '123456', 'NhanVienThuNgan');     -- ID 7 (Thu ngân mới)

-- 2. Insert 10 Khách hàng
INSERT INTO KhachHang (HoTen, SoDienThoai, DiaChi) VALUES 
('Nguyễn Văn A', '0901000001', 'Quận 12, TP.HCM'),
('Trần Thị B', '0901000002', 'Gò Vấp, TP.HCM'),
('Lê Văn C', '0901000003', 'Tân Phú, TP.HCM'),
('Phạm Minh D', '0901000004', 'Bình Tân, TP.HCM'),
('Hoàng Thị E', '0901000005', 'Quận 10, TP.HCM'),
('Vũ Văn F', '0901000006', 'Phú Nhuận, TP.HCM'),
('Đỗ Thị G', '0901000007', 'Bình Thạnh, TP.HCM'),
('Ngô Văn H', '0901000008', 'Quận 3, TP.HCM'),
('Bùi Minh I', '0901000009', 'Quận 1, TP.HCM'),
('Lý Thị K', '0901000010', 'Thủ Đức, TP.HCM');

-- 3. Insert 10 Xe máy
INSERT INTO XeMay (BienSo, MaKH, HangXe, DongXe, SoKhung, SoMay) VALUES 
('59A1-11111', 1, 'Honda', 'AirBlade', 'SK001', 'SM001'),
('59G2-22222', 2, 'Yamaha', 'Exciter', 'SK002', 'SM002'),
('59T3-33333', 3, 'Honda', 'Vision', 'SK003', 'SM003'),
('59B4-44444', 4, 'Suzuki', 'Raider', 'SK004', 'SM004'),
('59H5-55555', 5, 'Honda', 'Lead', 'SK005', 'SM005'),
('59P6-66666', 6, 'Yamaha', 'Sirius', 'SK006', 'SM006'),
('59S7-77777', 7, 'Piaggio', 'Vespa', 'SK007', 'SM007'),
('59F8-88888', 8, 'Honda', 'SH Mode', 'SK008', 'SM008'),
('59K9-99999', 9, 'Yamaha', 'Grande', 'SK009', 'SM009'),
('59L1-00000', 10, 'Honda', 'Wave Alpha', 'SK010', 'SM010');

-- 4. Insert 10 Phụ tùng
INSERT INTO PhuTung (TenPT, DonViTinh, SoLuongTon, DonGia) VALUES 
('Nhớt Máy Castrol 1L', 'Chai', 45, 140000),
('Bugi NGK C7', 'Cái', 80, 45000),
('Má Phanh Trước Honda', 'Bộ', 30, 90000),
('Lọc Gió Yamaha', 'Cái', 25, 65000),
('Săm Xe Máy Yokohama', 'Cái', 60, 75000),
('Lốp Xe Máy Michelin', 'Cái', 15, 450000),
('Dây Curoa Bando', 'Sợi', 12, 280000),
('Ắc Quy GS 12V', 'Bình', 10, 320000),
('Bình Xăng Con Keihin', 'Bộ', 5, 550000),
('Nhông Sên Dĩa DID', 'Bộ', 8, 380000);

-- 5. Insert 10 Phiếu sửa chữa (Phân bổ NV Tiếp nhận và NV Kỹ thuật hợp lý)
INSERT INTO PhieuSuaChua (BienSo, MaNV_TiepNhan, MaNV_KyThuat, MoTaLoi, TrangThai) VALUES 
('59A1-11111', 2, 5, 'Thay nhớt định kỳ và kiểm tra phanh trước', 'DaThanhToan'), 
('59G2-22222', 3, 6, 'Xe bị hụt ga, đề không nổ', 'DaThanhToan'),              
('59T3-33333', 7, 5, 'Thay lốp sau và kiểm tra nồi', 'DaThanhToan'),          
('59B4-44444', 2, 6, 'Thay bugi và lọc gió', 'DaThanhToan'),
('59H5-55555', 3, 5, 'Thay bình ắc quy mới', 'DaThanhToan'),
('59P6-66666', 7, 6, 'Thay nhông sên dĩa và săm trước', 'DaThanhToan'),
('59S7-77777', 2, 5, 'Côn xe kêu to, máy yếu', 'DaSuaXong'),        
('59F8-88888', 3, 6, 'Bảo dưỡng toàn bộ xe', 'DaSuaXong'), 
('59K9-99999', 7, 5, 'Đứt dây curoa xe tay ga', 'DangSua'),
('59L1-00000', 3, NULL, 'Thay săm sau bị thủng', 'ChuaSua');

-- 6. Insert Chi tiết sửa chữa (Chỉ những xe đang sửa hoặc đã xong mới có chi tiết dùng phụ tùng)
INSERT INTO ChiTietSuaChua (MaPhieu, MaPT, SoLuong, DonGiaThucTe, TienCong) VALUES 
(1, 1, 1, 140000, 30000),
(1, 3, 1, 90000, 40000),
(2, 2, 1, 45000, 50000),
(3, 6, 1, 450000, 60000),
(4, 2, 1, 45000, 20000),
(4, 4, 1, 65000, 20000),
(5, 8, 1, 320000, 30000),
(6, 10, 1, 380000, 70000),
(7, 7, 1, 280000, 80000),
(8, 5, 1, 75000, 30000);

-- 7. Insert Lịch sử xuất kho (Chỉ lấy các phiếu đã sửa xong hoặc đang sửa - do Thủ kho Bảo ID 4 xuất)
INSERT INTO XuatKho (MaPhieuSuaChua, MaNV_ThuKho) VALUES 
(1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4), (7, 4), (8, 4), (9, 4);

-- 8. Insert Hóa đơn thanh toán (CHỈ LẬP HÓA ĐƠN CHO 6 XE ĐÃ THANH TOÁN - Phân bổ cho 2 Thu Ngân)
INSERT INTO HoaDon (MaPhieu, MaNV_ThuNgan, TongTienPhuTung, TongTienCong, ThanhTien) VALUES 
(1, 2, 230000.00, 70000.00, 300000.00),
(2, 7, 45000.00, 50000.00, 95000.00),
(3, 2, 450000.00, 60000.00, 510000.00),  
(4, 7, 110000.00, 40000.00, 150000.00),
(5, 2, 320000.00, 30000.00, 350000.00),
(6, 7, 380000.00, 70000.00, 450000.00);