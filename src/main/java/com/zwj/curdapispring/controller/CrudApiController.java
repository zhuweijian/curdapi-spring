package com.zwj.curdapispring.controller;

import com.zwj.curdapispring.controller.resp.Response;
import com.zwj.curdapispring.crudapi.service.CrudApiHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class CrudApiController {
    @Autowired
    private CrudApiHandler crudApiHandler;

    @RequestMapping(value = "/crud_api/{tableName}")
    public Response<Object> curdApi(@PathVariable("tableName") String tableName,
                                    @RequestBody(required = false) String reqStr,
                                    HttpServletRequest req, HttpServletResponse resp) {
        try {
          return   crudApiHandler.handle(req);
        } catch (Exception e) {
            log.error("exception: ", e);
            return Response.error(-1, e.getMessage());
        }
    }

}
