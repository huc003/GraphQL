package com.example.controller;

import com.example.graphQL.schema.GraphSchema;
import com.example.utils.JSONUtils;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hucheng on 2017/4/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login() throws Exception {
        String param = "{user(id:10){id,sex,name,pic}}";
        GraphQLSchema schema = new GraphSchema().getSchema();
        Map<String,Object> result = (Map<String, Object>) GraphQL.newGraphQL(schema).build().execute(param).getData();
        String s = new JSONUtils(false).toJSON("response", result);
        System.out.println(s);
        return s;
    }
}
