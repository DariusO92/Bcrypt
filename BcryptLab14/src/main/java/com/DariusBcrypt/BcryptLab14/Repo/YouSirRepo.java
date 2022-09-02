package com.DariusBcrypt.BcryptLab14.Repo;

import com.DariusBcrypt.BcryptLab14.SiteYouSir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouSirRepo  extends JpaRepository<YouSirRepo, Long> {
    SiteYouSir findByUserName(String username);
}
