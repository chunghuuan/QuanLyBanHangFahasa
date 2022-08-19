package admin1.ufm.quanlybanhangfahasa.Control_DB_Fahasa;

import android.app.Activity;
import android.os.StrictMode;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._ChiTietDH;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._GioHang;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._TaiKhoan;
import admin1.ufm.quanlybanhangfahasa.DAO_Fahasa._DonHang;

public class Gmail {
    private String tilte, message;
    private Activity activity;

    public Gmail(Activity activity) {
        this.activity = activity;
    }

    public boolean sendXacNhanDH(int maDH) {

        Data_DonHang donHangData = new Data_DonHang(activity);
        _DonHang donHang = donHangData.dLDH(maDH);
        Data_ChiTietDH chiTietDHData = new Data_ChiTietDH(activity);
        ArrayList<_ChiTietDH> chiTietDHList = new ArrayList<>();
        chiTietDHList = chiTietDHData.layDLChiTietDH(maDH);

        Data_TaiKhoan taiKhoanData = new Data_TaiKhoan(activity);
        Data_KhachHang khachHangData = new Data_KhachHang(activity);
        int maKH = donHang.getMaKH();
        int maTK = khachHangData.layMaTK(maKH);

        _TaiKhoan taiKhoan = taiKhoanData.layThongTin2(maTK);
        String sdt = taiKhoan.getSdt();
        String email = taiKhoan.getEmail();

        String tenKH = donHang.getTenKH();
        String tenNV = donHang.getTenNV();
        String DiaChiGiaoHang = donHang.getDiaChiGiaoHang();
        String maVoucher = donHang.getMaVoucher();
        int tyLeGiam = (int) (donHang.getTyLeGiam() * 100);
        String sTyLeGiam = tyLeGiam + "%";
        String tenHTTT = donHang.getTenHTTT();
        String yeuCauKH = donHang.getYeuCauKH();

        String chuDe = "Đơn hàng " + maDH + " đang được vận chuyển ";
        String noiDung = "\n" +
                "\n" + tenKH + " thân mến !" +
                "\nĐơn hàng " + maDH + " đang được giao đến bạn bởi VNPOST. " +
                "Bạn sẽ nhận được cuộc gọi từ đơn vị vận chuyển để thông báo thời gian giao hàng cụ thể"
                + "\n" +
                "\nBước tiếp theo" +
                "\n1. Bạn vui lòng chuẩn bị sẵn số tiền mặt tương ứng để thuận tiện cho việc thanh toán." +
                "\n2. Bạn có thể kiểm tra ngoại quan sản phẩm (nhãn hiệu, mẫu mã, màu sắc, số lượng, ...) " +
                "trước khi thanh toán và có thể từ chối nhận hàng nếu không ưng ý. Vui lòng không dùng thử sản phẩm."
                + "\n" +
                "\nĐơn hàng sẽ được giao đến:" +
                "\nTên:" + tenKH +
                "\nNơi nhận:" + DiaChiGiaoHang +
                "\nGhi chú:" + yeuCauKH +
                "\nSố điện thoại:" + sdt +
                "\nEmail:" + email +
                "\n" +
                "\nCHI TIẾT ĐƠN HÀNG:" +
                "\n";
        for (int i = 0; i < chiTietDHList.size(); i++) {
            String addNoiDung1 = "\n" + i + ": " + chiTietDHList.get(i).getTenSach() +
                    "\nSố lượng mua: " + chiTietDHList.get(i).getSoLuong() + "\n";
            noiDung = noiDung + addNoiDung1;
        }
        String addNoiDung2 = "\nHình thức thanh toán:" + tenHTTT
                + "\nVoucher:" + maVoucher + "\t Tỷ lệ giảm:" + sTyLeGiam;
        String addNoiDung3 = "\n" + "\nTổng thành tiền:" + (int) donHang.getTongTien() + "đ" +
                "\n Nhân viên thực hiện: " + tenNV +
                "\n FAHASA xin chân thành cảm ơn quý khách !";
        noiDung = noiDung + addNoiDung2 + addNoiDung3;

        String nguoiNhan = email;

        if (sendMail(chuDe, noiDung, nguoiNhan)) {
            return true;
        }
        return false;
    }

    public boolean sendDaTaoDH(_DonHang donHang, ArrayList<_GioHang> gioHangList, int maTK, String tenKH, int maDH) {

        Data_TaiKhoan taiKhoanData = new Data_TaiKhoan(activity);
        _TaiKhoan taiKhoan = taiKhoanData.layThongTin2(maTK);
        String sdt = taiKhoan.getSdt();
        String email = taiKhoan.getEmail();

        if (donHang.getGhiChu() == null) {
            donHang.setGhiChu("Không có");
        }
        String chuDe = "FAHASA đã nhận đơn hàng " + maDH + " của bạn !";
        String noiDung = "Cám ơn bạn đã đặt hàng tại FAHASA" +
                "\n" +
                "\nXin chào " + tenKH +
                "\nFAHASA đã nhận được yêu cầu đặt hàng của bạn và đang chờ xử lý nhé. Bạn sẽ nhận được thông báo tiếp theo khi đơn hàng đã sẵn sàng được giao." + "\n" +
                "\nĐơn hàng sẽ được giao đến:" +
                "\nTên:" + tenKH +
                "\nNơi nhận:" + donHang.getDiaChiGiaoHang() +
                "\nGhi chú:" + donHang.getGhiChu() +
                "\nSố điện thoại:" + sdt +
                "\nEmail:" + email +
                "\n" +
                "\nCHI TIẾT ĐƠN HÀNG:" +
                "\n";
        for (int i = 0; i < gioHangList.size(); i++) {
            String addNoiDung1 = "\n" + i + ": " + gioHangList.get(i).getTenSach() +
                    "\nSố lượng mua: " + gioHangList.get(i).getSlMua() + "\n";
            noiDung = noiDung + addNoiDung1;
        }
        String addNoiDung2 = "\n" + "\nTổng thành tiền:" + (int) donHang.getTongTien() + "đ" +
                "\n Nhân viên chúng tôi sẽ sớm liên lạc lại với bạn, hãy nhớ bắt máy nhé !" +
                "\n FAHASA xin chân thành cảm ơn quý khách !";
        noiDung = noiDung + addNoiDung2;

        String nguoiNhan = email;


        if (sendMail(chuDe, noiDung, nguoiNhan)) {
            return true;
        }
        return false;
    }

    public boolean sendMail(String chuDe, String noiDung, String nguoiNhan) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final String username = "fahasavn7@gmail.com"; //USERNAME TÀI KHOẢN GỬI
        final String password = "7tVUt:ua57DZ9k5"; // PASSWORD TÀI KHOẢN GỬI
        Properties pros = new Properties();
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.enable", "true");
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.port", "587"); // PORT GUI (587)
        Session session = javax.mail.Session.getInstance(pros, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        int count = 0;
        try {
            count++;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(nguoiNhan)); // ĐỊA CHỈ GMAIL NGƯỜI NHẬN
            message.setSubject(chuDe); // CHỦ ĐỀ
            message.setText(noiDung); // NỘI DUNG
            Transport.send(message);
            System.out.println("THÀNH CÔNG");
        } catch (MessagingException e) {
            System.out.println("THAT BAI");
            System.out.println(e);
        }
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }
}
