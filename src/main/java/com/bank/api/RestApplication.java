package com.bank.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")   // base URL for all resources
public class RestApplication extends Application {
    // No code needed, just activates JAX-RS
}
