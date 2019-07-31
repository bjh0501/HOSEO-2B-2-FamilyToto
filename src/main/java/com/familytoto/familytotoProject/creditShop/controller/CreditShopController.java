package com.familytoto.familytotoProject.creditShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.familytoto.familytotoProject.creditShop.entitiy.CreditShopList;

@Controller
public class CreditShopController {
		
	@RequestMapping("creditShop")
    public String creditShop(Model model) {
		List<CreditShopList> list = new ArrayList<>();
		list.add(new CreditShopList("아그와", "15,000","http://blogfiles.naver.net/20151204_285/omp1111_1449157086994AKIlN_JPEG/%BE%C6%B1%D7%BF%CD%28AGWA%29%2C_%BE%C6%B1%D7%BF%CD%B9%E3_%C4%AC%C5%D7%C0%CF.jpg"));
		
		model.addAttribute("list", new CreditShopList("아그와", "15,000","http://blogfiles.naver.net/20151204_285/omp1111_1449157086994AKIlN_JPEG/%BE%C6%B1%D7%BF%CD%28AGWA%29%2C_%BE%C6%B1%D7%BF%CD%B9%E3_%C4%AC%C5%D7%C0%CF.jpg"));
		model.addAttribute("test", "TEST문구");
		
		return "shop/creditShop/creditShop";
    }
	
	@RequestMapping("showProduct")
    public String showProduct() {
		return "shop/creditShop/showProduct";
    }
}
