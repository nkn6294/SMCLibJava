package com.bkav.command.model;

/***
 * 
 */
public class ModelData {
	public static final String[] DEVICE_ENTITY_TYPE = {
		"thiet bi",
	};
	
	public static final String[] FUNCTION_ENTITY_TYPE = {
		"kich ban",
		"che do", 
		"chuc nang"
	};
	public static final String[] DEVICE_TYPE = {
		"den", // LIGHT
		"rem",//CURTAIN
		"dieu hoa", //air conditioner // dieu hoa trong phong...(1 type /room)
		"cong tac", // SWITCH, 
		"binh nong lanh",
		"tv",
	};
	
	public static final String[] CONTROLS = {
		"bat",
		"tat",
		"tang",
		"tang len",
		"len",
		"giam",
		"giam xuong",
		"xuong",
		"mo",
		"dong",
		"kich hoat",
		"them",
		"bot",
		"lon hon",
		"nho hon",
		"lon nhat",
		"nho nhat",
		"dat",
	};

	
	public static final String[] CONTEXTS = {
		"thoi gian",
		"vi tri",
		"thiet bi",
	};
	
	public static final String[] REPEATES = {
		"mot lan",//"gio nay ngay mai/tuan sau/...
		"hang ngay",
		"hang tuan",
		"hang thang",
		"hang nam",
	};
	
	public static final String[] TIMES = {
		"bay gio",
		"ngay mai",
	};
	
	public static final String[] TIMES_CONTEXT = {
		"luc",
		"sau",
		"truoc",
		"nua",
		"bat dau khi",
		"ket thuc khi",
		"cho toi khi",
		"...",
	};
	
	public static final String[] UNITS = {
		"phan tram",
		"bat",
		"tat",
		"lon nhat",
		"nho nhat",
		"trong khoang",
	};
	
	public static final String[] TIME_UNITS = {
		"giay",
		"phut",
		"gio",
		"ngay",
		"tuan",
		"thang",
		"nam"
	};
	
	public static final String[] TIME_IN_DAY = {
		"sang", // 10 GIO SANG
		"trua",
		"chieu",
		"toi",
		"buoi dem",
		"buoi sang",
		"buoi trua",
		"buoi chieu",
		"buoi toi", 
		"buoi dem"
	};
	
	public static final String[] DAY_IN_WEEK = {
		"thu hai",
		"thu ba",
		"thu tu",
		"thu nam",
		"thu sau",
		"thu bay",
		"chu nhat"
	};
	
	public static final String[] NUMBERIC = {
		"tat ca",
		"mot"
	};
	
	public static final String[] NUMBERS = {
		"khong", "mot", "hai", "ba", "bon", "nam", "sau", "bay", "tam", "chin", "muoi",
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
	};
	/***************************** RULES ******************************/
	public static final String[] NUMBERS_RULES = {
		"[NUMBERS]",
		"[NUMBERS] muoi",//20, 30...
		"[NUMBERS] mot", //21, 31
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

