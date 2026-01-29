import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HN_KS24_CNTT1_PhamVietQuan {
    static String[] mssvList = new String[100];
    static int count = 0;
    static final String MSSV_REGEX = "^B\\d{7}$";
    // hiển thị
    public static void displayMssv() {
        if(count == 0){
            System.out.println("Danh sách MSSV trống.");
        }else {
            System.out.print("Danh sách MSSV hiện có: ");
            for (int i =0; i< count; i++){
                System.out.printf("%d: %s",i+1, mssvList[i]);
                if(i < count-1){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    // thêm mới
    public static void addMSSV(Scanner sc) {
        if (count >= 100) {
            System.out.println("Danh sách đã đầy!");
            return;
        }

        String mssv;
        while (true) {
            System.out.print("Nhập MSSV mới: ");
            mssv = sc.nextLine();

            if (mssv.matches(MSSV_REGEX)) {
                break;
            } else {
                System.out.println("Sai định dạng ");
            }
        }

        mssvList[count] = mssv;
        count++;
        System.out.println("Thêm MSSV thành công.");
    }
    // cập nhập
    public static void updateMSSV(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách MSSV trống.");
            return;
        }

        System.out.print("Nhập vị trí cần sửa (1 - " + count + "): ");
        int index = Integer.parseInt(sc.nextLine());

        if (index < 1 || index > count) {
            System.out.println(" Vị trí không hợp lệ!");
            return;
        }

        String newMSSV;
        while (true) {
            System.out.print("Nhập MSSV mới: ");
            newMSSV = sc.nextLine();

            if (newMSSV.matches(MSSV_REGEX)) {
                break;
            } else {
                System.out.println("Sai định dạng MSSV!");
            }
        }

        mssvList[index - 1] = newMSSV;
        System.out.println("Cập nhật MSSV thành công.");
    }
    // xoá
    static void deleteMSSV(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách MSSV trống.");
            return;
        }

        System.out.print("Nhập MSSV cần xóa: ");
        String target = sc.nextLine();

        int pos = -1;
        for (int i = 0; i < count; i++) {
            if (mssvList[i].equalsIgnoreCase(target)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println(" Không tìm thấy MSSV.");
            return;
        }

        // Dồn mảng
        for (int i = pos; i < count - 1; i++) {
            mssvList[i] = mssvList[i + 1];
        }

        mssvList[count - 1] = null;
        count--;

        System.out.println("Xóa MSSV thành công.");
    }
// tìm kiếm
    static void searchMSSV(Scanner sc) {
        if (count == 0) {
            System.out.println("Danh sách MSSV trống.");
            return;
        }

        System.out.print("Nhập chuỗi cần tìm: ");
        String keyword = sc.nextLine();

        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        boolean found = false;

        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < count; i++) {
            Matcher matcher = pattern.matcher(mssvList[i]);
            if (matcher.find()) {
                System.out.println("- " + mssvList[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println(" Không có MSSV phù hợp.");
        }
    }
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1: Hiển thị MSSV");
            System.out.println("2: Thêm mới MSSV");
            System.out.println("3: Cập nhập MSSV");
            System.out.println("4: Xoá MSSV");
            System.out.println("5: Tìm kiếm MSSV");
            System.out.println("6: Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 :
                    displayMssv();
                    break;
                case 2 :
                    addMSSV(sc);
                    break;
                case 3 :
                    updateMSSV(sc);
                    break;
                case 4 :
                    deleteMSSV(sc);
                    break;
                case 5 :
                    searchMSSV(sc);
                    break;
                case 6 :
                    System.out.println("Thoát chương trình thành công.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }while (choice!=6);
        sc.close();
    }
}
