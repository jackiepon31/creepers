package com.fosun.fc.projects.creepers.getResource;

public class GenerURL implements Runnable{
    public GenerURL(String name){
        this.name = name;
    }
    private String name;
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(name +"<<======线程执行中~");
        }
    }
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 4; i++) {
            GenerURL gener = new GenerURL(String.valueOf(i));
            Thread thread = new Thread(gener);
            thread.start();
        }
        // StringBuffer urlSB = new StringBuffer();
        // //http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&djjg=&maent.pripid=5117813000068896&maent.entbigtype=50&random=
        // StringBuffer urlDetail = new StringBuffer();
        // urlDetail.append("http://gsxt.scaic.gov.cn/ztxy.do?method=qyInfo&djjg=&maent.pripid=").append("信用统一号").append("&maent.entbigtype=50&random=").append(new
        // Date().getTime());
        // System.out.println(urlDetail);
        // //四川
        // urlSB.append("http://gsxt.scaic.gov.cn/ztxy.do?method=list&random=").append(new
        // Date().getTime())
        // .append("&yzmYesOrNo=no&maent.entname=").append("511781600089236").append("\n");
        // //陕西
        // urlSB.append("http://xygs.snaic.gov.cn/ztxy.do?method=list&djjg=&yzmYesOrNo=no&random=").append(new
        // Date().getTime()).append("&pageNum=1&maent.entname=%E5%8C%BB%E8%8D%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8").append("\n");
        // //新疆
        // urlSB.append("http://gsxt.xjaic.gov.cn:7001/ztxy.do?method=list&djjg=&yzmYesOrNo=no&random=").append(new
        // Date().getTime()).append("&pageNum=1&maent.entname=%E5%8C%BB%E8%8D%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8");
        // System.out.println(urlSB.toString());

        // System.out.println(imgUrl.toString());
    }

}
