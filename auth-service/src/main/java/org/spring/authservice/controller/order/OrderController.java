package org.spring.authservice.controller.order;

import lombok.RequiredArgsConstructor;
import org.spring.authservice.client.order.OrderClient;
import org.spring.authservice.dto.input.restaurants.ProviderOrderDetailsDto;
import org.spring.authservice.service.AuthService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderClient client;
    private final AuthService authService;

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(
            @PathVariable("orderId") UUID orderId
    ){
        client.delete(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<String> create(ProviderOrderDetailsDto providerOrderDetailsDto) {
        return ResponseEntity.ok().body(authService.orderCreation(providerOrderDetailsDto));
    }

    @GetMapping
    public ResponseEntity<String> read(
            @PageableDefault(size = 10, sort = "created") Pageable pageable
    ){
        UUID userId = UUID.randomUUID();
        return ResponseEntity.ok(client.read(userId, pageable));
    }
}
