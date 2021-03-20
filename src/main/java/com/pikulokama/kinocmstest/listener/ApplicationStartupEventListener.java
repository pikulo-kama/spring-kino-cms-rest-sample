package com.pikulokama.kinocmstest.listener;

import com.pikulokama.kinocmstest.service.AdminUserInitializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final AdminUserInitializer adminUserInitializer;

    public ApplicationStartupEventListener(AdminUserInitializer adminUserInitializer) {
        this.adminUserInitializer = adminUserInitializer;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        adminUserInitializer.createAdminIfNotExists();
    }

}
