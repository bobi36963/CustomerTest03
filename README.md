# 一、项目来源

客户关系管理系统是我在知乎上看到一位同行发的帖子，该网友名为[codingXiaxw](https://www.zhihu.com/people/xia-xun-wu-56)。

由于我是初学JSP与Servlet，所以采用了他的第一个项目，比较简单，感觉也不是很难。

# 二、项目环境

## 环境搭建

1、IDEA 2019.3

2、WebStorm 2019.3

3、JDK 13

4、Mysql 8

5、Tomcat 8.5.16

## 主要技术

Java、Mysql、JSP、Servlet、layui、JSON



# 三、项目介绍

​		该项目主要就是实现客户信息的增删查改，以及管理员账户的登录，由于这是初学JSP与Servlet，这里不对登录功能做过多的功能。

​		这里采用了layui前端框架制作的前端模板layui mini，具体的学习和使用过程请参照layui的官网https://www.layui.com/。

​		如果不考虑项目的前端页面，这个项目或许很好做，学习JSP与Servlet我找的是B站的一个视频，https://www.bilibili.com/video/BV18s411u7EH?p=50&t=1056          该教程使用的是Eclipse，该教程我学习到第33节，这个视频的老师将的比较好，不拖泥带水。因为我正在同时学习Maven与Mybatis，所以这个项目制作的非常粗糙，是为了摸清layui框架与JSP、Servlet的特性，为了使用Maven与Mybatis的复习打好基础。

​		这里我并没有学习Ajax，Jquery也只是略有耳闻，至于JSON，用过一次而已，在下文的问题解决中，我会详细的阐述没有学习这些技术所引起的问题。



# 四、数据库

这里用的Mysql 8 ，可能会引起一些问题，推荐使用Mysql 5.7，以及老版本的Navicat。

数据库非常简单，只有两个表，因为我先搭建了知乎同行的项目，所以里面会多出一个t_customer表，这是人家的表。

我的表为c_customer  与admin，字段什么的不做赘述，请参考我的sql文件，直接使用navicat刷进去即可，或者复制出来在cmd里面刷。



# 五、前端页面

​		layui这个框架可以说入门以及很简单了，我采用的layui mini的单页版 模板，单页版的页面好看，简洁，但是在修改模板的时候极容易引发一些问题。

​		为了保证开发进度，我先在WebStorm中修改好前端页面，消除前端页面引起的bug（不知道是不是应该这样做）。

​		上述问题在WebStorm中操作的时候主要表现为样式文件(.js  .css)的丢失，因为这是单页版，基本只需要在index页面引入样式即可，而多页版需要在每个页面都引入样式文件，不过多页版的问题确实会少很多。

​		具体的页面样式请参考我的项目。



# 六、设计模式

​		设计模式是我大三下学期的一门重要的课，可惜我不经常去上课。

​		这里采用的设计模式不知道应该称之为“MVC”还是“三层架构”，可能还带一点Mybatis的影子。

​		建包：dao(数据库访问层)，entity(实体类)，service(服务层，与servlet交互)，servlet(与前端的交互)

**dao**

​		该层直接与数据库进行交互，这里出的异常什么的也都在这层解决，不会涉及到上层

​		这里我会先建一个IBaseDao 泛型接口，里面写的是add，delete，update，queryById，queryAll这些方法（顾名思义），因为项目有customer与admin两个实体，所以会编写对应的接口去继承IBaseDao接口，因为IBaseDao接口中的方法足够全面，如果后面需要添加特殊的方法，可以在对应的接口中写。

​		如 admin需要一个验证的方法，queryById这个方法是不能胜任的，就需要在IAdminDao接口中写	boolean selectAdmin(Admin admin);原本的IBaseDao并没有修改，但是在写了IAdminDao的实现类后，可以同时实现这两个接口中的方法。

**entity**
		实体类，存放各种对象如customer和admin等实体的属性，并且写了多种构造方法，以满足不同场合下的使用（因为我本人并不喜欢getter/setter方法，除非必要），以及get/set方法，以及toString方法（为了方便测试，用的sout测试，后面会改用log4g）

**service**
		该层就是用来调用dao层的，接受servlet给的参数，然后处理 dao层返回的结果，总的来说service没几行代码。（后面想了想，感觉这个项目加了这层没什么必要，还感觉很麻烦）

​		该层与dao层基本对应，也会创建IBaseService泛型接口，里面写的也是add，delete....这些方法，与dao层的方法一一对应。

**servlet**
		该层直接与前端页面交互，同时接受service层调用的返回结果。这里不做赘述，学习了JSP与servlet自然会懂。



​		如果你会使用Junit进行单元测试，那么编写代码的时候可以先写完dao层与service层的全部代码，毕竟可以通过单元测试来保证代码的正确，如果不用单元测试，那么还是一部分一部分的代码写吧。



**补充：**

​		我设计的时候没有设计好，将util工具类放在了dao包里面，这是错误的。且对于dao层的实现类，我在dao包里添加了一个impl包，service包也是如此也有impl包。

​		还有就是lib的jar包，我没有建立lib包，全部都是扔到了Tomcat安装路径下的lib里面。

​		总的来说，包建的有点乱。



**关于dao层与service层的方法命名问题**

​		因为它们的方法功能都是一样的是一一对应的，所以在命名上应该会差不多，这里我的命名非常的烂。

​		dao层的方法命名要贴近数据库的命名规则，如insert（插入），delete（删除），update（更新），selectById（按照id查询），selectAll（查询所有）。

​		service层的方法命名要贴近人性化，如add（添加），remove（删除），modify（修改），search（查询）或者query也行，等等等等。

​		**关于这两层的方法命名请千万不要参考我的项目，请参考上述规则**



# 七、开发问题及解决方案

## 1、IDEA中Tomcat的环境搭建

这个属于入门问题，我当初也是头疼，csdn实在太水了，如果遇到问题，望君自行解决。

这里推荐一个靠谱点的教程吧[IDEA创建JavaWeb项目](https://blog.csdn.net/skye_95/article/details/80986453?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162289758516780262596808%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=162289758516780262596808&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-80986453.pc_search_result_before_js&utm_term=IDEA%E5%88%9B%E5%BB%BA%E4%B8%80%E4%B8%AAweb%E9%A1%B9%E7%9B%AE&spm=1018.2226.3001.4187)

我本人是直接创建的 Java Enterprise项目，与教程不太一样。

## 2、jar包的引入

按照我老师的说法，我这里其实一点都不规范，因为没有建lib包，所有的依赖包都是一股脑的丢到了Tomcat里，具体的说法我不清楚。

我的做法是，将jar包丢到Tomcat的lib里，按下ctrl+alt+shift+s进入Project Structure，且配置Modules中的Dependencies，编辑里面的Tomcat依赖，在里面选择我们丢进Tomcat->lib中的jar包，这样是可行的。

## 3、前端页面样式的路径问题

这里要首先清楚，我们的浏览器可以直接访问src或者web下的文件。

例如htt p://localhost:8888/MyJspProject/index1.jsp
其中的index1.jsp就在web目录中；

对于前端页面样式的路径：

以我们的项目为例子：web下有page，lib包和index.jsp，我们使用的是单页版，一般来说只在index引入样式即可（也有特殊情况），index与lib为同级，都属于web之下，所以index中的样式引入方式为：

```html
<link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
```

如果要是page中的页面customerAdd.jsp去引用lib中的样式呢？

```html
../lib/layui-v2.5.5/css/layui.css
```

这句话的意思是：../  上一级目录，因为customerAdd在page之下，上一级目录就是web之下，这样一来，../ 就让我们走到了index同级的目录中，后面直接跟着写类似index引用样式的写法。

总的来说，这其实没什么大用，在外面我们下载好模板后，直接在WebStorm中修改即可，完全不用去理会路径的问题，因为模板中的路径都是相对路径。在完成修改后，将前端工程下的所有文件，不包括.idea文件，全部拷贝至IDEA下的web目录下，拷贝过来的也是相对路径，无需担心路径问题。

但是在有些页面中，由于页面跳转会导致样式丢失，或者JQuery未被引入的问题出现，这就需要我们手动解决，记住：lib中的文件的位置，要与当前页面的位置相对应起来，在上层目录就../,上上层就../../,依次类推。

## 4、数据库连接的问题

因为还没有完全学完Mybatis，这里采用了最原始的JDBC，将数据库的连接做成了一个方法，即dao包下util包下DataBaseConnect类中的connectDataBase()方法，该方法返回一个Connection对象。并且我在任何有关数据库的代码中都没有写到有关 关闭的语句，如conn.close(),result.close(),或是statement.close()等等，这是不对的，至于为什么将错就错，完全是因为懒惰，而且这么小的项目感觉也不会影响多少性能，在之后使用Mybatis重做的时候会注意这一点。

这里我采用的是Mysql8，其加载驱动的语句与Mysql5不同：

mysql 8 ：

```java
static final String dataBaseDriver = "com.mysql.cj.jdbc.Driver";
```

mysql 5 ：

```java
static final String dataBaseDriver = "com.mysql.jdbc.Driver";
```

关于url我这里比别人用的更复杂一点，因为使用别人mysql5的语句会报时区错误，我这里顺便处理了一下乱码问题：

```java
static final String dataBaseUrl = "jdbc:mysql://localhost/customer?useSSL=FALSE&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
```

其中：

customer	是我的数据库名字

useSSL=FALSE&serverTimezone=UTC	用于处理时区问题

useUnicode=true&characterEncoding=utf8	用于处理乱码问题

各参数之间使用 & 连接

如果在url的使用上有问题，可以根据报错自行网上百度，毕竟mysql 8 实在是问题太多了（当时我使用别人在PowerDesigner 上导出的mysql5语句，由于主外键限制的原因无法设置主键递增，mysql5可以，我的就不行，后来是从cdm模型里直接设置好主键的递增才解决了问题）

追求细节的话可以网上自行百度数据库连接池，上面我提到的b站教程里面讲到了关于两种数据库连接池的使用，其中一种是c3p0，比较好用。

## 5、后端操作完成后反馈给前端并弹窗提示

我这里的代码并不规范，与下述例子可能略有不同。

举例：

dao层的insert操作，会用到executeUpdate()方法，该方法返回值为一个int型，意为影响的记录数，即如果添加成功，该方法返回1，添加失败则返回0，具体的细节可以参考executeUpdate()方法的源码。

故若添加成功，dao层的insert方法应当返回1，service层的add方法接受该结果，判断：若结果为1，返回true给上层，否则返回false，servlet调用service层的add方法，其接受的返回值为true或者false，即成功或者失败，若成功，给前端页面发送一个success的参数，否则发送一个fail的参数。前端页面接受该参数，通过判断该参数的值success？fail 来弹窗显示不同的提示。

servlet中添加操作 给前端的反馈 代码

```java
ICustomerService customerService = new CustomerService();
boolean result = customerService.add(customer);
resp.setCharacterEncoding("utf-8");

if(result){
  	resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp？operatingResult=addSuccess");
  }else{
  	resp.sendRedirect("/CustomerTest/#/page/customerQuery.jsp?operatingResult=addFail");
}
```

​		这里我采用的是重定向，因为我需要地址栏发生改变，因为我发现如果使用请求转发的话，我的前端样式会丢失，这可能是由于layui单页版的特性导致的。

​		可以发现项目名称CustomerTest后面跟的是/#/，这个符号我不能具体解释出他的含义，但是我清楚他就是单页版的“核心”，按照我的理解它应该算个容器什么的（不清楚对不对），在我的测试中：使用一个完全独立的登录jsp页面（样式均独立引入）adminLogin.jsp，如果在地址栏中写/CustomerTest/#/page/adminLogin.jsp，该登录页面也是在整个容器中，因为在左边可以看到系统的菜单栏，这是不应该的，因为我们的登录界面是独立的，当我们把登录页面从page包中移出来，方到index.jsp的同级目录，/CustomerTest/adminLogin.jsp，直接可以这样写，不会显示出系统的菜单栏，这时它是一个完全独立的页面。

​		关于servlet跳转路径的问题，我完全是试出来的，网上也没找到几个靠谱的教程，反正我的经历附上，具体问题请君自解。

​		这里传递两个参数，参数名为operatingResult，值为addSuccess或者addFail，关于这里的参数使用问题，下面会提到。

​		在servlet传递参数给前端后，前端需要接受，我这里添加成功后，跳转的是查询界面，因为添加成功了总得看吧？看就得在查询里面看。

​		这里接受参数不用多说，学会JSP自然会，我们可以使用JSP的语句来控制html或者js，如：

```java
<%
    if(operatingResult.equals("addSuccess")){
%>
<script>
    layer.msg('客户添加成功！');
</script>
<%
    operatingResult = "noOperating";
    }
%>
```

​		这就是jsp与js的嵌套，意思就是，如果接受的参数为addSuccess，则弹窗提示添加成功，需要注意的是String operatingResult = "noOperating";我设置为了全局变量，可以接受添加，修改等操作的参数，这里还是写的比较严谨的，因为不给它初始化，它在接受不到参数时会报空指针异常（这种情况通常发生在你直接访问查询页面的时候，因为不是从servlet跳转过去的，没有参数），（上述b站视频里的类似操作就会这样），且在使用完该参数后（弹窗提示后）需要将该变量复位（如果不复位，此时你停留在查询界面，再点击其他菜单到其他页面，然后再回到查询页面，此时的operatingResult 可不是noOperating，这时的地址栏都没有参数，你也没有操作，但是它就是会弹窗提示）

​		我这里将添加，修改后的跳转都设在了查询页面，所以这里的代码可以说是又臭又长，详情请看代码。



## 6、servlet的参数问题

​		我们可以通过在地址后面写一个 "?"，后面加	参数名=参数值		这样的操作来添加参数。

​		如果是多个参数的话就是：参数名=参数值&参数名=参数值			使用&连接。当然如果参数值是一个变量的话，字符串的拼接完全可以解决，而且据我所知，这样写的话，传递的值只能是字符串，不能传对象什么的。

### 		servlet向jsp传递参数时的中文乱码问题

​		这个问题很重要，解决方案如下：

```java
Customer customer = customerService.queryByName(searchCustomerName);

String customerId = URLEncoder.encode(customer.getId()+"","utf-8");
String customerName = URLEncoder.encode(searchCustomerName,"utf-8");
String customerSex = URLEncoder.encode(customer.getGender(),"utf-8");
String customerPhone = URLEncoder.encode(customer.getPhone(),"utf-8");
String customerEmail = URLEncoder.encode(customer.getEmail(),"utf-8");
String customerRemark = URLEncoder.encode(customer.getDescription(),"utf-8");

resp.setContentType("text/html;charset=UTF-8");
resp.sendRedirect("/CustomerTest/#/page/customerSearch.jsp" +
        "?operatingResult=searchSuccess" +
        "&customerId="+customerId+
        "&customerName="+customerName+
        "&customerSex="+customerSex+
        "&customerPhone="+customerPhone+
        "&customerEmail="+customerEmail+
        "&customerRemark="+customerRemark
);
```

​		customer是我从下层获取了一个customer对象。

​		customer.getId()使用get方法获取属性的值。

​		String customerId = URLEncoder.encode(customer.getId()+"","utf-8");

​		将获取的属性的值以utf-8的格式重新编码，然后赋值给一个新的变量customerId ，这时customerId 可以作为我们的参数值传递出去。

​		那么中文乱码问题表现为什么样子呢？比方说这是一个链接正常的样子

```
CustomerTest/#/page/customerSearch.jsp
?operatingResult=searchSuccess
&customerId=1&customerName=我&customerRemark=我就是我
```

乱码的时候：

```
CustomerTest/#/page/customerSearch.jsp
?operatingResult=searchSuccess
&customerId=1&customerName=?&customerRemark=????
```

可以发现，中文全是？，而且几个字就是几个?

使用上述方法完全可以解决这个问题，而使用new String(string.getBytes(“ISO-8859-1”),”UTF-8”);这种方法，对于该项目来说，完全不行。



## 7、表格的问题

### 7.1表格数据的获取

学习过layui后我们会知道，layui表格的数据源于一个json（静态网页的话json存在web下的api包里，其实你用servlet吧json打印到页面也是可以的），开始我把从后台查到的数据转化为.json文件存到项目根目录里面，这里路径的问题就很难解决，我直接使用了绝对路径（绝对的不能再绝对了），这样确实是可以显示的，但是进行增删查改会很麻烦，总之就是这个方法并不实用。

采用的方法：

使用FASTJSON的jar包中的JSONArray.fromObject()方法，直接将List<Customer> 转化为一个JSONArray，然后将"code"（没啥用），"msg"（也没啥用），"count"（记录的数量），"data"（data其实就是我们这个JSONArray的参数名，JSONArray是参数值）等参数逐个put 入jsonObject中，使用

```
PrintWriter out=resp.getWriter();
out.println(jsonObject.toString());
```

将这个jsonObject输出到这个servlet界面上，随后直接在jsp中表格中的url中填写这个servlet的名称，即可完美的将整串的数据到展示到表格中，具体的实现请看代码。



### 7.2表格分页的实现

layui的分页还是很容易实现的，在前端的表格中，主要参数有三个：

```html
limits: [15,30,45,60,75,90],
limit: 15,
page: true,
```

page是分页的开关，limit是设置每页的记录个数，limits是每页条数的选择项（就是个下拉列表框,可以更改每页显示记录的个数)，具体的其他参数的设置，请参考layui的官方文档。

在前端设置好这些参数后，我们只需在对应的servlet中获取page和limit这两个参数

page的意思是当前是第几页（page不是true吗？我们无需关心这是怎么实现的）。

limit的意思是一页的记录数量。



接下来就是告诉我们的dao层分页的参数，我们都知道Mysql的分页用的是limit关键字，如：

```sql
SELECT * FROM table LIMIT 5,10;  // 检索记录行 6-15
```

limit m,n  从第m+1条记录开始检索，往后检索n条。

那么我们上面的参数page就没用了，我们需要转化一下。即：

```java
 int pages = (page-1)*limit;
```

之后，我们只需将pages，limit两个参数传给dao层，数据库即可分页查询我们所需的数据。

```java
public List<Customer> queryAll(int pages,int limit)
    
String querySql = "SELECT id,name,gender,phone,email,description FROM c_customer limit "+pages+","+limit;
```

具体操作请看代码。



### 7.3表格数据的单个删除

layui mini模板只能实现单个记录的删除（每条记录都有一个删除按钮，请看我的实现效果，仅为前端效果），对于表格上方的批量删除，我开始的想法是跳转到删除操作的servlet，循环删除每条记录，删除完之后跳转回查询界面，后来发现这样比较麻烦。

之后还是通过ajax解决了这个问题，当然，我没学习过ajax，不清楚原理，这里只介绍用法。

layui的工具条不做赘述，只需要知道在点击某行工具条时，该行对应的数据也会被选取。即：

```html
var data = obj.data;
```

这个data就是我们需要的数据，之后需要将其转化为json：

```javascript
data: {
        borId: JSON.stringify(data),
       }
```

borId为参数名，JSON.stringify(data)为参数值，我们在servlet中接受的就是这个参数

当监听到我们点击“删除”工具条时，我们要弹出提示框“确定删除行吗？”，这里用到的是layer.confirm(content, options, yes, cancel) - 询问框，具体用法请参考layui官网。

我们这里的用法是：

```javascript
layer.confirm('is not?', function(index){
  //do something
  layer.close(index);
});       
```

在这里ajax的语句中，

url: 'DeleteCustomerServlet'；

data：就是上面的数据；

method：（提交方式，这里还是得填写get，我曾尝试填写post会报500错误）；

contentType: 'application/json' （没有查是什么意思，想来应该是提交的数据类型吧）

success: function () ：操作成功后的操作，这里我写了三个操作：

```javascript
obj.del();
layer.close(index);
layer.msg('成功删除客户！');
```

obj.del();	layui自带的前端删除数据操作，只针对前端页面有用

layer.close(index);	关闭当前提示窗口confirm

layer.msg('成功删除客户！');	弹窗提示删除成功



在上述操作完成后，前端的被选中数据会被提交到后端，看一下后端的操作：

刚刚我们提到了一个参数：borId: JSON.stringify(data)

在servlet中我们需要获取这个参数，但是获取到的值只能是String类型的，这时，我们就用到了FASTJSON

```java
String customer = req.getParameter("borId");
JSONObject obj1= JSONObject.parseObject(customer);
String id=obj1.getString("id");
```

将获取到的json字符串（就是我们的borId参数）通过FASTJSON转化为json对象，通过json对象的方法来获取对应的值，如上述代码所示，我们获取名为id的值，之后便可以调用service层，dao层对数据库进行操作，彻底的删除某条信息。

这里因为不清楚ajax的使用方式（其实是可以利用ajax来实现反馈的，但是不会），没有在servlet中写对前端的反馈，写到如下语句就结束了，甚至没有判断result的值是不是true。

```java
boolean result = service.delete(id);
```

一般来说都是能成功删除的，如果删除失败（有主外键关联的话可能会导致删除失败，该项目没有，而且一般来说表格的数据都是实时的，删除的时候肯定是能找到的，除非你开着网页进数据库里面把数据删了还不刷新网页，这样会出错）

异常也会被牢牢控制在dao层，不会跑到我们的上层来，但是前端仍然会显示删除成功，或者可能报500错误，关于删除失败的测试，我并没有多测，随后重做可能会着重解决这些细节问题。



### 7.4表格数据的批量删除

与上述的单个删除类似，有不同的就是：layui mini给出了单个数据删除的操作，但是批量删除却没有，测试模板的批量删除只是给出一个弹窗用来显示选中的数据，而表格本身没有变化。

这里用到的前端的批量删除的操作是我在网上找到的，原理不详，望君自解。

批量删除与单个删除操作基本相同：

首先获取数据

```javascript
var checkStatus = table.checkStatus('currentTableId')
    , data = checkStatus.data;
```

然后弹窗confirm

```javascript
layer.confirm('确定删除选中的行？', {icon: 3, title: '提示信息'}, function (index) 
```

接下来就是ajax

我们只需要将单个删除 的操作 obj.del();

替换为批量删除的操作

```javascript
//layui中找到CheckBox所在的行，并遍历找到行的顺序
$("div.layui-table-body table tbody input[name='layTableCheckbox']:checked").each(function () { // 遍历选中的checkbox
    n = $(this).parents("tbody tr").index();  // 获取checkbox所在行的顺序
    //移除行
    $("div.layui-table-body table tbody ").find("tr:eq(" + n + ")").remove();
    //如果是全选移除，就将全选CheckBox还原为未选中状态
    $("div.layui-table-header table thead div.layui-unselect.layui-form-checkbox").removeClass("layui-form-checked");
});
```

根据我的测试，该操作是有bug的，原理不详，有能力的同志请自行解决。



### 7.5添加、修改、详情工具条

这些工具条的使用会使得customerQuery页面弹出一个小页面，具体效果可以参考效果展示。

**添加（弹窗）**

该页面只要修改模板后便可直接使用，用的便是菜单栏中的添加用户页面（customerAdd）

**修改（弹窗）**

该页面的实现，只需要我们复制customerAdd页面，然后修改页面的名字为customerModify，当然也要对应一个servlet及其service层与dao层。

需要注意的是，我们需要从customerQuery页面的表格中直接获取数据（这可能会导致bug，就像删除操作中的举例：开着网页去改动数据库中的信息，或许我的这种设计根本不规范），不过这还是提高了一定的性能。

从customerQuery页面的表格中取得数据：

我们需要在customerQuery页面的js中定义一个全局变量：

```javascript
var json;
```

接着给这个json赋值（将监听到修改工具条的点击后获取的数据转化为json 并赋值给json变量）

```javascript
json = JSON.stringify(data);
```

在customerModify页面中获取父页面的数据：

```javascript
//从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
var json = eval('(' + parent.json + ')');
```

随后可以通过“.”的方式获取json中的值，如：json.id	json.name

然后我们使用jquery给表单赋值：

```javascript
document.getElementById("customerId").value = json.id;
document.getElementById("customerName").value = json.name;
document.getElementById("customerPhone").value = json.phone;
document.getElementById("customerEmail").value = json.email;
document.getElementById("customerRemark").value = json.description;

if(json.gender=='男'){
    $("input[name='customerSex'][value='男']").attr("checked",true);
}
if(json.gender=='女'){
    $("input[name='customerSex'][value='女']").attr("checked",true);
}
```

这样一来，修改页面就有了默认的值，其余的操作就像添加页面一样，提交表单给下层去操作。

**详情（弹窗）**

与修改操作相同，复制添加页面使用jquery给表单赋值即可，该页面纯粹就是一个静态的页面，同时也可以去掉焦点监听和表单验证。

#### 补充：高级查询页面的实现

该页面由两个表单组成，一个是搜索关键字（简单起见，只有一个姓名作为搜索关键字），一个是详情信息。

我们在提交搜索关键字表单到后台后，后台应当返回对应的参数给前端（可以用json，也可以直接用变量），前端接受参数并且使用jquery给详情信息表单赋值，需要注意的是参数中含有中文，这里会出现中文乱码，可以参考上文servlet中文乱码问题的解决办法。

还有就是**jsp**中的变量在**js**中使用？

jsp中的变量都属于java范畴，我们可以使用jsp中的<%= %>来获取变量的值

然后赋值给js中的变量，如：

```javascript
var id = '<%=customerId%>';
```

记得一定要加单引号。



## 8、遇到的404、500错误

这些错误每个人每个项目的原因都是不相同的，这里说一下我遇到的：

### 404

由于没有配置web.xml导致无法找到servlet而导致的404

由于跳转路径写错而导致的404

由于接受到的参数值为空导致空指针异常而导致的404

### 500

servlet中语句错误导致的500，或者ajax中post/get用的不正确导致的500



# 八、未来

未来我会使用Maven、Mybatis等新的技术重做一下这个小小的项目，这个小小的项目漏洞百出，看起来非常的简单精致却又非常的粗糙。

评价一下：目之所急，皆是漏洞。

设计的非常粗糙，前端页面并没有改完，登录功能是个残废，各种bug懒得改了，还好客户管理的功能都实现了

之所以放出来给人看，就是为了听听别人的意见，使我更快的进步。



# 九、作者

CSDN：BobLoveJava

QQ：1517967907

邮箱：1517967907@qq.com

项目GitHub地址：https://github.com/bobi36963/CustomerTest03.git





