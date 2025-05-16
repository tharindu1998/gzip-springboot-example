package com.student.gzip;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPResponseWrapper extends HttpServletResponseWrapper {

    private final GZIPOutputStream gzipOutputStream;

    public GZIPResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        this.gzipOutputStream = new GZIPOutputStream(response.getOutputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                gzipOutputStream.write(b);
            }

            @Override
            public void flush() throws IOException {
                gzipOutputStream.flush();
            }

            @Override
            public void close() throws IOException {
                gzipOutputStream.close();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(jakarta.servlet.WriteListener writeListener) {
                // No implementation needed
            }
        };
    }


    @Override
    public void flushBuffer() throws IOException {
        gzipOutputStream.flush();
    }

    public void close() throws IOException {
        gzipOutputStream.close();
    }
}