package com.pangpang6.books.guava.base;

import com.google.common.base.Converter;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * Created by jiangjiguang on 2017/12/11.
 */
public class ConverterTest {
    @Test
    public void andThenTest() {
        User user = new User("malone", "man");


        //把两个Converter连接成一个转换链条
        Converter<User, String> converC = convertA.andThen(convertB);

        String userStr = converC.convert(user);
        System.out.println(userStr);
        //使用Converse方法实现反转换
        User newUser = converC.reverse().convert(userStr);
        System.out.println(newUser.getUsername() + "|" + newUser.getSex());
    }


    @Test
    public void convertTest(){
        Converter<Integer, String> c1 = Converter.from(x -> (x + 1) + "", x -> Integer.parseInt(x) - 1);
        Converter<String, Integer> c2 = c1.reverse();
        String result = c1.convert(5);
        System.out.println(result);
    }





    //自定义一个Converter
    Converter<UserDto, String> convertB = new Converter<UserDto, String>() {
        @Override
        protected String doForward(UserDto userDto) {
            if (Objects.equals(userDto, null)) {
                return "null";
            }
            return userDto.getName() + "|" + userDto.getUserSex();
        }

        @Override
        protected UserDto doBackward(String s) {
            if (Objects.equals(s, null)) {
                return new UserDto("", "");
            }
            List<String> list = Splitter.on("|").splitToList(s);
            return new UserDto(list.get(0), list.get(1));
        }
    };


    //自定义一个Converter
    Converter<User, UserDto> convertA = new Converter<User, UserDto>() {
        @Override
        protected UserDto doForward(User user) {
            UserDto dto = new UserDto();
            if (user == null) {
                return dto;
            }
            dto.setName(user.getUsername());
            dto.setUserSex(user.getSex());
            return dto;
        }

        @Override
        protected User doBackward(UserDto userDto) {
            User user = new User();
            if (userDto == null) {
                return user;
            }
            user.setSex(userDto.getUserSex());
            user.setUsername(userDto.getName());
            return user;
        }
    };


    class User {
        private String username;

        private String sex;

        public User() {

        }

        public User(String username, String sex) {
            this.username = username;
            this.sex = sex;
        }

        void setUsername(String username) {
            this.username = username;
        }

        void setSex(String sex) {
            this.sex = sex;
        }

        String getUsername() {

            return username;
        }

        String getSex() {
            return sex;
        }
    }

    class UserDto {
        private String name;
        private String userSex;

        public UserDto() {
        }

        public UserDto(String name, String userSex) {
            this.name = name;
            this.userSex = userSex;
        }

        void setName(String name) {
            this.name = name;
        }

        void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        String getName() {

            return name;
        }

        String getUserSex() {
            return userSex;
        }
    }
}
