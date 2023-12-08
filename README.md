
<h1 align="center"> CPS 2232 FinalProject: Boat Manager</h1>  
<p align="left"> <a href="https://www.wku.edu.cn/en/" target="_blank" rel="noreferrer"> <img src="https://wku.edu.cn/wp-content/uploads/2016/02/logo_en.png" alt="WKU" width="300" height="78"/> </a> 

Welcome to our project !  This is a `final project` for `CPS 2232`, `Abstract Data Type`, of [`Wenzhou-Kean University`][wku].       
       
**The authors:**   
[***Zhang Lei***][ZL]   
[***Jiao Luyao***][JY]   
[***Zhao Qinjian***][ZJ]

This project is intended to help Sailboat Company manage their special assets, boats. Boats have many features, such as their maker, variant, length, region, selling price, and year of manufacture; therefore, we created a boat class to describe the features of the boats. We use a file reader to read all the data from a CSV file and create boat objects. Then, we use a ??? data type to store all the boats.   
Staff can use the Restart.java to clear all the data and read the data from a CSV file.   

Once read the data, the menu is ready for the client to use. Clients can access this software from Menu.java and choose to rent, return, or buy a boat. Based on clients' requirements, this software will recommend the fittest boat accordingly. The history of the clients' operation will be recorded in ??? file for the staff to check.   
For the renters, the software will recommend boats according to their requirements, ask them to pay the deposit, and record their renting and returning time. After return, the software will calculate the rental based on the boats' depreciation fee, which is based on the selling price, and renting time.

For the buyers, after recommendations, the software will record the change of the number of the boats.  

有一个问题就是我们的main 需要模拟的是公司客户端还是用户端  
 

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
        * its price, owner and user can be modified and got
        *
       */

```java
import java.util.HashMap;
import java.util.Map;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 正确实现 hashCode() 和 equals() 方法
    @Override
    public int hashCode() {
        // 可以根据对象的内容生成哈希码
        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        // 比较对象的内容是否相等
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }
}

public class Example {
    public static void main(String[] args) {
        // 创建一个HashMap，使用Person对象作为键
        Map<Person, String> personMap = new HashMap<>();

        // 添加键值对
        Person person1 = new Person("John", 25);
        personMap.put(person1, "Value1");

        Person person2 = new Person("Jane", 30);
        personMap.put(person2, "Value2");

        // 使用Person对象查找值
        String value = personMap.get(new Person("John", 25));
        System.out.println("Value for John: " + value);  // 输出 "Value1"
    }
}
```
# Database 类介绍

`Database` 类是一个用于管理船只信息的 Java 类。该类包含各种方法，用于检索、过滤和显示船只的信息。以下是该类的主要方法介绍：

## 成员变量

- `HashMap<String, ArrayList<Boat>> byAttributeBoats`: 通过不同属性组织的船只信息的哈希映射。
- `HashMap<Boat, Boat> allBoats`: 包含所有船只的哈希映射。
- `TreeMap<Integer, ArrayList<Boat>> rPriceBoats`: 按租金价格排序的船只信息的有序映射。
- `TreeMap<Integer, ArrayList<Boat>> sPriceBoats`: 按销售价格排序的船只信息的有序映射。
- `TreeMap<Integer, ArrayList<Boat>> lengthBoats`: 按长度排序的船只信息的有序映射。
- `TreeMap<Integer, ArrayList<Boat>> yearBoats`: 按年份排序的船只信息的有序映射。

## 主要方法

### `showAllMakes()`

显示所有制造商及其船只数量的方法。

### `showAllrPrice()`

显示按租金价格分组的船只数量的方法。

### `showAllsPrice()`

显示按销售价格分组的船只数量的方法。

### `getAveragePrice(String make)`

获取指定制造商的平均销售价格的方法。

### `show()`

用户交互方法，根据用户选择展示不同的船只信息。

### `showAllBoats()`

显示所有船只信息的方法。

### `showBoatsByMake(String make)`

按制造商过滤并显示船只信息的方法。

### `showBoatsByRegion(String region)`

按地区过滤并显示船只信息的方法。

### `showBoatsByVariant(String variant)`

按型号过滤并显示船只信息的方法。

### `showBoatsByrPrice(int price)`

按租金价格过滤并显示船只信息的方法。

### `showBoatsBysPrice(int price)`

按销售价格过滤并显示船只信息的方法。

### `showBoatsByYear(int year)`

按年份过滤并显示船只信息的方法。

### `showBoatsByPriceRange(int minPrice, int maxPrice)`

按价格范围过滤并显示船只信息的方法。

### `showBoatsByLengthRange(int minLength, int maxLength)`

按长度范围过滤并显示船只信息的方法。

### `showBoatsByYearRange(int minYear, int maxYear)`

按年份范围过滤并显示船只信息的方法。

### `showBoatsBysPriceAndMake(double price, String make)`

按销售价格和制造商过滤并显示船只信息的方法。

[点这里跳转代码][DC]


