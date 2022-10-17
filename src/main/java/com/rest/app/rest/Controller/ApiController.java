package com.rest.app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController
public class ApiController {

    @AutoWired
    private UserRepository userRepository;

    @AutoWired
    private PostRepository postRepository;

    @GetMapping(value = '/')
    public Strin getPage() {
        return "Hello World";
    }

    @GetMapping(value = '/users')
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = '/users')
    public String saveUser(User user) {
        userRepository.save(user);
        return "User saved";
    }

    @GetMapping(value = '/users/{id}')
    public User getUser(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping(value = '/users/{id}')
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @PutMapping(value = '/users/{id}')
    public String updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();

        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());

        return "User updated";
    }

    @GetMapping(value = '/users/{id}/posts')
    public List<Post> getPosts(@PathVariable("id") long id) {
        return userRepository.findById(id).getPosts();
    }

    @PostMapping(value = '/users/{id}/posts')
    public String savePost(@PathVariable("id") long id, @RequestBody Post post, User user) {
        User user = userRepository.findById(id);
        post.setUser(user);
        postRepository.save(post);
        return "Post saved";
    }

    @GetMapping(value = '/users/{id}/posts/{postId}')
    public Post getPost(@PathVariable("id") long id, @PathVariable("postId") long postId) {
        return postRepository.findById(postId);
    }

    @DeleteMapping(value = '/users/{id}/posts/{postId}')
    public String deletePost(@PathVariable("id") long id, @PathVariable("postId") long postId) {
        postRepository.deleteById(postId);
        return "Post deleted";
    }

    @PutMapping(value = '/users/{id}/posts/{postId}')
    public String updatePost(@PathVariable("id") long id, @PathVariable("postId") long postId, @RequestBody Post post) {
        Post updatedPost = postRepository.findById(postId).get();

        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        
        return "Post updated";
    }
}