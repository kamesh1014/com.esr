package com.test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	 @Scheduled(fixedRate = 1000) 
	public void fixedRateSch() throws ClientProtocolException, IOException {

	//	HttpGet request = new HttpGet("http://localhost:8080/track");
		 HttpPost post = new HttpPost("https://httpbin.org/post");
        System.out.println("execute");
        CloseableHttpResponse response = httpClient.execute(post);
		
	

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            

        }
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		/*Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Fixed Rate scheduler:: " + strDate);*/
	}
}
