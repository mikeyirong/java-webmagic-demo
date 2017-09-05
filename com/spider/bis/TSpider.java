package com.spider.bis;

import com.spider.Utils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class TSpider implements PageProcessor{
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private static Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");

    static {
        Map<String ,String > map = Utils.getCookies();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String ,String > entry = (Map.Entry<String, String>) it.next();
            site.addCookie(entry.getKey(), entry.getValue());
        }
    }
    private static int count = 0;
    private static String[] keyWord = {"男装","男鞋","女装","女鞋"};

    @Override
    public void process(Page page) {
        /*//判断链接是否符合http://www.cnblogs.com/任意个数字字母-/p/7个数字.html格式
        if(!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()){
            //加入满足条件的链接
            page.addTargetRequests(
                    page.getHtml().xpath("//*[@id=\"post_list\"]/div/div[@class='post_item_body']/h3/a/@href").all());
        }else{
            //获取页面需要的内容
            System.out.println("抓取的内容："+
                    page.getHtml().xpath("//*[@id=\"Header1_HeaderTitle\"]/text()").get()
            );
            count ++;
        }*/
        //System.out.println(page.getHtml().css("div.product").all());
        HashMap<String,String> J_CollectCount = new HashMap<String,String>();
        if (page.getUrl().regex("https://list.tmall.com/search_product.htm").match()){
            System.out.println("列表");
            List<String> product = page.getHtml().css("div.product").all();
            //商品详情连接
            List<String> productLinks = page.getHtml().css("div.productImg-wrap").links().all();
            //价格
            List<String> productPrice = page.getHtml().css("p.productPrice").css("em","title").all();
            //标题
            List<String> productTitle = page.getHtml().css("p.productTitle").css("a","title").all();
            //店铺名称
            List<String> productShop = page.getHtml().css("div.productShop").css("a","text").all();
            //月成交
            List<String> productTrans = page.getHtml().css("p.productStatus").css("span").css("em","text").all();
            //评价数
            List<String> productAdjust = page.getHtml().css("p.productStatus").css("span").css("a","text").all();
            //大图地址
            List<String> imgSoruce = page.getHtml().css("div.productImg-wrap").css("a").css("img","src").all();
            //对大图地址处理，添加https:
            for(int i=0;i<imgSoruce.size();i++){
                String str = "https:"+imgSoruce.get(i);
                imgSoruce.set(i, str);
            }
            for(int i=0;i<productLinks.size();i++){
                J_CollectCount.put(productLinks.get(i),"");
            }
            page.addTargetRequests(productLinks);
        }else {//商品详情页面
            System.out.println("详情");
            //收藏人数， 动态加载，暂时出不来
            J_CollectCount.put(page.getUrl().toString(),page.getHtml().css("span#J_CollectCount").toString());
            Iterator it = J_CollectCount.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println(entry.getValue());
            }
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取....");
        startTime = System.currentTimeMillis();
        for(int i=0;i<1 ;i++){
            try {
                Spider.create(new TSpider())
                        .addUrl("https://list.tmall.com/search_product.htm?q="+URLEncoder.encode(keyWord[0],"gbk"))
                        .thread(5).run();
            } catch (UnsupportedEncodingException e) {
                System.out.println("编码格式设置错误");
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime)/1000) + "秒， 抓取了"+count+"条记录");
    }
}
