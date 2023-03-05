
package com.adobe.aem.my.wknd.site.core.servlets;

import com.adobe.granite.workflow.model.WorkflowModel;
import com.day.cq.commons.jcr.JcrConstants;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class })
@SlingServletPaths(value = {"/bin/executeworkflow","/my-wknd-site/executeworkflow"})
@ServiceDescription("Run workflow for my wknd site page")
public class RunWorkflow extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    String status = "Workflow Executing..";
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        String payload = req.getRequestParameter("page").getString();
        try{
            WorkflowSession workflowSesion = resourceResolver.adaptTo(WorkflowSession.class);
            WorkflowModel workFlowModel = workflowSesion.getModel("/var/workflow/models/generate-page-version");
            WorkflowData workFlowData = workflowSesion.newWorkflowData("JCR_PATH", payload);
            status = workflowSesion.startWorkflow(workFlowModel, workFlowData).getState();
        }catch(Exception e){
            log.error("Error Executing Workflow"+e.getMessage());
        }
        resp.setContentType("application/json");
        resp.getWriter().write(status);
    }
}
