package com.bkav.command.demo;

public class SampleData {
	public static final String[] DEVICE_ENTITY_TYPE = {
			"thiết bị",
		};
		
		public static final String[] FUNCTION_ENTITY_TYPE = {
			"kịch bản",
			"chế độ", 
			"chức năng"
		};
		public static final String[] DEVICE_TYPE = {
			"đèn", // LIGHT
			"rèm",//CURTAIN
			"điều hòa", //air conditioner // dieu hoa trong phong...(1 type /room)
			"công tác", // SWITCH, 
			"bình nóng lạnh",
			"tv",
			"tivi",
		};
		
		public static final String[] CONTROLS = {
			"bật",
			"tắt",
			"tăng",
			"tăng lên",
			"lên",
			"giảm",
			"giảm xuống",
			"xuống",
			"mở",
			"đóng",
			"kích họat",
			"thêm",
			"bớt",
			"lớn hơn",
			"nhỏ hơn",
			"lớn nhất",
			"nhỏ nhất",
			"đặt",
		};

		public static final String[] MIN_CONTROL = {
	    		"tắt",
	    		"nhỏ hơn",
	    		"đóng",
	    		"nhỏ nhất",    		
	    };
	    
	    public static final String[] MAX_CONTROL = {
	    		"bật",
	    		"mở",
	    		"kích hoạt",
	    		"lớn nhất",
	    };
	    
		public static final String[] LOWER_CONTROL = { 
				"giảm", 
				"bớt", };

		public static final  String[] UPPER_CONTROL = { 
				"tăng", 
				"thêm", 
				"lớn hơn", };

		public static final String[] VALUE_CONTROL = {
				"đặt",
				"điều chỉnh",
				"thay đổi"
		};
	public static final String[] ENTITYS = {
	};
	
	public static final String[] AREAS = {
		"phòng khách",
		"phòng ngủ",
		"phòng tắm",
		"phòng ăn",
		"phòng chính",
		"phòng họp",
		"phòng trung tâm",
		"hành lang",
		"bếp",
	};
	
	public static final String[] FUNCTION = {
		"buổi sáng",
		"buổi trưa",
		"buổi chiều",
		"buổi tối",
		"buổi đêm",
		"họp",
		"giải trí",
		"đi ngủ",
		"ra khỏi nhà",
		"xem phim",
		"tiêu chuẩn",
		"trở về",
		"kiểm tra nhà",
		"chào buổi sáng",
		"tập thể dục",
		"ngủ trưa",
		"tạm dừng xem phim",
		"bật đèn", 
		"tắt đèn",
		"dùng bữa",
	};
	
	public static final String[] DEVICES = {
		"rèm 1",
        "rèm 2",
        "đèn 1",
        "đèn chùm 1",
        "đèn chùm bep",
        "đèn tranh",
        "đèn rọi 1",
        "đèn hành lang",
        "đèn rọi bep",
        "đèn hắt",
        "đèn rọi trong",
        "đèn rọi ngoai",
        "quạt hút 1",
        "máy chiếu 1",
        "màn chiếu 1",
        "điều hoà 1",
//        "tốc độ gió",
//        "hướng gió",
        "rèm ngoài",
        "rèm trái ngoài",
        "rèm trái trong",
        "rèm phải",
        "bình nóng lạnh 1",
        "quạt hút 1",
	};
	public static final String[] SampleCommands = {
		// thiet bi cu the, nhom thiet bi, noi suy qua loai thiet bi (1/n thiet bi), trang thai hien tai
			
		"Bật đèn một thứ ba", // device only

		"Điều chỉnh điều hòa một tăng mười độ",	
		"Bật đèn một phòng khách",//device with room
		"Bật đèn phòng khách", // all device type in room
		"Bật tất cả đèn",
		"Bật đèn", 
		"Mở rèm", // all device type in current room (context)
		"Kích hoạt chế độ đi ngủ phòng ngủ", // function with room
		"Kích hoạt chế độ đi ngủ", // only function
		"Đặt điều hòa một hai tư độ",
		"Tăng điều hòa một mười độ",
		"Điều chỉnh điều hòa một tăng mười độ", //need status current
		"Điều chỉnh điều hòa một nhỏ nhất",
		"Tăng điều hòa phòng ngủ mười độ",
		"Tăng điều hòa phòng ngủ",
	};
	public static final String[] SampleFullCommands = {
		// thiet bi cu the, nhom thiet bi, noi suy qua loai thiet bi (1/n thiet bi), trang thai hien tai
		"Bật đèn một phòng khách mười giờ sáng thứ hai hàng tuần",//device with room
		"Bật đèn một mười giờ thứ ba", // device only
		"Bật đèn phòng khách sáu giờ chiều thứ năm hàng tuần", // all device type in room
		"Bật tất cả đèn chiều thứ tư hàng tuần",
		"Bật đèn sáng lúc mười giờ", 
		"Mở rèm sau hai giờ nữa", // all device type in current room (context)
		"Kích hoạt chế độ đi ngủ phòng ngủ sau hai tiếng nữa", // function with room
		"Kích hoạt chế độ đi ngủ lúc mười hai giờ trưa", // only function
		"Đặt điều hòa một hai mươi tư độ lúc chín giờ sáng ngày mai",
		"Tăng điều hòa một mười độ sau chín giờ nữa",
		"Điều chỉnh điều hòa một tăng mười độ độ hai tư phút nữa", //need status current
		"Điều chỉnh điều hòa một nhỏ nhất chiều thứ sáu tuần sau",
		"Tăng điều hòa phòng ngủ mười độ lúc sáu giờ sáng thứ năm tuần tới",
		"Tăng điều hòa phòng ngủ mười giờ ngày mai",
	};
	
