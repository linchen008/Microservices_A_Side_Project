package com.udemy.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 05/06/2024 14:23
 * @Description :
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {


    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
}
