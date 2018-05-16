package com.bkav.command.test;

public class SampleData {
	
	public final static String[] ENTITYS = {
			
	};
	
	public final static String[] AREAS = {
		"phong khach",
		"phong ngu",
		"phong tam",
		"phong an",
		"hanh lang",
		"bep",
		"phong chinh",
		"phong hop",
	};
	
	public final static String[] FUNCTION = {
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
	
	public final static String[] DEVICES = {
		"rem 1",
        "rem 2",
        "den chum",
        "den chum bep",
        "den tranh",
        "den roi",
        "den hanh lang",
        "den roi bep",
        "den hat",
        "den roi trong",
        "den roi ngoai",
        "quat hut",
        "may chieu",
        "man chieu",
        "dieu hoa",
        "toc do gio",
        "huong gio",
        "rem ngoai",
        "rem trai ngoai",
        "rem trai trong",
        "rem phai",
            "binh nong lanh",
            "quat hut",
	};
	
	public final static String[] SampleCommands = {
			
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
			"Tang dieu hoa 1 10 do",
			"Dieu chinh dieu hoa 1 giam 10 do", //need status current
			"Dieu chinh dieu hoa 1 nho nhat",
			
			"Tang dieu hoa phong ngu 10 do",
			"Tang dieu hoa phong ngu",
			
	};
	
	public final static String[] SampleCommands2 = {
			
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
			"Tang dieu hoa 1 10 do",
			"Dieu chinh dieu hoa 1 tang 10 do", //need status current
			"Dieu chinh dieu hoa 1 nho nhat",
			
			"Tang dieu hoa phong ngu 10 do",
			"Tang dieu hoa phong ngu",
			
	};
	
	public final static String[] SampleSchedule = {
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
}