	public static final String[] SampleTimeRepeat = {
		"hàng ngày",
		"hàng tháng",
		"hàng năm",
	};
	
	public static final String[] SampleShortSchedule = {
		"lúc 10:22",
		"lúc 22:30",
		"lúc 10:00 am",
		"lúc 6am",
		"sau 10:00 nữa"
	};
	public static final String[] SampleLongSchedule = {
		"lúc 10 giờ 10 phút",
		"lúc 22 giờ 30 phút",
		"lúc 10 giờ 30 phút sáng",
		"lúc 12 giờ đêm",
		"lúc 12 giờ trưa",
	};
	public static final String[] SampleSchedule = {
		// chu y AM, PM, thoi gian hien tai, ngay trong thang..., gio,phut,giay -> h,m,s, hh:ss
//		"2 giờ 10 phút ngày 12-12-2012",
//		"lúc 2 giờ chiều",
//		"lúc 2 giờ 10 phút",
//		"lúc 2 giờ sáng",
//		"lúc 14 giờ 10 phút",
//		"lúc 2 giờ 10 phút ngày mai",
//		"sau 2 giờ 10 phút nữa",
		"2 giờ 10 phút thứ 6 tuần này",
//		"2 giờ 10 phút thứ 6 tuần sau",
//		"sau 2 phút nữa",
	};
	public static final String[] SampleTimeSchedule = {
			// chu y AM, PM, thoi gian hien tai, ngay trong thang..., gio,phut,giay -> h,m,s, hh:ss
			"lúc 2 giờ chiều",
			"lúc 2 giờ 10 phút",
			"lúc 2 giờ sáng",
			"lúc 14 giờ 10 phút",
			"lúc 2 giờ 10 phút ngày mai",
			"sau 2 giờ 10 phút nữa",
			"2 giờ 10 phút thứ 6 tuần này",
			"2 giờ 10 phút thứ 6 tuần sau",
			"sau 2 phút nữa",
			"6 am",
			"6am",
			"12 a. m",
			"01 a . m",
			"2 giờ",
			
			"2 giờ pm",
			"2 giờ 10 phút",
			"2 giờ 10 phút",
			"2 giờ 10 phút nữa",
			"sau 2 giờ 10 phút nữa",
			
			"2 phút",
			"2 phút nữa",
			"sau 02 phút nữa",
			"lúc 2 giờ 10",
			"6 giờ 30 am",
			
			"6 giờ 30 pm",
			"6 giờ sáng",
			"3 giờ chiều",
			"11 giờ trưa",
			"11 giờ 30 đêm",
		};
	public static final String[] SampleDateSchedule = {
			// chu y AM, PM, thoi gian hien tai, ngay trong thang..., gio,phut,giay -> h,m,s, hh:ss
			"ngày 12-12-2012",
			"ngày 12 tháng 2 năm 2019",
			"ngày 12 tháng 2",
			"12 tháng 4",
			"ngày 12",
			"ngày 12-12-2012",
			"ngày 12/12/2012",
			"ngày 12_12_2012",
			"thứ sáu ngày 12_12_2012",
		};
	private SampleData() {}
}
