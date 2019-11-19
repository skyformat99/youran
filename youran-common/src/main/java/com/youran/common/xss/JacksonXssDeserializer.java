package com.youran.common.xss;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

/**
 * jackson防XSS反序列化器
 * 请使用该反序列化器替代默认的字符串反序列化器
 *
 * @author: cbb
 * @date: 2018/4/10
 */
public class JacksonXssDeserializer extends StdScalarDeserializer<String> implements ContextualDeserializer {

    public JacksonXssDeserializer() {
        super(String.class);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        IgnoreXSS annotation = property.getAnnotation(IgnoreXSS.class);
        if (annotation != null) {
            return StringDeserializer.instance;
        }
        return this;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = StringDeserializer.instance.deserialize(p, ctxt);
        return XSSUtil.clean(value);
    }

    @Override
    public String deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        String value = StringDeserializer.instance.deserializeWithType(jp, ctxt, typeDeserializer);
        return XSSUtil.clean(value);
    }

    @Override
    public boolean isCachable() {
        return StringDeserializer.instance.isCachable();
    }

}
