package com.bkav.command.model;

/***
 * 
 */
public class ModelData {

	public static final String[] CONTEXTS = {
		"thời gian",
	};
	
	public static final String[] ONCE_TIME_REPEAT = {
		"một lần",//"gio nay ngay mai/tuan sau/...	
	};
	
	public static final String[] DAILY_TIME_REPEAT = {
		"hàng ngày",
	};

	public static final String[] WEEKLY_TIME_REPEAT = {
		"hàng tuần",
	};

	public static final String[] MONTHLY_TIME_REPEAT = {
		"hàng tháng",
	};

	public static final String[] YEARLY_TIME_REPEAT = {
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
	public static final String[] REPEATES = {
			"một lần",//"gio nay ngay mai/tuan sau/...
			"hàng ngày",
			"hàng tuần",
			"hàng tháng",
			"hàng năm",
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


	-----------------CONTROL----------------------
	BAT/TAT
	MO/DONG
	KICH HOAT
	TANG/GIAM [value]
	THEM/BOT [value]
	LON/NHO [HON/NHAT]

	-------------------TIME------------------------
	TIME [chinh xac/tuong doi]
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

