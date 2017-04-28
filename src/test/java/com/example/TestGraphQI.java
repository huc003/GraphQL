package com.example;

import com.example.graphQL.schema.GraphSchema;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import java.util.Map;

/**
 * Created by huc on 2017/4/28.
 */
public class TestGraphQI {

    public static void main(String[] args) {
//        GraphQLObjectType queryType = newObject().name("helloWorldQuery").field(newFieldDefinition().type(GraphQLString).name("hello").staticValue("world")).build();
//        GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();
//        GraphQL graphQL = GraphQL.newGraphQL(schema).build();
//        Map<String, Object> result = (Map<String, Object>) graphQL.execute("{hello}").getData();
//        System.out.println(result);


        GraphQLSchema schema = new GraphSchema().getSchema();
        String sql1 = "{user(id:10){id,sex,name,pic}}";
        Map<String,Object> result1 = (Map<String, Object>) GraphQL.newGraphQL(schema).build().execute(sql1).getData();

        System.out.println(result1);

    }
}
