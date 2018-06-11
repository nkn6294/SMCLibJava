package com.bkav.command.model;

/***
 * 
 */
public class ModelData {
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
	public static final String[] CONTEXTS = {
		"thời gian",
		"vị trí",
		"thiết bị",
	};
	
	public static final String[] REPEATES = {
		"một lần",//"gio nay ngay mai/tuan sau/...
		"hàng ngày",
		"hàng tuần",
		"hàng tháng",
		"hàng năm",
	};
	
	public static final String[] TIMES = {
		"bây giờ",
		"ngày mai",
	};
	
	public static final String[] TIMES_CONTEXT = {
		"lúc",
		"sau",
		"trước",
		"nữa",
		"bắt đầu khi",
		"kết thúc khi",
		"cho tới khi",
	};
	
	public static final String[] UNITS = {
		"phần trăm",
		"bật",
		"tắt",
		"lớn nhất",
		"nhỏ nhất",
		"trong khoảng",
	};
	
	public static final String[] TIME_UNITS = {
		"giây",
		"phút",
		"giờ",
		"ngày",
		"tuần",
		"tháng",
		"năm"
	};
	
	public static final String[] TIME_IN_DAY = {
		"sáng", // 10 GIO SANG
		"trưa",
		"chiều",
		"tối",
		"buổi đêm",
		"buổi sáng",
		"buổi trưa",
		"buổi chiều",
		"buổi tối", 
		"buổi đêm"
	};
	
	public static final String[] DAY_IN_WEEK = {
		"thứ hai",
		"thứ ba",
		"thứ tư",
		"thứ năm",
		"thứ sáu",
		"thứ bảy",
		"chủ nhật"
	};
	
	public static final String[] NUMBERIC = {
		"tất cả",
		"một"
	};
	
	public static final String[] NUMBERS = {
		"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười",
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
	};
	
	public static final String[] ALL_AMOUNT = {
			"tất cả",
			"toàn bộ",
		};
	public static final String[] SOME_AMOUNT = {
		"một vài",	
	};
	
	public static final String[] ONLY_AMOUNT = {
		"duy nhất",
		"chỉ"
	};
	
	/***************************** RULES ******************************/
	public static final String[] NUMBERS_RULES = {
		"[NUMBERS]",
		"[NUMBERS] mươi",//20, 30...
		"[NUMBERS] một", //21, 31
		"[NUMBERS][NUMBERS]",		
	};
	
	public static final String[] TIME_RULES = {
			//00:00, 10 gio kem 20 -> 9:40
		"<NUMBERS_RULES><TIME_UNITS>(TOI)",
		"<DAY_IN_WEEK>(TOI)",
		"<DAY_IN_WEEK>(NAY)",
		"<DAY_IN_WEEK>(TUAN)(TOI)",
		"<DAY_IN_WEEK>(TUAN)(NAY)",
	};
	
	
	public static final String[] COMMAND_RULES = {
		"[CONTEXT]<CONTROL><ENTITY>[<TIME>]",
		"<CONTROL><ENTITY>[<VALUE>][<TIME>]",
		""
	};
	
	private ModelData() {}
}


/*
-----------------CONTEXT----------------------
- VI TRI: PHONG, THIET BI
- THOI GIAN: thoi gian hien tai.
- TRANG THAI: 

------------------ENTITY----------------------
ROOM (area)
DEVICE  [ROOM]
FUNCTION [ROOM]
TIME [chinh xac/tuong doi]


-----------------CONTROL----------------------
BAT/TAT
MO/DONG
KICH HOAT
TANG/GIAM [value]
THEM/BOT [value]
LON/NHO [HON/NHAT]

-------------------TIME------------------------
NGAY/THANG/NAM
NGAYMAI, TUAN TOI, 2 NGAY TOI, THANG TOI...
LUC, SAU (sau 1 tieng nua), NUA.
SANG/TRUA/CHIEU/TOI/DEM


-----------------UNIT VALUE--------------------------
- 0/1
- %
- KHOANG GIA TRI

-----------------REPEATE---------------
- HANG NGAY, HANG TUAN, HANG THANG, HANG NAM
- MOT LAN (GIO NAY NGAY MAI)
- BAT DAU/KET THUC - TU/DEN.

________________________________________________________________________
COMMAND
________________________________________________________________________
[CONTEXT]<CONTROL><ENTITY>[<TIME>]-> [ROOM][KICH-HOAT][FUNCTION]

STATIC DATA

RARELY CHANGED:
- LIST DEVICEs, LIST AREAs
NORMAL CHANGE:
- FUNCTION
USUALLY:
*/ 

