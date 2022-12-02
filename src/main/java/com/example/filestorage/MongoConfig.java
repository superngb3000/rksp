package com.example.filestorage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private MappingMongoConverter mongoConverter;

    @Bean
    public GridFsTemplate gridFsTemplate() {
        return new GridFsTemplate(mongoDbFactory(), mongoConverter);
    }

    @Override
    public MongoClient mongoClient(){
        return MongoClients.create("mongoFileStorage://root:mongo@mongo:27017/files");
    }

    @Override
    protected String getDatabaseName() {
        return "files";
    }
}