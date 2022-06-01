package com.example.meta.model;

import java.io.Serializable;
import java.util.List;

public class WishlistModel implements Serializable {
    boolean success;
    String message;
    List<Wishlist> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Wishlist> getResult() {
        return result;
    }

    public void setResult(List<Wishlist> result) {
        this.result = result;
    }
}
