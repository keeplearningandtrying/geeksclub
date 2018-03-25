package com.sivalabs.geeksclub.web.controllers;

import com.sivalabs.geeksclub.entities.Link;
import com.sivalabs.geeksclub.entities.Tag;
import com.sivalabs.geeksclub.models.NewLinkDTO;
import com.sivalabs.geeksclub.models.SecurityUser;
import com.sivalabs.geeksclub.repositories.LinkRepository;
import com.sivalabs.geeksclub.repositories.TagRepository;
import com.sivalabs.geeksclub.repositories.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController
{
    private final LinkRepository linkRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    @Autowired
    public HomeController(LinkRepository linkRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        model.addAttribute("links", linkRepository.findAll(sort));
        return "index";
    }

    @GetMapping("/tags/{name}")
    public String byTag(@PathVariable("name")  String tag, Model model){
        Optional<Tag> tagOptional = tagRepository.findByName(tag);
        if(tagOptional.isPresent()) {
            model.addAttribute("links", tagOptional.get().getLinks());
        } else {
            model.addAttribute("links", new ArrayList<>());
        }
        model.addAttribute("searchTag", tag);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "q", defaultValue = "") String q, Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        model.addAttribute("links", linkRepository.findByTitleContainingIgnoreCase(q, sort));
        model.addAttribute("q", q);
        return "index";
    }

    @GetMapping("/newlink")
    public String newLinkForm (Model model) {
        model.addAttribute("link", new NewLinkDTO());
        return "newlink";
    }

    @PostMapping("/links")
    String createLink(@ModelAttribute("link") @Validated NewLinkDTO link,
                      BindingResult errors,
                      @AuthenticationPrincipal SecurityUser loginUser) {
        if(errors.hasErrors()) {
            return "newlink";
        }
        saveLink(link, loginUser);
        return "redirect:/";
    }

    private void saveLink(NewLinkDTO link, SecurityUser loginUser) {
        Link newlink = new Link();
        newlink.setTitle(link.getTitle());
        newlink.setUrl(link.getUrl());
        Set<Tag> tagsList = new HashSet<>();

        Arrays.stream(link.getTags().split(",")).forEach(tagName -> {
            if (tagName.trim() != "") {
                Tag tag = createTagIfNotExist(tagName.trim());
                tagsList.add(tag);
            }
        });
        newlink.setTags(tagsList);
        val user = userRepository.findByEmail(loginUser.getUsername());
        newlink.setCreatedBy(user.get());
        linkRepository.save(newlink);
    }

    private Tag createTagIfNotExist(String tagName)  {
        Optional<Tag> tagOptional = tagRepository.findByName(tagName);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }

        Tag tag = new Tag();
        tag.setName(tagName);
        return tagRepository.save(tag);
    }

    @ModelAttribute("allTags")
    List<Tag> allTags() {
       return tagRepository.findAll();
    }
}
