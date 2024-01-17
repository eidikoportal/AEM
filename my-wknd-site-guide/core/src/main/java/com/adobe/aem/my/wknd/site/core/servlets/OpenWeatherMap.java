package com.adobe.aem.my.wknd.site.core.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.servlets.HttpConstants;

import com.adobe.aem.my.wknd.site.core.config.WeatherOsgiConfig;
import com.adobe.aem.my.wknd.site.core.config.WnkdOsgiFactoryConfig;
import com.adobe.aem.my.wknd.site.core.services.WeatherOsgi;
import com.day.cq.commons.jcr.JcrConstants;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/bin/OpenWeatherMap",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.extensions=json"
})

public class OpenWeatherMap extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;
    private static Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMap.class);
     CloseableHttpClient httpPostClient = HttpClients.createDefault();

    @Reference
    private transient WeatherOsgi weatherOsgi;
    
    //LOGGER.info("locationId="+locationId+"ApiKey="+apiKey);
    public static String OAUTH_SERVER_URL = "https://dm-us.informaticacloud.com/authz-service/oauth/token";
    
	public static final String POST_URL = "https://localhost:9090/SpringMVCExample/home";

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws IOException {
               // this.doPost(request, response);
                LOGGER.info("OpenWeatherMap=");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            BufferedReader reader =null;
             long locationId = Objects.isNull(weatherOsgi.getLocationId()) ? 0L:weatherOsgi.getLocationId();
            String apiKey = StringUtils.isNotBlank(weatherOsgi.getApiKey())?weatherOsgi.getApiKey():"";
            final String GET_URL = StringUtils.join("https://api.openweathermap.org/data/2.5/forecast?id=",locationId,"&appid=",apiKey);
            LOGGER.info("locationID=="+weatherOsgi.getLocationId()+"apiKey=="+weatherOsgi.getApiKey());
        try {
            // final Resource resource = request.getResource();
           
           
            LOGGER.info("GET_URL="+GET_URL);
            HttpGet httpGet = new HttpGet(GET_URL);
            //httpGet.addHeader("User-Agent", USER_AGENT);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            
            LOGGER.info("GET Response Status:: "
                    + httpResponse.getStatusLine().getStatusCode());

            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            
            // print result
            LOGGER.info(stringBuffer.toString());
             
            response.setContentType("application/json");
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            
            LOGGER.info("json Data" + jsonObject.toString());
            response.getWriter().write(jsonObject.toString());
             
        } catch (Exception e) {
             LOGGER.error(e.getMessage());
        }
        finally{
                if(reader!=null)
                    reader.close();
                httpClient.close();
        }
    }
    @Override
    protected void doPost( SlingHttpServletRequest request,  SlingHttpServletResponse response) {
        CloseableHttpClient httpClient = null;
        try {
            String cc= request.getParameter("CLIENT_CREDENTIALS");
            String token = null;
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(OAUTH_SERVER_URL);
            String header = "Basic "+cc;
            httpPost.addHeader("Authorization", header);
            List<NameValuePair> formparams = new ArrayList<>();
            formparams.add(new BasicNameValuePair("grant_type","client_credentials"));
            httpPost.setEntity(new UrlEncodedFormEntity(formparams));
            RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            token=EntityUtils.toString(entity);
            response.getWriter().write(token);
            httpClient.close();
            }catch (IOException e) {
                throw new RuntimeException(e);
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
    private static void sendGET() throws IOException {
        
        
	}
    private static void sendPOST() throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(POST_URL);
		//httpPost.addHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("userName", "Pankaj Kumar"));

		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPost.setEntity(postParams);

		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		System.out.println("POST Response Status:: "
				+ httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();

		// print result
		System.out.println(response.toString());
		httpClient.close();

	}
}
