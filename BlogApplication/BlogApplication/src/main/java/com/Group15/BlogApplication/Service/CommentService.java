package com.Group15.BlogApplication.Service;

import com.Group15.BlogApplication.Repository.CommentRepo;
import com.Group15.BlogApplication.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

    public void create(Comment comment){
        commentRepo.save(comment);
    }

    public void delete(Long id){
        commentRepo.deleteById(id);
    }
}
