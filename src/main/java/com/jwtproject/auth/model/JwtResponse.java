package com.jwtproject.auth.model;

import java.io.Serializable;

public record JwtResponse(String jwttoken) implements Serializable {
}
