package app.api.interop;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.decoder.BrotliInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String, Object> doWork(String arg) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", arg);
        return map;
    }

    public static String fromBrotli(byte[] bytes) throws Exception {
        Brotli4jLoader.ensureAvailability();
        ByteArrayInputStream compressedStream = new ByteArrayInputStream(bytes);
        BrotliInputStream decodingStream = new BrotliInputStream(compressedStream);
        ByteArrayOutputStream decompressedStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = decodingStream.read(buffer)) != -1) {
            decompressedStream.write(buffer, 0, bytesRead);
        }
        decodingStream.close();
        return decompressedStream.toString(StandardCharsets.UTF_8);
    }

}
