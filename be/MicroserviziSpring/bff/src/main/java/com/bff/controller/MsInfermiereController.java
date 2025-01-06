package com.bff.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms-pazienti")
@Validated
@Tag(name = "Ms pazienti Controller",
        description = "Ms pazienti")
@AllArgsConstructor
public class MsInfermiereController {

    //TODO implementare
}
