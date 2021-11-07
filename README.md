# Đồ án số 3: Hệ thống quản lý cho cửa hàng xe máy

## 1.) Thông tin đồ án
- Ngôn ngữ sử dụng: Java
- Framework sử dụng: Java Swing, Hibernate
- IDE sử dụng: Eclipse
- Hệ quản trị cơ sở dữ liệu sử dụng: SQL Server 2014
- Chương trình được lập trình trên: Windows 7 SP1 32bits

## 2.) Các chức năng đã làm được
- TOÀN BỘ chức năng mà đề yêu cầu, NGOẠI TRỪ:
   + Chức năng phân trang ở "2. Quản lí đơn hàng "

## 3.) Các điểm đáng chú ý
- Lập trình GUI (đề không yêu cầu lập trình GUI)
- Sử dụng hệ quản trị cơ sở dữ liệu để quản lí dữ liệu (sử dụng SQL Server 2014)
- Do sử dụng DBMS, TẤT CẢ các dữ liệu ở từng yêu cầu của đề bài đều kết nối với nhau, việc thêm/xóa/sửa dữ liệu ở một vị trí bất kì có thể ảnh hưởng trực tiếp đến các vị trí khác

## 4.) Link demo
https://www.youtube.com/watch?v=nMvsSakjM-k

## 5.) Hướng dẫn sử dựng
### a.) Config SQL Server
- Đầu tiên, ta hiển nhiên phải cho start SQL Server mà ta đã cài đặt bằng "SQL Server 2014 Configuration Manager"
- Tiếp theo, ta phải kích hoạt TOÀN BỘ các Protocols của SQL Server bằng "SQL Server 2014 Configuration Manager": 
   + Chọn mục SQL Server Network Configuration
   + Chọn Protocols for "instance_name" (ở máy người code, "instance_name" là MSSQLSERVER)
   + Kích hoạt cả 3 protocols là: Shared Memory, Named Pipes và TCP/IP

### b.) Config hibernate.cfg.xml (nằm trong folder release)
- Ta bắt buộc phải tạo 1 account full quyền, và BẮT BUỘC phải sử dụng SQL Server Authentication để login
- Tiếp theo, ta cần phải chỉnh sửa các properties trong file hibernate.cfg.xml theo config tương ứng CỦA RIÊNG MÁY CỦA NGƯỜI SỬ DỤNG, ở đây, ta chỉ cần thay đổi các thông số sau đây theo config của mỗi người chúng ta là được, các thông số khác giữ nguyên:
   + Port: của người code sử dụng Port mặc định của SQL Server, là 1433
   + Database name: ở bước 5.c, ta sẽ chạy script makeDBS.txt, ở đấy, người code đã cài đặt mặc định database name là: QUANLYCUAHANGXEMAY, nên không cần thay đổi
    + instance_name: của người code khi cài đặt, instance_name là MSSQL
	* Cả 3 thông số trên, ta sẽ config ở dòng:
<property name="connection.url">jdbc:jtds:sqlserver://localhost:1433/QUANLYCUAHANGXEMAY;instance=MSSQL</property>

     + username: người code đặt là sa, ta sẽ config ở dòng:
 <property name="connection.username">sa</property>

     + password: người code không đặt password, ta sẽ config ở dòng:
<property name="connection.password"></property>

### c.) Chạy script để tạo database
- Ở folder release, người code đã để 1 script có tên là makeDBS.txt, ta chỉ cần copy và paste cho chạy trên SQL Server là được
- **LƯU Ý CỰC KÌ QUAN TRỌNG:**
    + Script của người code sẽ tìm và phát hiện NẾU CÓ BẤT KÌ 1 DATABASE NÀO TÊN LÀ QUANLYCUAHANGXEMAY CÓ SẴN, NÓ SẼ DROP DATABASE ĐẤY NGAY VÀ TẠO MỚI
    + Vậy nên nếu ta có 1 database tên là QUANLYCUAHANGXEMAY, ta nên đổi cái tên trong script của người code thành QUANLYCUAHANGXEMAY_FIX chẳng hạn, và nhớ là thay đổi luôn cái database name trên hibernate.cfg.xml
### d.) Sau khi tạo thành công database, ta đã có thể chạy file release.jar nằm trong folder release để có thể sử dụng ứng dụng của người code, chúc mọi người thành công.
