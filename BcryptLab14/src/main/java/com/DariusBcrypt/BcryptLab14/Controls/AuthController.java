package com.DariusBcrypt.BcryptLab14.Controls;

import com.DariusBcrypt.BcryptLab14.Repo.YouSirRepo;
import com.DariusBcrypt.BcryptLab14.SiteYouSir;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {
    @Autowired
    YouSirRepo youSirRepo;

    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "/signup.html";
    }

    @PostMapping("/login")
    public RedirectView loginYouSir(String username, String password){
        SiteYouSir yousirFromDb = youSirRepo.findByUserName(username);
        if((yousirFromDb == null) || (!BCrypt.checkpw(password,yousirFromDb.getPassword()))){
           return new RedirectView("/login");
        }
        return new RedirectView("/");
    }

    @PostMapping("/signup")
    public RedirectView signUPYouSir(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        SiteYouSir newYouSir = new SiteYouSir(username, hashedPassword);

       /* youSirRepo.save(newYouSir);*/
        return  new RedirectView("/login");
    }
}
