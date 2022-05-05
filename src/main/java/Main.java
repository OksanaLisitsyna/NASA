import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(RequestConfig.DEFAULT.custom()
                            .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                            .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                            .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                            .build())
                    .build();

            //делаем запрос к api с помощью нашего ключа
            HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=B7rTZfqeILFNimFzBbCnkJ7O3fBOXcoPiu7rPKQn");
            CloseableHttpResponse response = httpClient.execute(request);

            //преобразуем json ответ в объект класса ResponseFromNASA
            ResponseFromNASA dailyResponseFromNasa = mapper.readValue(response.getEntity().getContent(),
                    new TypeReference<ResponseFromNASA>() {
                    });
            System.out.println(dailyResponseFromNasa);

            // в java-объекте найдем url и по нему скачаем файл
            String url = dailyResponseFromNasa.getUrl();
            String fileName = getFileNameFromUrl(url);
            String path = "C:\\Users\\lisit\\IdeaProjects\\images\\";

            request = new HttpGet(url);
            response = httpClient.execute(request);

            File file = new File(path + fileName);

            OutputStream writer = new FileOutputStream(file);
            byte bytes[] = response.getEntity().getContent().readAllBytes();
            writer.write(bytes);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileNameFromUrl(String sourceString) {
        int index = sourceString.lastIndexOf("/") + 1;
        return sourceString.substring(index);
    }
}
