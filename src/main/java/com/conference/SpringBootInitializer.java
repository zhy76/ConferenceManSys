package com.conference;

/**
 * @ClassName: ServletInitializer
 * @Description: That's enough.
 * @Author: Lance
 * @Date: 2020/12/25 10:45
 */
import com.conference.entity.Conference;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SpringBootInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //Application的类名
        return application.sources(ConferenceApplication.class);
    }

}
