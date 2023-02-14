package com.adobe.aem.my.wknd.site.core.models.impl;


import javax.annotation.PostConstruct;

import com.adobe.aem.my.wknd.site.core.models.ImageText;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import com.adobe.cq.wcm.core.components.models.Image;
@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {ImageText.class},
        resourceType = {ImageTextImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ImageTextImpl implements ImageText{
    protected static final String RESOURCE_TYPE = "wknd1/components/content/imagetext";
    private Image image;
   // private String imagePosition;
    @Self
    private SlingHttpServletRequest request;
    
    @OSGiService
    private ModelFactory modelFactory;
    
    @ValueMapValue
    private String title;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String imagePosition;

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return title;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isEmpty() {
        final Image componentImage = getImage();
         if (StringUtils.isBlank(title)) {
            // title is missing, but required
            return true;
        } else if (StringUtils.isBlank(text)) {
            // A text is required
            return true;
        }
        else if (StringUtils.isBlank(imagePosition)) {
            // A text is required
            return true;
        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
    private Image getImage() {
        return image;
    }
    @Override
    public String getImagePosition() {
        return imagePosition;
    }

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request,
                                                        request.getResource(),
                                                        Image.class);
    }
}
