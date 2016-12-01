/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.utils.html2PDF;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

/**
 * <p>
 * description:xhtml to  w3c.document
 * </p>
 * 
 * @author MaXin
 * @since 2016年10月14日
 * @see
 */
public class Html2Xhtml {

    public static void main(String[] args){
        try{
           FileInputStream FIS=new FileInputStream("d:/pdf/4.html");
           FileOutputStream FOS=new FileOutputStream("d:/pdf/4.xml");   
           Tidy T=new Tidy();
           Document D=T.parseDOM(FIS,FOS);
           System.out.println(D.toString());
           }
        catch (java.io.FileNotFoundException e)
           {System.out.println(e.getMessage());}   
        }
}
