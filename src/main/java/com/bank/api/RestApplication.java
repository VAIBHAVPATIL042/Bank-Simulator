package com.bank.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")   // Base URL for all APIs
public class RestApplication extends Application {
    // No additional code needed
}
