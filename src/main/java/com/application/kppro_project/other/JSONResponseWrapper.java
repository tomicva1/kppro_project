package com.application.kppro_project.other;

import lombok.Data;
import org.springframework.core.MethodParameter;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice(basePackages = "com.application.kppro_project.controllers")
public class JSONResponseWrapper implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof List) {
            return new ListWrapper<>((List<Object>) body);
        }
        if(body instanceof EntityModel) {
            return new EntityModelWrapper<>((EntityModel<Object>) body);
        }
        return body;
    }

    @Data // just the lombok annotation which provides getter and setter
    private class ListWrapper<T> {
        private final List<T> data;

        public ListWrapper(List<T> data) {
            this.data = data;
        }


    }

    @Data
    private class EntityModelWrapper<T> {

        private final EntityModel<T> data;

        public EntityModelWrapper(EntityModel<T> data){
            this.data = data;
        }
    }
}
