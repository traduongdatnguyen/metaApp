package com.example.meta.model;

import java.util.List;

public class DienThoaiMoiModel {
    boolean success;
    String message;
    List<SanPhamMoi> result;
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

    public List<SanPhamMoi> getResult() {
        return result;
    }

    public void setResult() {
        this.result = result;
    }
}
