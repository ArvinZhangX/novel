项目初衷：

国庆穷逼一个宅在家里没事看小说，可是wap端的小说站点简直太没节操了，那些广告真是实在是不堪忍受。所以想做一个没有任何广告的自己的小说站点（果然，做出的站点也够简单的！）。

然后就付诸实践了！

![站点一截图](https://github.com/ArvinZhangX/novel/blob/master/readmeImage/aa.png)

https://github.com/ArvinZhangX/novel/blob/master/readmeImage/aa.png


![站点2截图](https://github.com/ArvinZhangX/novel/blob/master/readmeImage/2.png)

![站点3截图](https://github.com/ArvinZhangX/novel/blob/master/readmeImage/3.png)

![站点4截图](https://github.com/ArvinZhangX/novel/blob/master/readmeImage/4.png)


![Image text](https://github.com/ArvinZhangX/novel/blob/master/readmeImage/4.png)

闲话少说，介绍一下如何运行这（几）个项目：
第一个项目：novel.spider（姑且叫他为spider吧）-jsoup,http-client
提供了三个最为底层的方法：
ChapterFactory.getChapterSpider(NovelSiteEnum novelSiteEnum).getChapters(NovelSpiderUtil.getRelativeUrl(NovelSiteEnum novelSiteEnum, url))  获取一个爬取对应网站的章节的实体，并执行爬取任务
ContentFactory.getContentSpider(NovelSiteEnum novelSiteEnum).getContent(NovelSpiderUtil.getRelativeUrl(NovelSiteEnum novelSiteEnum,url)) 获取一个爬取对应网站的内容的实体，并执行爬取任务
NovelDownloaderObserver observer = new NovelDownloaderObserver(url) 下载小说...

这三个都依赖于一个方法：NovelSpiderUtil.setRootPath(path) 该方法用来指定爬取规则的配置文件存放的路径，以及下载的小说存放在哪个文件夹下
该项目下有个测试类，可以参考一下使用方式：NovelSpiderTest.java

第二个项目：novel.book.storage（用来爬取某个网站的所有书列表）-mybatis
唯一的难点就是指定好配置文件，以及配置好数据库的连接信息。config目录下还有一个sql文件，该文件用来创建数据库和表，没有库爬到的数据没地方存呀。
这个项目很简单，总共2个类+1个接口，就不细说了！


第三个项目：novel （这是一个web站点）-spring,mybatis
稍微重要点的类是NovelController和NovelServiceImpl（写这个实现类的是才发现之前给自己挖的坑有多么的深，所以在spider项目里面多了一个抽象...）

稍微要注意的是，NovelServiceImpl中也调用了NovelSpiderUtil.setRootPath(path)方法来指定配置文件的位置。

JAVA小说站点爬虫-spring-mybatis-jsoup-http-client

数据库截图


JAVA小说站点爬虫-spring-mybatis-jsoup-http-client

这个是三个项目组合起来的，我整合在一起了，有问题可以问我。谢谢

喜欢的话，欢迎打赏！谢谢。

![支持作者](https://github.com/ArvinZhangX/file_tem/blob/master/chat_pay.png)

![打赏](https://github.com/ArvinZhangX/file_tem/blob/master/chat_pay.png)
