package com.exercise.crowndb.controller;

import com.exercise.crowndb.model.Cart;
import com.exercise.crowndb.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create a new cart
    @PostMapping
    public ResponseEntity<Cart> createCart() {
        return ResponseEntity.ok(cartService.createCart());
    }

    // Get cart by ID
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add product to cart
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") int quantity) {

        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId, quantity));
    }

    // Remove product from cart
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {

        return ResponseEntity.ok(cartService.removeProductFromCart(cartId, productId));
    }

    // Get cart total
    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartTotal(cartId));
    }
}
