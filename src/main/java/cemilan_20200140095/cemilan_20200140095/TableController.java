/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cemilan_20200140095.cemilan_20200140095;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS ROG
 */
@Controller
public class TableController {    
    @RequestMapping("/belisayur")
    public String SayurMayur(HttpServletRequest data, Model sayuran){
        Penjualan pjl = new Penjualan();
        //getting data
        String namaSayur = data.getParameter("var_namasayur");
        String hargaSayur = data.getParameter("var_hargaperkilo");        
        String jumlahSayur = data.getParameter("var_jumlahbelisayur");
        String uangBayar = data.getParameter("var_uangbayar");
        String customer = data.getParameter("uang_kembalian");
        //variabel
        Double convharga = pjl.newharga(hargaSayur);
        Double convjumlah = pjl.newjumlah(jumlahSayur);
        Double jumlahbayar = pjl.newjumlahbayar(convharga, convjumlah);
        String diskonpercent = pjl.diskon(jumlahbayar);
        Double hargadiskon = pjl.newhargadiskon(jumlahbayar, Integer.valueOf(diskonpercent));
        Double totalbayar = pjl.newtotalbayar(jumlahbayar, hargadiskon);
        pjl.math(jumlahbayar, Integer.valueOf(diskonpercent), totalbayar, hargadiskon);
        String kembaliancust = pjl.KembalianCust(convjumlah, totalbayar);
        
        sayuran.addAttribute("nama", namaSayur);
        sayuran.addAttribute("harga", convharga);
        sayuran.addAttribute("kilogram",jumlahSayur);
        sayuran.addAttribute("hargaawal",jumlahbayar); 
        sayuran.addAttribute("hargadiskon", hargadiskon);
        sayuran.addAttribute("diskonmula", diskonpercent);
        sayuran.addAttribute("totalpembayaran", totalbayar);
        sayuran.addAttribute("kembalian", kembaliancust);
        return "templatesayur";
    }
}
