# CPS2232_FinalProject
**1. 先运行reset，读取数据。后续如果想要重置一切记录，就再运行一遍，重新输入记录**  
**2. 再运行menu，开始与用户交互**  
**3. 打出menu，询问用户意图**
---
|          |  买船：    |借船   |
|:----------|:-----------:|-------|
|  船的归属 |   使用者和所有者都改为客户    | 使用者改成用户，所有者为公司
|  船只管理        |    移除     |  取消部分管理权限，开放归还权限
|   其他属性        |     不变     |  不变
       

### 借船：
**询问借什么特征的船** 
>- 如果没要求，默认推荐最贵的船
>- 听取用户输入 
>- 筛选出适合的船，打印出各种类型可供选择 
>- 确认借出，支付押金，对应船型的数量减一  
>- 也可取消   

### 还船：
**根据船价和借用时间计算租金**
>- 支付租金，对应船型的数量加一
>- 买船：
>- 询问借什么特征的船
>- 如果没要求，默认推荐最贵的船
>- 听取用户输入
>- 筛选出适合的船，打印出各种类型可供选择
>- 确认购买，支付船价，对应船型的数量减一

----
这个地方放接口吧,把比较重要的方法写上就行了

|财产|   |
|----|---
|  `getPrice（）`  |这个return三维数组，租价，售价和成本价
|`getOwner()` `getUser()`|这个可以得到所有人和使用人，然后如果东西租出去，和卖出去，都需要查询船这俩个实体是否为公司

       /*
        * This interface is used to define the methods that all assets must have.
        * This interface is implemented by the Boat class.
        *
        * An asset is anything that can be bought, sold or borrowed.
        * its price, owner and user can be modified and got.
        *
       */
---

