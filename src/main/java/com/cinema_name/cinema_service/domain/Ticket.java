package com.cinema_name.cinema_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record Ticket (@JsonProperty("token") UUID token, @JsonProperty("seat") Seat seat) { }
