package com.example.filestorage;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    public String addFile(String title, MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("title", title);
        ObjectId id = gridFsTemplate.store(file.getInputStream(), title, file.getContentType(), metaData);
        return id.toString();
    }

    public File getFile(String id) throws IllegalStateException, IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        File file = new File();
        file.setTitle(gridFSFile.getMetadata().get("title").toString());
        file.setStream(operations.getResource(gridFSFile).getInputStream());
        return file;
    }

    public List<File> getAll(){
        GridFSFindIterable gridFSFindIterable = gridFsTemplate.find(new Query());
        List<File> files = new ArrayList<>();
        gridFSFindIterable.forEach(gridFSFile -> {
            File file = new File();
            String tmp = gridFSFile.getId().toString().substring(19, 43);
            file.setId(tmp);
            file.setTitle(gridFSFile.getMetadata().get("title").toString());
            files.add(file);
        });
        return files;
    }

    public void deleteFile(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        gridFsTemplate.delete(query);
    }
}