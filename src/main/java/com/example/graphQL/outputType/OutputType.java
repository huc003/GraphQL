package com.example.graphQL.outputType;

import com.example.graphQL.bean.User;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

import java.util.ArrayList;
import java.util.List;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by huc on 2017/4/28.
 * 结构对象
 */
public class OutputType {
    private GraphQLObjectType userType;
    public void initOutputType(){
        userType = newObject().name("User").field(newFieldDefinition().name("id").type(GraphQLInt).build())
                .field(newFieldDefinition().name("age").type(GraphQLInt).build())
                .field(newFieldDefinition().name("sex").type(GraphQLInt).build())
                .field(newFieldDefinition().name("name").type(GraphQLString).build())
                .field(newFieldDefinition().name("pic").type(GraphQLString).build()).build();
    }

    /**
     * 查询一条记录
     * @return
     */
    public GraphQLFieldDefinition resultSql(){
//        return GraphQLFieldDefinition.newFieldDefinition().name("user").argument(newArgument().name("id").type(GraphQLInt).build()).type(userType)
//                    .dataFetcher(environment -> {
//                    //获取查询参数
//                    int id = environment.getArgument("id");
//                    //执行查询操作
//                    User user = new User();
//                    user.setId(id);
//                    user.setAge(id+15);
//                    user.setSex(id%2);
//                    user.setName("Name_"+id);
//                    user.setPic("pic_"+id+".jpg");
//                    return user;
//                }).build();

        GraphQLFieldDefinition findforid = GraphQLFieldDefinition.newFieldDefinition().name("user")
                .argument(//这里用GraphQL些查询语句时传入的参数
                        newArgument()
                                .name("id")//参数名为id
                                .type(Scalars.GraphQLInt)//参数类型
                                .build()
                )
                .type(userType)//绑定GraphQL的一个结构，就是上面的那段代码
                .dataFetcher(environment -> {
                    // 获取查询参数
                    int id = environment.getArgument("id");
                    User user = new User();
                    user.setId(id);
                    user.setAge(id+15);
                    user.setSex(id%2);
                    user.setName("Name_"+id);
                    user.setPic("pic_"+id+".jpg");
//                    ZyProxy zyProxy = new ZyProxy();//一个对ZyVo数据进行增删改查代理的操作类
//                    ZyVo vo = null;
//                    try {
//                        vo = zyProxy.findById(id);//通过ID查找数据
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(vo);
                    return user;
                }).build();

        return findforid;
    }

    /**
     * 查询多条记录
     * @return
     */
    public GraphQLFieldDefinition resultList(){
        GraphQLFieldDefinition findforid = GraphQLFieldDefinition.newFieldDefinition().name("users")
                //这里用GraphQL些查询语句时传入的参数
                .argument(newArgument().name("page").type(GraphQLInt).build())
                .argument(newArgument().name("size").type(GraphQLInt).build())
                .argument(newArgument().name("name").type(GraphQLString).build()).type(new GraphQLList(userType))
                .dataFetcher(environment -> {
                    //获取传入进来的参数
                    int page = environment.getArgument("page");
                    int size = environment.getArgument("size");
                    int name = environment.getArgument("name");

                    //执行查询
                    List<User> list = new ArrayList<User>(size);
                    for (int i = 0; i < size; i++) {
                        User user = new User();
                        user.setId(i);
                        user.setAge(i+15);
                        user.setSex(i%2);
                        user.setName(name+"_"+page+"_"+i);
                        user.setPic("pic_"+i+".jpg");
                        list.add(user);
                    }
                    return list;
                }).build();
        return findforid;
    }

}
