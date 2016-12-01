package com.fosun.fc.projects.creepers.utils;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;

/**
 * add annotation with javassist
 * Created by outofmemory.cn on 2015/12/14.
 */
public class Classmaker {
    public static void main(String[] args) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        // create the class
        CtClass cc = pool.makeClass("foo");
        ClassFile ccFile = cc.getClassFile();
        ConstPool constpool = ccFile.getConstPool();

        // create the annotation
        AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        Annotation annot = new Annotation("MyAnnotation", constpool);
        annot.addMemberValue("value", new IntegerMemberValue(ccFile.getConstPool(), 0));
        attr.addAnnotation(annot);

        // create the method
        CtMethod mthd = CtNewMethod.make("public Integer getInteger() { return null; }", cc);
        cc.addMethod(mthd);
        mthd.getMethodInfo().addAttribute(attr);

        cc.writeFile("d://");
        // generate the class
        Class<?> clazz = cc.toClass();

        // length is zero
        @SuppressWarnings("unused")
        java.lang.annotation.Annotation[] annots = clazz.getAnnotations();
    }
}