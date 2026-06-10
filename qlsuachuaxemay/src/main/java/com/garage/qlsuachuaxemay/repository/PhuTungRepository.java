package com.garage.qlsuachuaxemay.repository;

import com.garage.qlsuachuaxemay.entity.PhuTung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhuTungRepository extends JpaRepository<PhuTung, Integer> {
    // Câu lệnh tùy chỉnh để lấy các phụ tùng sắp hết hàng (dùng cho Báo cáo tồn kho)
    @Query("SELECT p FROM PhuTung p WHERE p.soLuongTon < p.tonKhoToiThieu")
    List<PhuTung> findPhuTungCanNhapHang();
}