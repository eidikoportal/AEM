package com.adobe.aem.my.wknd.site.core.models;

public interface DialogValidation {
    String getTitle();

   
    String getTextArea();

    String getLink();
    String getInternal();
    String getInternalLabel();
    String getExternal();
    String getExternalLabel();
    boolean isEmpty();
}
