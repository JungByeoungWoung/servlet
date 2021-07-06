package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_MOVED_TEMPORARILY;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pregma", "no-cache");
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        //redirect(response);

        //[message body]
        PrintWriter printWriter = response.getWriter();
        printWriter.write("ok");

    }
    private void redirect(HttpServletResponse response) throws IOException {
        //status code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location","/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");

    }
}
