package com.habialtx3.ecommerce_be.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebResponse <T>{
    private T data;
    private String message;
    private String errors;
}
