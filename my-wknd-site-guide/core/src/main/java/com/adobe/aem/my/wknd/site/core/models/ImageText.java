package com.adobe.aem.my.wknd.site.core.models;



/**
* Represents the Byline AEM Component for the WKND Site project.
**/
public interface ImageText {
    /***
    * @return a string to display as the title.
    */
    String getTitle();

    /***
    * 
    *
    * @return a string to display as the text.
    */
    String getText();

    String getImagePosition();
    /***
    * @return a boolean if the component has enough content to display.
    */
    boolean isEmpty();
}
