package com.exercise.crowndb.service;

import com.exercise.crowndb.model.Cart;
import com.exercise.crowndb.model.CartItem;
import com.exercise.crowndb.model.Product;
import com.exercise.crowndb.repository.CartRepository;
import com.exercise.crowndb.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCart(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.addProduct(product, quantity);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.removeProduct(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));

        return cartRepository.save(cart);
    }

    public Double getCartTotal(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getTotalPrice();
    }
}
