package com.bkav.command.data;

public class SampleData {
	
	public static final String[] ENTITYS = {
			
	};
	
	public static final String[] AREAS = {
		"phong khach",
		"phong ngu",
		"phong tam",
		"phong an",
		"hanh lang",
		"bep",
		"phong chinh",
		"phong hop",
		"phong trung tam"
	};
	
	public static final String[] FUNCTION = {
		"buoi sang",
		"buoi trua",
		"buoi chieu",
		"buoi toi",
		"buoi dem",
		"hop",
		"giai tri",
		"di ngu",
		"ra khoi nha",
		"xem phim",
		"tieu chuan",
		"tro ve",
		"kiem tra nha",
		"chao buoi sang",
		"tap the duc",
		"ngu trua",
		"tam dung xem phim",
		"bat den", 
		"tat den",
		"dung bua",
		
	};
	
	public static final String[] DEVICES = {
		"rem 1",
        "rem 2",
        "den 1",
        "den chum 1",
        "den chum bep",
        "den tranh",
        "den roi 1",
        "den hanh lang",
        "den roi bep",
        "den hat",
        "den roi trong",
        "den roi ngoai",
        "quat hut 1",
        "may chieu 1",
        "man chieu 1",
        "dieu hoa 1",
//        "toc do gio",
//        "huong gio",
        "rem ngoai",
        "rem trai ngoai",
        "rem trai trong",
        "rem phai",
            "binh nong lanh 1",
            "quat hut 1",
	};
	
	public static final String[] SampleCommands = {
			
			// thiet bi cu the, nhom thiet bi, noi suy qua loai thiet bi (1/n thiet bi), trang thai hien tai
			
			"Bat den 1 phong khach",//device with room
			
			"Bat den 1", // device only
			
			"Bat den phong khach", // all device type in room
			
			"Bat tat ca den",
			"Bat den", 
			"Mo rem", // all device type in current room (context)
			
			
			"Kich hoat che do di ngu phong ngu", // function with room
			"Kich hoat che do di ngu", // only function
			
			"Dat dieu hoa 1 24 do",
			"Tang dieu hoa 1 10 do",
			"Dieu chinh dieu hoa 1 giam 10 do", //need status current
			"Dieu chinh dieu hoa 1 nho nhat",
			
			"Tang dieu hoa phong ngu 10 do",
			"Tang dieu hoa phong ngu",
			
	};
	
	public static final String[] SampleCommands2 = {
			
			// thiet bi cu the, nhom thiet bi, noi suy qua loai thiet bi (1/n thiet bi), trang thai hien tai
			
			"Bat den 1 phong khach thu hai",//device with room
			
			"Bat den 1 thu ba", // device only
			
			"Bat den phong khach sang thu nam", // all device type in room
			
			"Bat tat ca den chieu thu tu hang tuan",
			"Bat den sang chu nhat", 
			"Mo rem", // all device type in current room (context)
			
			
			"Kich hoat che do di ngu phong ngu", // function with room
			"Kich hoat che do di ngu", // only function
			
			"Dat dieu hoa 1 24 do",
			"Tang dieu hoa 1 10 do",
			"Dieu chinh dieu hoa 1 tang 10 do", //need status current
			"Dieu chinh dieu hoa 1 nho nhat",
			
			"Tang dieu hoa phong ngu 10 do",
			"Tang dieu hoa phong ngu",
			
	};
	public static final String[] SampleShortSchedule = {
		"luc 10:22",
		"luc 22:30",
		"luc 10:00 am",
		"luc 6am",
		"sau 10:00 nua"
	};
	public static final String[] SampleLongSchedule = {
			"luc 10 gio 10 phut",
			"luc 22 gio 30 phut",
			"luc 10 gio 30 phut sang",
			"luc 12 gio dem",
			"luc 12 gio trua",
	};
	public static final String[] SampleSchedule = {
			// chu y AM, PM, thoi gian hien tai, ngay trong thang..., gio,phut,giay -> h,m,s, hh:ss
			"luc 2 gio 10 phut",
			"luc 2 gio sang",
			"luc 2 gio chieu",
			"luc 14 gio 10 phut",
			"luc 2 gio 10 phut ngay mai",
			"sau 2 gio 10 phut nua",
			"2 gio 10 phut ngay dd-MM-yyyy",
			"2 gio 10 phut thu 6 tuan nay",
			"2 gio 10 phut thu 6 tuan sau"
	};
	public static final String[] SampleSchedule2 = {
			// chu y AM, PM, thoi gian hien tai, ngay trong thang..., gio,phut,giay -> h,m,s, hh:ss
			"lúc 2 giờ chiều",
			"lúc 2 giờ 10 phút",
			"lúc 2 giờ sáng",
			"lúc 14 giờ 10 phút",
			"lúc 2 giờ 10 phút ngày mai",
			"sau 2 giờ 10 phút nữa",
			"2 giờ 10 phút ngày 12-12-2012",
			"2 giờ 10 phút thứ 6 tuần này",
			"2 giờ 10 phút thứ 6 tuần sau",
			"sau 2 phút nữa",
	};
	private SampleData() {}
}
