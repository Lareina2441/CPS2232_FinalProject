 
<h1 align="center"> CPS 2232 FinalProject: Boat Manager</h1>  
<p align="left"> <a href="https://www.wku.edu.cn/en/" target="_blank" rel="noreferrer"> <img src="https://wku.edu.cn/wp-content/uploads/2016/02/logo_en.png" alt="WKU" width="300" height="78"/> </a>   

Welcome to our project !  This is the `final project` for `CPS 2232`, `Abstract Data Type`, of [`Wenzhou-Kean University`][wku].       
       
**The authors:**   
[***Zhang Lei***][ZL]   
[***Jiao Luyao***][JY]   
[***Zhao Qinjian***][ZJ]

## How to deploy our project

       git clone https://github.com/Lareina2441/CPS2232_FinalProject.git  
Open this project in your local IDE (the following is an example using IDEA).
Find `project structure`  then click dependencies. Add the jars file in [resources](./resources)

![image](https://github.com/Lareina2441/CPS2232_FinalProject/assets/111401870/81bfb7f9-e870-4aef-bf8e-1c4067f3e969)                   


CHANGE EMAIL INFORMATION IN [COMPANY.JAVA](./src/backend/CompanyEmailSender.java) and [COMANY.JAVA](./src/person/Company.java)  


## 1. Run the [BoatCreater](./src/boat/BoatCreator.java) class first, when the binary code generated successfully, run the [Menu](./src/backend/Menu.java)

![img.png](resources/img.png)
<p align="center"> (the boats has been created, we got 2346 boats in total)</p>  

## 2. Run the main method in [Menu](./src/backend/Menu.java)
   class, then you can run the project as administrator or client.  
![img_1.png](resources/img_1.png)![img_2.png](resources/img_2.png)
<p align="center"> (when someone is using the system, the email will be sent to the administrator's email.
)</p>

## 3. enter the system you want (client or administrator), then you can use the system.
### Company System :
**SEAERCH** : you can **SEARCH** by function 5 and 6.
**you can search boat information by our filter:**  
![img_4.png](resources/img_4.png) **you can see the user information, THE PASSWORD IS HIDDEN**
![img_8.png](resources/img_8.png)**Following are boats informaiton**
![img_5.png](resources/img_5.png) **you can add a boat**
![img_6.png](resources/img_6.png) **you can delete a boat by searching the boat info by filter**
![img_11.png](resources/img_11.png)
![img_10.png](resources/img_10.png)**you can search detailed information of a boat by our filter**
![img_7.png](resources/img_7.png) **you can modify a boat** 
![img_9.png](resources/img_9.png)

### Client System :
**REGISTER** : you can register a new account in the system.
![img_3.png](resources/img_3.png)
<p align="left"> <a href="https://www.kean.edu/" target="_blank" rel="noreferrer"> <img src="https://www.kean.edu/themes/custom/kean/logo.svg" alt="KeanU" width="300" height="78"/> </a>

<img width="512" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/f7014579-9702-4cb0-9be1-0997393958b4">
<img width="680" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/053409d2-7852-43e6-b011-8732a4d740a3">
<img width="734" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/a8e6fc11-44ab-4757-9bf6-f466245e9fe6">
Use data from csv to create object "boat". put all boats in ArrayList and give their index. 
<img width="563" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/240e4ee5-b6ae-493c-8dbd-eafd447ba936">
<img width="539" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/3fcb3a32-8333-45ee-8484-259c860c2bb7">
use objectOutputStream, write object "ArrayList" in file.
emphasize: the reason we use ArrayList
1. traverse/ access by index
2. low efficiency in "add" or "remove". but we avoid it.

### register()
<img width="677" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/dd4f3f04-0750-4851-9477-cf902528ff44">
we also save clients' info in File and offer method to load it.
<img width="630" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/00615819-2840-4332-b2da-b93725294b6a">
<img width="568" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/5003bc5c-3929-498c-9d28-c47843767676">
when client register their count, new entry <String, Client> will be put in Map.
Map: without duplicate so without same user name(!containsKey(key)), just O(1) to find specific Client.
 
### login()
<img width="703" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/ed248400-3ddf-4e8f-ac61-2f23fa8ae578">
when login successfully, "fiveRequest(name)"will be invoked to let client do following operation. In this step, "name" be a parameter passed in following method.
<img width="512" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/8f0e2410-a694-44a7-991d-5aa9fed440a6">
 
#### 1. borrow and buy
<img width="800" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/d1a26c2a-b4eb-41ca-b38d-51b430874520">
all use method recommandBoat to return the boat the client want to operate.
<img width="731" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/82648cda-c627-471a-8534-12cd264fb9ea">
<img width="694" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/a445a110-453f-4b68-bcc7-6572b8594384">
Client may not know which boat to choose, we will use subMap to recommand boat by price.
After client know which boat to buy/borrow, transaction start.
<img width="466" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/79b4fd04-46c5-4746-bb68-3de0bc779da7">

<img width="734" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/e57b3614-8972-49f8-9bda-88f46ff964b1">
buyTransaction is same as borrowTransaction in aspect of judge the boat is busy or not. but a little differnet:

<img width="189" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/7773f6e1-e3b3-42de-bc39-8dd43b4cde45">

#### 2. return
<img width="564" alt="image" src="https://github.com/Lareina2441/CPS2232_FinalProject/assets/152167380/80a8e76c-b158-4c45-8783-2365870e9679">
we not expect that client will return the boat which is sold out, so we just check use() list.


[ZL]: https://github.com/Lareina2441
[JY]: https://github.com/GemmaJiao
[ZJ]: https://github.com/AlbertZhaoCA
[wku]: https://www.wku.edu.cn/en/
[DC]: ./src/backend/Datastructure.java
[BC]: ./src/boat/Boat.java
