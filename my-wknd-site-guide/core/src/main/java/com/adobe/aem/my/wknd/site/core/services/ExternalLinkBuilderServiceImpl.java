package com.adobe.aem.my.wknd.site.core.services;

import java.util.Optional;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.feed.Feed;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ExternalLinkBuilderService.class, immediate = true)
public class ExternalLinkBuilderServiceImpl implements ExternalLinkBuilderService{
   Logger log = LoggerFactory.getLogger(this.getClass());
    @Self
    private SlingHttpServletRequest request;
    @Reference
    private ResourceResolverFactory resolverFactory;
    @OSGiService
    private SlingSettingsService slingSettingsService;
    @Reference
    private Externalizer externalizer;

    private String getLinkWithHtmlSuffix(final String link) {
        return link.endsWith(Feed.SUFFIX_HTML) ? link : StringUtils.join(link, Feed.SUFFIX_HTML);
    }

    private boolean isPublishServer(final SlingSettingsService slingSettingsService) {
        log.info("slingSettingsService==");
        log.info("=="+slingSettingsService);
        return Optional.ofNullable(slingSettingsService).map(settings -> settings.getRunModes().contains(Externalizer.PUBLISH)).orElse(false);
    }

    @Override
    public String getExternalLink(final ResourceResolver resourceResolver, final String path) {
        log.info("getExternalLink==");
        final String link = this.isPublishServer(this.slingSettingsService) ?
                              this.externalizer.publishLink(resourceResolver, path) :
                              this.externalizer.authorLink(resourceResolver, path);
        return this.getLinkWithHtmlSuffix(link);
    }
    
}
