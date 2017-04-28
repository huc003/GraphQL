package com.example.graphQL.schema;

import com.example.graphQL.outputType.OutputType;
import graphql.schema.GraphQLSchema;

import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by huc on 2017/4/28.
 */
public class GraphSchema {
    private GraphQLSchema schema;
    public GraphSchema(){
        OutputType outputType = new OutputType();
        outputType.initOutputType();
        schema = GraphQLSchema.newSchema().query(newObject().name("GraphQuery")
        .field(outputType.resultSql())
        .field(outputType.resultList()).build()).build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public void setSchema(GraphQLSchema schema) {
        this.schema = schema;
    }
}
