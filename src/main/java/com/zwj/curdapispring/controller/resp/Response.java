package com.zwj.curdapispring.controller.resp;

import cn.hutool.json.JSONUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {
  public static final Response<Object> SYSTEM_ERROR_RESPONSE = Response.error(-1, "system error");
  private int code = 0;
  private String message = "请求成功";
  private T data;


  public Response(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public Response(T data) {
    this.data = data;
  }

  public Response(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static <T> Response<T> ok(T obj) {
    return new Response<>(obj);
  }

  public static <T> Response<T> ok() {
    return new Response<>();
  }

  public static <T> Response<T> error(int code, String message) {
    return new Response<>(code, message);
  }

  public static <T> Response<T> error(int code, String message, T data) {
    return new Response<>(code, message, data);
  }

  public Response<T> setMsg(String message) {
    this.message = message;
    return this;
  }

  @Override
  public String toString() {
    return JSONUtil.toJsonStr(this);
  }
}
