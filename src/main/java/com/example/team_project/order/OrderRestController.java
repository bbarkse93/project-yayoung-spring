package com.example.team_project.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RequiredArgsConstructor
@RestController
public class OrderRestController {

    private final OrderService orderService;

}
