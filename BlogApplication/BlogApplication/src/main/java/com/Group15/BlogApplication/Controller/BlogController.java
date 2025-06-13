package com.Group15.BlogApplication.Controller;

import com.Group15.BlogApplication.Service.BlogPostService;
import com.Group15.BlogApplication.Service.CommentService;
import com.Group15.BlogApplication.Model.BlogPost;
import com.Group15.BlogApplication.Model.Comment;
import com.Group15.BlogApplication.Model.User;
import com.Group15.BlogApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class BlogController {



    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private CommentService commentService;


    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page) {
        Page<BlogPost> postPage = blogPostService.getPaginatedPosts(page, 5);
        model.addAttribute("postPage", postPage);
        model.addAttribute("posts", postPage.getContent());
        return "home";
    }



    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        BlogPost post = blogPostService.getPostById(id).get();
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment()); // For form binding
        model.addAttribute("comments", post.getComments()); // To display existing comments
        return "post-detail";
    }


    @PostMapping("/blog/{postId}/comments")
    public String addComment(@PathVariable Long postId,
                             @ModelAttribute Comment comment,
                             Principal principal) {
        BlogPost blogPost = blogPostService.getPostById(postId).get();
        User user = userService.findByUsername(principal.getName());

        comment.setId(null);
        comment.setBlogPost(blogPost);
//        comment.setUser(user);
        comment.setDate(LocalDateTime.now());

        commentService.create(comment);

        return "redirect:/";
    }





    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "create-post";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute BlogPost post) {
        post.setCreatedDate(LocalDateTime.now());
        blogPostService.save(post);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost (@PathVariable Long id, Model model){
        blogPostService.delete(id);
        model.addAttribute("posts", blogPostService.getAllPosts());
        return "redirect:/";
    }



   @GetMapping("/pagination")
   public String getPaginatedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ){
        Page<BlogPost> postPage = blogPostService.getPaginatedPosts(page, size);
        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("postPage", postPage);
        return "home";
    }

}
