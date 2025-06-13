package com.Group15.BlogApplication.Repository;


import com.Group15.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findByCategoryContainingIgnoreCase(String category);
}

