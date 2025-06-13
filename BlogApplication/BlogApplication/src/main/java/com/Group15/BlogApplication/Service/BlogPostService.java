package com.Group15.BlogApplication.Service;


import com.Group15.BlogApplication.Repository.BlogPostRepository;
import com.Group15.BlogApplication.Model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }



    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost save(BlogPost post) {
        return blogPostRepository.save(post);
    }

    public void delete(Long id) {
        blogPostRepository.deleteById(id);
    }

    public Page<BlogPost> getPaginatedPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return blogPostRepository.findAll(pageable);
    }

}


