package com.java.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FController {

	@GetMapping("/")
	public String index() {
		return "index";
	}// index()
	
	@GetMapping("nList")
	public String nList() {
		return "nList";
	}// nList()
	
	
	@GetMapping("tSearch")
	public String tSearch() {
		return "tSearch";
	}// tSearch()
	
	@GetMapping("cpRent")
	public String cpRent() {
		return "cpRent";
	}// cpRent()
	
	@GetMapping("cpRent_v1")
	public String cpRent_v1() {
		return "cpRent_v1";
	}// cpRent_v1()
	@GetMapping("cpRent_v2")
	public String cpRent_v2() {
		return "cpRent_v2";
	}// cpRent_v2()
	@GetMapping("cpRent_v3")
	public String cpRent_v3() {
		return "cpRent_v3";
	}// cpRent_v3()
	
	
	@GetMapping("cp_Cart")
	public String cp_Cart() {
		return "cp_Cart";
	}// cp_Cart()
	@GetMapping("cp_Cart02")
	public String cp_Cart02() {
		return "cp_Cart02";
	}// cp_Cart02()
	@GetMapping("cp_Cart03")
	public String cp_Cart03() {
		return "cp_Cart03";
	}// cp_Cart03()
	
	

}// FController
