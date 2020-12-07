# S_Engineering
软件工程课程设计

### 问题
>1、纯电脑端看的人少、会议冲突、不知道哪些会议室空闲；<br>
2、临时紧急开会找不到会议室；<br>
3、会议开太久影响后面人开会；<br>
4、未预定却占用会议室；<br>
***
### 需要解决
>1、知道空闲会议室的编号和空闲时段；<br>
2、灵活利用会议室资源；<br>
3、灵活管理会议室；<br>
***
### 需求解决
>1、时间片定为**15min**；<br>
2、具体实现<br>
>>1、管理端<br>
>>>1、管理会议室：设置空闲与否、是否可用；<br>
2、人员管理：增删改查；<br>
3、在与会人员未进行延时情况下会议超时，赶人；<br>

>>2、预定端<br>
>>>1、适配移动端、Web端；<br>
2、预约界面显示当前所有会议室，可选择显示其被预约情况；<br>
3、预约的时候可以进行筛选，可以模糊筛选，即系统优先显示符合条件的最佳会议室，之后再时间优先显示；<br>
4、预约会议室，输入一些信息后可以实时显示可用会议室（比如输入时间，显示当前时间可用会议室，再输入人数，显示符合时间与人数的会议室），可以取消预约；<br>
5、一键预约会议室，紧急会议，提供一键预约，需要输入人数，系统自动匹配临近时间的最佳会议室（需要吗？）；<br>
6、例会预约；<br>
7、预约会议时若是无空闲会议室，选择接受系统通知，输入时间、人数，若是此时有会议取消，系统安排会议并且通知；<br>
8、会议快结束了（15min）系统发送消息提醒主持者，主持者选择延后或者更换会议室，若是此会议室之后无预约，延后成功，否则延后失败；选择更换会议室，系统根据人数自动匹配会议室；<br>
9、管理员赶人功能；<br>

>>3、会议室前端<br>
>>>1、显示当日会议室预定信息以及当前时段使用情况；<br>
2、可以使用会议室前端预约；<br>
3、人脸识别、开门，识别及签到；<br>

>>4、其他需求<br>
>>>1、会议室大小划分；<br>
2、某会议室可用区间小于15min不可被预约；<br>
## 开发工具与接口
>[虹软人工智能开放平台](https://ai.arcsoft.com.cn/index.html)<br>
[虹软人脸识别 SDK](https://ai.arcsoft.com.cn/product/arcface.html)