```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Database {
    HashMap<String, ArrayList<Boat>> byAttributeBoats = new HashMap<>();
    HashMap<Boat, Boat> allBoats = new HashMap<>();
    TreeMap<Integer, ArrayList<Boat>> rPriceBoats = new TreeMap<>();
    TreeMap<Integer, ArrayList<Boat>> sPriceBoats = new TreeMap<>();
    TreeMap<Integer, ArrayList<Boat>> lengthBoats = new TreeMap<>();
    TreeMap<Integer, ArrayList<Boat>> yearBoats = new TreeMap<>();

    public void showAllMakes() {
        // Method body
    }

    public void showAllrPrice() {
        // Method body
    }

    public void showAllsPrice() {
        // Method body
    }

    public double getAveragePrice(String make) {
        // Method body
        return 0.0;
    }

    public void show() {
        // Method body
    }

    public void showAllBoats() {
        // Method body
    }

    public void showBoatsByMake(String make) {
        // Method body
    }

    public void showBoatsByRegion(String region) {
        // Method body
    }

    public void showBoatsByVariant(String variant) {
        // Method body
    }

    public void showBoatsByrPrice(int price) {
        // Method body
    }

    public void showBoatsBysPrice(int price) {
        // Method body
    }

    public void showBoatsByYear(int year) {
        // Method body
    }

    public void showBoatsByPriceRange(int minPrice, int maxPrice) {
        // Method body
    }

    public void showBoatsByLengthRange(int minLength, int maxLength) {
        // Method body
    }

    public void showBoatsByYearRange(int minYear, int maxYear) {
        // Method body
    }

    public void showBoatsBysPriceAndMake(double price, String make) {
        // Method body
    }

    public static void main(String[] args) {
        Database database = new Database();
        database.showAllMakes();
        database.showAllrPrice();
    }

    public Database() {
        // Constructor body
    }
}
```
# Boat 类

## 简介

`Boat` 类是表示船只的 Java 类，实现了 `Asset` 接口并实现了 `Comparable<Boat>` 接口。该类包含船只的各种属性和方法。

## 数据字段

- `private String make`: 船只制造商。
- `private String variant`: 船只型号。
- `private int length`: 船只长度。
- `private String region`: 船只所在地区。
- `private int sellPrice`: 船只销售价格。
- `private int costPrice`: 船只成本价格。
- `private int rentPrice`: 船只租金价格。
- `private int year`: 船只制造年份。
- `private int[] prices`: 包含租金、销售和成本价格的数组。
- `private Person owner`: 船只所有者。
- `private Person user`: 船只使用者。


[点这里跳转代码][BC]

```java
public class Boat implements Asset, Comparable<Boat> {
    private String make;
    private String variant;
    private int length;
    private String region;
    private int sellPrice;
    private int costPrice;
    private int rentPrice;
    private int year;
    private int[] prices = new int[3];
    private Person owner;
    private Person user;

    // 构造函数
    public Boat(String make, String variant, int length, String region, int sellPrice, int costPrice, int rentPrice, int year) {
        // 实现构造函数
    }

    // 获取制造商
    public String getMake() {
        // 实现方法
    }

    // 获取型号
    public String getVariant() {
        // 实现方法
    }

    // 获取价格
    @Override
    public int[] getPrice() {
        // 实现方法
    }

    // 设置价格
    @Override
    public void setPrice(int rentPrice, int sellPrice, int costPrice) {
        // 实现方法
    }

    // 获取所有者
    @Override
    public Person getOwner() {
        // 实现方法
    }

    // 设置所有者
    @Override
    public void setOwner(Person owner) {
        // 实现方法
    }

    // 获取使用者
    @Override
    public Person getUser() {
        // 实现方法
    }

    // 设置使用者
    @Override
    public void setUser(Person user) {
        // 实现方法
    }

    // 获取制造商（getMaker）
    public String getMaker() {
        // 实现方法
    }

    // 设置制造商（setMaker）
    public void setMaker(String maker) {
        // 实现方法
    }

    // 获取长度
    public int getLength() {
        // 实现方法
    }

    // 设置长度
    public void setLength(int length) {
        // 实现方法
    }

    // 获取地区
    public String getRegion() {
        // 实现方法
    }

    // 设置地区
    public void setRegion(String region) {
        // 实现方法
    }

    // 获取成本价格
    public int getCostPrice() {
        // 实现方法
    }

    // 设置成本价格
    public void setCostPrice(int costPrice) {
        // 实现方法
    }

    // 获取租金价格
    public int getRentPrice() {
        // 实现方法
    }

    // 设置租金价格
    public void setRentPrice(int rentPrice) {
        // 实现方法
    }

    // 获取制造年份
    public int getYear() {
        // 实现方法
    }

    // 设置制造年份
    public void setYear(int year) {
        // 实现方法
    }

    // 比较
    @Override
    public int compareTo(Boat o) {
        // 实现方法
    }

    // hashCode 方法
    @Override
    public int hashCode() {
        // 实现方法
    }

    // equals 方法
    @Override
    public boolean equals(Object obj) {
        // 实现方法
    }
}
```




<p align="left"> <a href="https://www.kean.edu/" target="_blank" rel="noreferrer"> <img src="https://www.kean.edu/themes/custom/kean/logo.svg" alt="KeanU" width="300" height="78"/> </a> 

[ZL]: https://github.com/Lareina2441
[JY]: https://github.com/GemmaJiao
[ZJ]: https://github.com/AlbertZhaoCA
[wku]: https://www.wku.edu.cn/en/
[DC]: ./src/backend/Datastructure.java
[BC]: ./src/boat/Boat.java
