package com.adobe.aem.my.wknd.site.core.servlets;



import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.crx.JcrConstants;

import org.apache.http.protocol.HTTP;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Shiv
 */
@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = { "/apps/jsonDropDown" },
    methods = HttpConstants.METHOD_GET)
public class JsonDataDropdownServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDataDropdownServlet.class);

    transient ResourceResolver resourceResolver;
    transient Resource pathResource;
    transient ValueMap valueMap;
    transient List<Resource> resourceList;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        resourceResolver = request.getResourceResolver();
        pathResource = request.getResource();
        resourceList = new ArrayList<>();

        try {
            /* Getting AEM jsonDataPath given on datasource Node */
            String jsonDataPath = Objects.requireNonNull(pathResource.getChild("datasource"))
                                .getValueMap().get("jsonDataPath", String.class);
            assert jsonDataPath != null;
            //Getting Tag Resource using JsonData
            Resource jsonResource = request.getResourceResolver()
                                    .getResource(jsonDataPath + "/" + JcrConstants.JCR_CONTENT);
            assert jsonResource != null;
            //Getting Node from jsonResource
            Node jsonNode = jsonResource.adaptTo(Node.class);
            assert jsonNode != null;
            //Converting input stream to JSON Object
            InputStream inputStream = jsonNode.getProperty(JcrConstants.JCR_DATA)
                                        .getBinary().getStream();

            StringBuilder stringBuilder = new StringBuilder();
            String eachLine;
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((eachLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(eachLine);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            @SuppressWarnings("unchecked")
            Iterator<String> jsonKeys = jsonObject.keys();
            LOGGER.info("Json Data loaded successfully"+jsonObject.toString());
            //Iterating JSON Objects over key
            while (jsonKeys.hasNext()) {
                String jsonKey = jsonKeys.next();
                String jsonValue = jsonObject.getString(jsonKey);

                valueMap = new ValueMapDecorator(new HashMap<>());
                valueMap.put("value", jsonKey);
                valueMap.put("text", jsonValue);
                resourceList.add(new ValueMapResource(resourceResolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, valueMap));
            }

            /*Create a DataSource that is used to populate the drop-down control*/
            DataSource dataSource = new SimpleDataSource(resourceList.iterator());
            request.setAttribute(DataSource.class.getName(), dataSource);
            LOGGER.info("Json Data loaded successfully");

        } catch (JSONException | IOException | RepositoryException e) {
            LOGGER.error("Error in Json Data Exporting : {}", e.getMessage());
        }
    }
}
