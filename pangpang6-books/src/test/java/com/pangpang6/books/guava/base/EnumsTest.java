package com.pangpang6.books.guava.base;

import com.google.common.base.Converter;
import com.google.common.base.Enums;
import com.google.common.base.Optional;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by jiangjiguang on 2017/12/11.
 */
public class EnumsTest {

    @Test
    public void testGetField() throws NoSuchFieldException {
        Field field = Enums.getField(Gender.MALE);
        System.out.println(field);
        System.out.println(field.getName());
        System.out.println(field.getType());
        // result: public static final com.weimob.carl.user.service.Gender com.weimob.carl.user.service.Gender.MALE
    }

    @Test
    public void testGetIfPresent() {
        Optional<Gender> male = Enums.getIfPresent(Gender.class, Gender.MALE.name());
        System.out.println(male.get());
        Optional<Gender> male1 = Optional.of(Enum.valueOf(Gender.class, Gender.MALE.name()));
        System.out.println(male1.get());

    }

    @Test
    public void testStringConverter() {
        Converter<String, Gender> stringGenderConverter = Enums.stringConverter(Gender.class);
        Gender male = stringGenderConverter.convert(Gender.MALE.name());
        System.out.println(male);
        // result: MALE
    }

    public enum Gender {

        MALE(1, "male"),          // 男性
        FEMALE(2, "female");      // 女性

        private Integer key;
        private String desc;

        Gender(Integer key, String desc) {
            this.key = key;
            this.desc = desc;
        }
    }
}
