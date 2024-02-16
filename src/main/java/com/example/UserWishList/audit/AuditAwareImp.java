package com.example.UserWishList.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "AuditAwareImp")
public class AuditAwareImp implements AuditorAware<String> {

    /**
     * @return the current auditor
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
