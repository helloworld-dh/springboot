package com.it.boot;

import com.it.boot.mapper.AdminMapper;
import com.it.boot.pojo.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.ClusterOperations;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class BootApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminMapper adminMapper;

//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", long.class);
        log.info("记录总数 ：",aLong);
        log.info("数据源类型:",dataSource.getClass());
    }

    @Test
    void testAdminMapper(){
        Admin admin = adminMapper.selectById(1L);
        log.info("用户信息",admin);
        System.out.println(admin);
    }

//    @Test
//    void testRedis(){
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        operations.set("hello","world");
//        String hello = operations.get("hello");
//        System.out.println(hello);
//        System.out.println(redisConnectionFactory.getClass());
//    }

}
