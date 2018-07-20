package com.dyhdyh.helper.recyclerview.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dengyuhan
 * @created 2017/12/4 18:13
 */
public class ExampleData {
    private String title;
    private String nickname;
    private String avatar;
    private String image;
    private String fileUrl;
    private int displayHeight;

    private static final String[] TITLE_DATA = new String[]{
            "很荣幸获得年度影响力人物的荣誉！很开心的夜晚，感谢腾讯视频 #星光大赏#让我们新老朋友相聚！",
            "晨空,上方,荷兰人,农场,风车,山羊晨空,上方,荷兰人,农场,风车,山羊晨空,上方,荷兰人,农场,风车,山羊晨空,上方,荷兰人,农场,风车,山羊",
            "9个世界上美得不真实的风景，你最想去哪一个？",
            "2017年很快就会过去，而我却恋恋不舍，因为我还有好多事没有去做，许多风景都还没有去看。抓住岁月的尾巴，活出精彩的自己。一切都是浮云。 ",
    };
    private static final String[] TITLE_DATA1 = new String[]{
            "硬汉史泰龙上演最牛逼的越狱过程，看完我只想说我见识短浅！#电影推荐##电影精选片段#",
            "看了#电视剧远大前程#的最新片花，觉得很有看点，片花的运镜和武术动作都堪称电影级，从片花来看几位主演从颜值到演技都亮点满满，“老戏骨”们的演技都足够自信人了，不得不说陈思诚还是很有才华的，你们期待这部剧吗？",
            "【《侏罗纪世界2》中文先导预告片公开】《侏罗纪世界2 陨落国度》中文先导预告片公开，该片将于2018年6月22日在北美上映。[憧憬]",
            "#电影推荐# #恐怖笔记# 4分钟看完恐怖片 恐怖笔记，男人外面偷腥，女友做鬼来索命",
            "新剧#柒个我#将于12月13日首播，期待#张一山#[心]在那之前，准备再刷#杀了我治愈我#",
            "#毛不易#《请记住我》(《寻梦环游记》电影中文主题曲）！ ",
            "#橘子录像厅# 柯南最新剧场版#名侦探柯南：零的执行人# 发布先导预告！和安室透的再次决斗！东京八国峰会现场爆炸，毛利小五郎被捕，柯南和安室透分秒必争！电影将于明年4月13日日本上映，内地求引进啊，晚半年也没关系...[跪了]",
            "英国电影学院奖最佳动画短片《堕落的艺术》，某个军事基地上，大胡子军官将一枚荣誉勋章授予某个士兵后，将其从高处推落，士兵落地瞬间，一名瘦子医生会及时按下快门，然后迅速将其送给大胖子将军，供他用作“艺术创作”的素材。一旦将军不满意，上述过程就要重来。画风扭曲但寓意深刻……"
    };

    private static final String[] IMAGE_DATA = new String[]{
            "http://mpic.tiankong.com/5d3/0e9/5d30e9334a1f75e9bcc6fb1819eb3bb2/640.jpg",
            "http://mpic.tiankong.com/08c/06d/08c06d4f67314d6a70d3af508189499d/640.jpg",
            "http://mpic.tiankong.com/0e3/f59/0e3f59d37b6d22c6aa17ac88cf2227b9/640.jpg",
            "http://mpic.tiankong.com/23e/ea7/23eea7184ef0d4292f7311a85dd643b8/640.jpg"
    };

    public static List<ExampleData> randomData(int count, String videoPath) {
        List<ExampleData> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(TITLE_DATA.length);
            ExampleData model = new ExampleData();
            model.setTitle(TITLE_DATA1[Math.min(i, TITLE_DATA1.length - 1)]);
            model.setNickname("我是昵称 " + i);
            model.setAvatar(IMAGE_DATA[random.nextInt(TITLE_DATA.length)]);
            model.setImage(IMAGE_DATA[index]);
            model.setFileUrl(videoPath);
            int height = random.nextInt(400) + 400;
            //int height = 500;
            model.setDisplayHeight(height);
            data.add(model);
        }
        return data;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
