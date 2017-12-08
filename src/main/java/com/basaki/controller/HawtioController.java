package com.basaki.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Slf4j
@ApiIgnore
public class HawtioController {

    private ResourceLoader loader;

    @Autowired
    public HawtioController(ResourceLoader loader) {
        this.loader = loader;
    }

    @RequestMapping(value = "/hawtio/app/core/html/login.html", method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    public void getHawtioLoginHtml(HttpServletResponse response) {
        String location = "classpath:/templates/login-hawtio.html";
        try {
            String body = getResource(location);
            response.setStatus(HttpStatus.OK.value());
            //response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().write(body);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    /**
     * Looks for a resource at different locations in order of preference.
     *
     * @return input stream of the resource, null if not found
     */
    private String getResource(String location) throws IOException {
        InputStream istream;

        try {
            istream = loader.getResource(location).getInputStream();
        } catch (IOException e) {
            log.debug(
                    "Searching for scope configuration. Not found in " + location,
                    e);
            throw new FileNotFoundException(
                    "Scope configuration file " + location + " not found.");
        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(istream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }

        return builder.toString();
    }
}
