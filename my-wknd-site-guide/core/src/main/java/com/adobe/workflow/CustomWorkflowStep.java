package com.adobe.workflow;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.framework.Constants;

@Component(service = WorkflowProcess.class,immediate=true,
            property = {
                "process.label"+ "=Custom Workflow Step",
                Constants.SERVICE_VENDOR + "=Sample",
                Constants.SERVICE_DESCRIPTION + "=Sample custom workflow step"
            } )
public class CustomWorkflowStep implements WorkflowProcess {

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
       WorkflowData workflowData = workItem.getWorkflowData();
       if(workflowData.getPayloadType().equals("JCR_PATH")){
        Session session = workflowSession.adaptTo(Session.class);
        String path = workflowData.getPayload().toString() + "/jcr:content";
        try {
            Node node = (Node)session.getItem(path);
           String[] processArgs = processArguments.get("PROCESS_ARGS","string").toString().split(",");
           MetaDataMap workFlowMetaDataMap = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
           for(String wfpArgs : processArgs){
            String[] args = wfpArgs.split(":");
            String prop = args[0];
            String val = args[1];
            if(node!=null){
                workFlowMetaDataMap.put(prop, val);
                node.setProperty(prop, val);
            }
           }
           
           Set<String> pair = workFlowMetaDataMap.keySet();
           Iterator<String> it = pair.iterator();
           while(it.hasNext()){
                String key = it.next();
                logger.info("\n Item Key - {},Value - {}",key,workFlowMetaDataMap.get(key));
           }

        } catch (RepositoryException e) {
            logger.error("Repo Exception"+e);
        }
       }
    }
    
    private static final Logger logger = LoggerFactory.getLogger(CustomWorkflowStep.class);
}
